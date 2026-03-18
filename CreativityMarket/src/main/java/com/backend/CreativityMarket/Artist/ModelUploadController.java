package com.backend.CreativityMarket.Artist;

import com.backend.CreativityMarket.User.Asset;
import com.backend.CreativityMarket.User.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;

import java.util.Optional;

@Controller
@RequestMapping("/upload")
public class ModelUploadController {

    private final SketchfabUploadService sketchfabUploadService;
    private final MarketplaceAssetService marketplaceAssetService;

    public ModelUploadController(SketchfabUploadService sketchfabUploadService,
                                 MarketplaceAssetService marketplaceAssetService) {
        this.sketchfabUploadService = sketchfabUploadService;
        this.marketplaceAssetService = marketplaceAssetService;
    }

    @GetMapping
    public String showUploadForm(Model model) {
        model.addAttribute("uploadForm", new ModelUploadForm());
        model.addAttribute("sketchfabUploadEnabled", sketchfabUploadService.isUploadEnabled());
        return "artist/modelupload";
    }

    @PostMapping
    public String handleUpload(@ModelAttribute("uploadForm") ModelUploadForm form,
                               RedirectAttributes redirectAttributes,
                               HttpSession session) {

        User uploader = (User) session.getAttribute("user");

        Optional<String> uidFromEmbed = sketchfabUploadService.parseUid(form.getEmbedUrl());
        Optional<String> uidFromUpload = uidFromEmbed.isPresent()
            ? Optional.empty()
            : sketchfabUploadService.uploadModel(form.getModelFile(), form);
        String sketchfabUid = uidFromEmbed.or(() -> uidFromUpload).orElse(null);

        Asset published = marketplaceAssetService.publish(form, uploader, sketchfabUid);

        System.out.println("Upload received: " + form.getTitle());
        System.out.println("  File type: " + form.getFileType());
        System.out.println("  Category:  " + form.getCategory());
        System.out.println("  Price:     $" + form.getPrice());
        System.out.println("  License:   " + form.getLicense());

        if (form.getModelFile() != null && !form.getModelFile().isEmpty()) {
            System.out.println("  Model file: " + form.getModelFile().getOriginalFilename()
                    + " (" + form.getModelFile().getSize() + " bytes)");
        }

        String successMessage = "Your model \"" + published.getTitle() + "\" is now listed on the homepage.";
        if (sketchfabUid != null) {
            successMessage += " Sketchfab preview is enabled.";
        } else {
            successMessage += " Add a Sketchfab API token in server config to auto-enable 3D previews.";
        }

        redirectAttributes.addFlashAttribute("successMessage", successMessage);

        return "redirect:/upload";
    }
}
