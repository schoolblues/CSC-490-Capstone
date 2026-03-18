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

    private final SketchfabService sketchfabService;

    public ModelUploadController(SketchfabService sketchfabService) {
        this.sketchfabService = sketchfabService;
    }

    @GetMapping
    public String showUploadForm(Model model) {
        model.addAttribute("uploadForm", new ModelUploadForm());
        return "artist/modelupload";
    }

    @PostMapping
    public String handleUpload(@ModelAttribute("uploadForm") ModelUploadForm form,
                               RedirectAttributes redirectAttributes) {

        if (form.getModelFile() == null || form.getModelFile().isEmpty()) {
            redirectAttributes.addFlashAttribute("errorMessage", "Please select a model file to upload.");
            return "redirect:/upload";
        }

        try {
            String uid = sketchfabService.uploadModel(
                    form.getModelFile(),
                    form.getTitle(),
                    form.getDescription(),
                    form.getTags()
            );

            System.out.println("Sketchfab upload successful. UID: " + uid);

            redirectAttributes.addFlashAttribute("successMessage",
                    "Your model \"" + form.getTitle() + "\" has been uploaded! Sketchfab UID: " + uid);
            redirectAttributes.addFlashAttribute("sketchfabUid", uid);

        } catch (Exception e) {
            System.err.println("Sketchfab upload failed: " + e.getMessage());
            redirectAttributes.addFlashAttribute("errorMessage",
                    "Upload failed: " + e.getMessage());
        }

        return "redirect:/upload";
    }
}
