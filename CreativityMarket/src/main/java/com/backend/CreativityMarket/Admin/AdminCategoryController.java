package com.backend.CreativityMarket.Admin;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.backend.CreativityMarket.User.User;
import com.backend.CreativityMarket.Marketplace.Category;
import com.backend.CreativityMarket.Marketplace.CategoryService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/categories")
public class AdminCategoryController {

    private final CategoryService categoryService;
    private final AdminService adminService;

    // List all categories
    @GetMapping
    public String listCategories(Model model, HttpSession session) {
        model.addAttribute("categories",categoryService.getAllCategories());
        return "admin/categories"; // template: /templates/admin/categories.ftl
    }

    // Show form to create a new category
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("category", new Category());
        return "admin/category-form";
    }

    // Handle form submission for creating a new category
    @PostMapping("/new")
    public String createCategory(@ModelAttribute Category category, HttpSession session) {
        User user = (User) session.getAttribute("user");
        adminService.createCategory(category.getName(), category.getDescription(), user);
        return "redirect:/admin/categories";
    }

    // Show form to edit existing category
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("category", categoryService.getCategoryById(id));
        return "admin/category-form";
    }

    // Handle form submission for editing
    @PostMapping("/edit/{id}")
    public String updateCategory(@PathVariable Long id, @ModelAttribute Category category, HttpSession session) {
        User user = (User) session.getAttribute("user");
        adminService.updateCategory(id, category.getName(), category.getDescription(), user);
        return "redirect:/admin/categories";
    }

    // Delete a category
    @PostMapping("/delete/{id}")
    public String deleteCategory(@PathVariable Long id, HttpSession session) {
        User user = (User) session.getAttribute("user");
        adminService.deleteCategory(id, user);
        return "redirect:/admin/categories";
    }
}