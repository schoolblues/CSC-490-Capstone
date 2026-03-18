package com.backend.CreativityMarket.Artist;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/upload")
public class ModelUploadController {

    @GetMapping
    public String showUploadForm(Model model) {
        model.addAttribute("uploadForm", new ModelUploadForm());
        return "artist/modelupload";
    }

    @PostMapping
    public String handleUpload(@ModelAttribute("uploadForm") ModelUploadForm form,
                               RedirectAttributes redirectAttributes) {

        System.out.println("Upload received: " + form.getTitle());
        System.out.println("  File type: " + form.getFileType());
        System.out.println("  Category:  " + form.getCategory());
        System.out.println("  Price:     $" + form.getPrice());
        System.out.println("  License:   " + form.getLicense());

        if (form.getModelFile() != null && !form.getModelFile().isEmpty()) {
            System.out.println("  Model file: " + form.getModelFile().getOriginalFilename()
                    + " (" + form.getModelFile().getSize() + " bytes)");
        }

        redirectAttributes.addFlashAttribute("successMessage",
                "Your model \"" + form.getTitle() + "\" has been submitted!");

        return "redirect:/upload";
    }
}
