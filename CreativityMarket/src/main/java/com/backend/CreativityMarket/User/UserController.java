package com.backend.CreativityMarket.User;

import com.backend.CreativityMarket.Marketplace.OrderItem;
import com.backend.CreativityMarket.Marketplace.OrderRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/users")
public class UserController {

    private final OrderRepository orderRepository;
    private final WishlistItemRepository wishlistItemRepository;
    private final AssetRepository assetRepository;
    private final UserRepository userRepository;

    public UserController(OrderRepository orderRepository,
                          WishlistItemRepository wishlistItemRepository,
                          AssetRepository assetRepository,
                          UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.wishlistItemRepository = wishlistItemRepository;
        this.assetRepository = assetRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/home")
    public String userHome(HttpSession session, Model model) {

        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/signin";
        }

        List<OrderItem> purchases = new ArrayList<>();
        if (user.getId() != null) {
            purchases = orderRepository.findByUserIdWithItemsOrderByCreatedAtDesc(user.getId())
                    .stream()
                    .flatMap(order -> order.getItems().stream())
                    .collect(Collectors.toList());
        }

        List<Asset> wishlist = wishlistItemRepository.findByUser(user).stream()
                .map(WishlistItem::getAsset)
                .collect(Collectors.toList());

        List<Asset> uploads = assetRepository.findByCreator(user);

        model.addAttribute("user", user);
        model.addAttribute("purchases", purchases);
        model.addAttribute("wishlist", wishlist);
        model.addAttribute("uploads", uploads);
        model.addAttribute("purchasesCount", purchases.size());
        model.addAttribute("wishlistCount", wishlist.size());
        model.addAttribute("uploadsCount", uploads.size());
        model.addAttribute("downloadsCount", purchases.size());

        return "user/user-home";
    }

    @GetMapping("/account")
    public String accountSettings(HttpSession session, Model model,
                                  @RequestParam(value = "saved", required = false) Boolean saved,
                                  @RequestParam(value = "error", required = false) String error) {

        User user = (User) session.getAttribute("user");
        if (user == null) return "redirect:/signin";

        model.addAttribute("user", user);
        model.addAttribute("saved", saved != null && saved);
        model.addAttribute("error", error);

        return "user/account-settings";
    }

    @PostMapping("/account")
    public String saveAccount(HttpSession session,
                              @RequestParam String name,
                              @RequestParam String email,
                              @RequestParam(required = false) String bio,
                              @RequestParam(required = false) String location,
                              @RequestParam(required = false) String currentPassword,
                              @RequestParam(required = false) String newPassword,
                              @RequestParam(value = "profileImage", required = false) MultipartFile profileImage) {

        User sessionUser = (User) session.getAttribute("user");
        if (sessionUser == null) return "redirect:/signin";

        User user = userRepository.findById(sessionUser.getId()).orElse(null);
        if (user == null) return "redirect:/signin";

        user.setName(name);
        user.setEmail(email);
        user.setBio(bio);
        user.setLocation(location);

        if (newPassword != null && !newPassword.isBlank()) {
            if (currentPassword == null || !currentPassword.equals(user.getPassword())) {
                return "redirect:/users/account?error=password";
            }
            user.setPassword(newPassword);
        }

        if (profileImage != null && !profileImage.isEmpty()) {
            try {
                String original = profileImage.getOriginalFilename();
                String ext = (original != null && original.contains(".")) ? original.substring(original.lastIndexOf('.')) : ".png";
                String filename = "user-" + user.getId() + "-" + System.currentTimeMillis() + ext;

                Path uploadDir = Paths.get(System.getProperty("user.dir"),
                        "CreativityMarket", "src", "main", "resources", "static", "images", "uploads");
                if (!Files.exists(uploadDir)) {
                    Path altDir = Paths.get(System.getProperty("user.dir"),
                            "src", "main", "resources", "static", "images", "uploads");
                    uploadDir = altDir;
                }
                Files.createDirectories(uploadDir);

                File dest = uploadDir.resolve(filename).toFile();
                Files.copy(profileImage.getInputStream(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
                user.setProfileImageUrl("/images/uploads/" + filename);
            } catch (IOException ignored) {
            }
        }

        userRepository.save(user);
        session.setAttribute("user", user);

        return "redirect:/users/account?saved=true";
    }

    @GetMapping("/profile/edit")
    public String editProfileRedirect() {
        return "redirect:/users/account";
    }
}
