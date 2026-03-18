package com.backend.CreativityMarket.Artist;

import com.backend.CreativityMarket.User.Asset;
import com.backend.CreativityMarket.User.AssetRepository;
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
    private final AssetRepository assetRepository;

    public ModelUploadController(SketchfabService sketchfabService, AssetRepository assetRepository) {
        this.sketchfabService = sketchfabService;
        this.assetRepository = assetRepository;
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

            Asset asset = new Asset();
            asset.setTitle(form.getTitle());
            asset.setDescription(form.getDescription());
            asset.setPrice(form.getPrice());
            asset.setFileType(form.getFileType());
            asset.setCategory(form.getCategory());
            asset.setTags(form.getTags());
            asset.setLicense(form.getLicense());
            asset.setSketchfabUid(uid);
            asset.setPolyCount(form.getPolyCount());
            asset.setVertices(form.getVertices());
            asset.setPolygons(form.getPolygons());
            asset.setGeometry(form.getGeometry());
            asset.setUvMapping(form.getUvMapping());
            asset.setRigged(form.isRigged());
            asset.setAnimated(form.isAnimated());
            asset.setTexturesIncluded(form.getTexturesIncluded());
            asset.setTextureResolution(form.getTextureResolution());
            asset.setMaterials(form.getMaterials());

            assetRepository.save(asset);

            redirectAttributes.addFlashAttribute("successMessage",
                    "Your model \"" + form.getTitle() + "\" has been uploaded and saved!");

        } catch (Exception e) {
            System.err.println("Upload failed: " + e.getMessage());
            redirectAttributes.addFlashAttribute("errorMessage",
                    "Upload failed: " + e.getMessage());
        }

        return "redirect:/upload";
    }
}
