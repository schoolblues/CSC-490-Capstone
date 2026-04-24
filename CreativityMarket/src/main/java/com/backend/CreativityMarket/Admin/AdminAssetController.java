package com.backend.CreativityMarket.Admin;

import com.backend.CreativityMarket.Marketplace.CategoryService;
import com.backend.CreativityMarket.User.Asset;
import com.backend.CreativityMarket.User.AssetRepository;
import com.backend.CreativityMarket.User.User;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/assets")
@RequiredArgsConstructor
public class AdminAssetController {

    private final AdminService adminService;
    private final AssetRepository assetRepository;
    private final CategoryService categoryService;

    // =========================
    // HELPER
    // =========================
    private User getAdmin(HttpSession session) {
        User user = (User) session.getAttribute("user");

        if (user == null || !user.isAdminOrAbove()) {
            throw new SecurityException("Unauthorized");
        }

        return user;
    }

    // =========================
    // LIST
    // =========================
    @GetMapping
    public String listAssets(Model model, HttpSession session) {

        User requester = getAdmin(session);

        model.addAttribute("user", requester);
        model.addAttribute("assets", assetRepository.findAll());
        model.addAttribute("asset", new Asset());
        model.addAttribute("categories", categoryService.getAllCategories());

        return "admin/assets";
    }

    // =========================
    // CREATE FORM
    // =========================
    @GetMapping("/new")
    public String showCreateForm(Model model, HttpSession session) {

        User requester = getAdmin(session);

        model.addAttribute("user", requester);
        model.addAttribute("asset", new Asset());
        model.addAttribute("categories", categoryService.getAllCategories());

        return "admin/asset-form";
    }

    @PostMapping("/new")
    public String createAsset(@ModelAttribute Asset asset,
                              @RequestParam(value = "categoryId", required = false) Long categoryId,
                              HttpSession session) {

        User requester = getAdmin(session);

        if (categoryId != null) {
            asset.setCategoryEntity(categoryService.getCategoryById(categoryId));
        }

        adminService.createAsset(asset, requester);

        return "redirect:/admin/assets";
    }

    // =========================
    // EDIT
    // =========================
    @GetMapping("/edit/{id}")
    public String editAsset(@PathVariable Long id, Model model, HttpSession session) {

        User requester = getAdmin(session);

        Asset asset = assetRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Asset not found"));

        model.addAttribute("user", requester);
        model.addAttribute("asset", asset);
        model.addAttribute("categories", categoryService.getAllCategories());

        return "admin/asset-form";
    }

    @PostMapping("/edit/{id}")
    public String updateAsset(@PathVariable Long id,
                              @ModelAttribute Asset asset,
                              @RequestParam(value = "categoryId", required = false) Long categoryId,
                              HttpSession session) {

        User requester = getAdmin(session);

        asset.setId(id);
        if (categoryId != null) {
            asset.setCategoryEntity(categoryService.getCategoryById(categoryId));
        }
        adminService.updateAsset(asset, requester);

        return "redirect:/admin/assets";
    }

    // =========================
    // DELETE
    // =========================
    @PostMapping("/delete/{id}")
    public String deleteAsset(@PathVariable Long id, HttpSession session) {

        User requester = getAdmin(session);

        adminService.deleteAsset(id, requester);

        return "redirect:/admin/assets";
    }
}