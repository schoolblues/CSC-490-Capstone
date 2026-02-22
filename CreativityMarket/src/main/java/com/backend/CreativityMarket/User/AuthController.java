package com.backend.CreativityMarket.User;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    private final UserRepository userRepository;

    public AuthController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

   
    @GetMapping("/signup")
    public String signupForm() {
        return "user/signup";
    }

    @PostMapping("/signup")
    public String signupSubmit(@RequestParam String name,
                               @RequestParam String email,
                               @RequestParam String password,
                               HttpSession session,
                               Model model) {

        String normalizedEmail = email.trim().toLowerCase();

        if (userRepository.existsByEmail(normalizedEmail)) {
            model.addAttribute("error", "That email is already registered. Try signing in.");
            return "user/signup";
        }

        User user = new User();
        user.setName(name.trim());
        user.setEmail(normalizedEmail);
        user.setPassword(password); 
        user.setRole("USER");
        user.setCreatedAt(java.time.LocalDate.now().toString());

        userRepository.save(user);

        session.setAttribute("user", user);
        return "redirect:/users/home";
    }

   
    @GetMapping("/signin")
    public String signinForm() {
        return "user/signin";
    }

    @PostMapping("/signin")
    public String signinSubmit(@RequestParam String email,
                               @RequestParam String password,
                               HttpSession session,
                               Model model) {

        String normalizedEmail = email.trim().toLowerCase();

        User user = userRepository.findByEmail(normalizedEmail).orElse(null);

        if (user == null || user.getPassword() == null || !user.getPassword().equals(password)) {
            model.addAttribute("error", "Invalid email or password.");
            return "user/signin";
        }

        session.setAttribute("user", user);
        return "redirect:/users/home";
    }

    
    @GetMapping("/users/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/signin";
    }
}
