package com.backend.CreativityMarket.Artist;

import com.backend.CreativityMarket.Marketplace.CategoryRepository;
import com.backend.CreativityMarket.User.Asset;
import com.backend.CreativityMarket.User.AssetRepository;
import com.backend.CreativityMarket.User.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/upload")
public class ModelUploadController {

    private final SketchfabService sketchfabService;
    private final AssetRepository assetRepository;
    private final CategoryRepository categoryRepository;

    public ModelUploadController(SketchfabService sketchfabService, AssetRepository assetRepository, CategoryRepository categoryRepository) {
        this.sketchfabService = sketchfabService;
        this.assetRepository = assetRepository;
        this.categoryRepository = categoryRepository;
    }

    @GetMapping
    public String showUploadForm(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) return "redirect:/signin";

        model.addAttribute("uploadForm", new ModelUploadForm());
        model.addAttribute("user", user);
        return "artist/modelupload";
    }

    @PostMapping
    public String handleUpload(@ModelAttribute("uploadForm") ModelUploadForm form,
                               RedirectAttributes redirectAttributes,
                               HttpSession session) {

        User uploader = (User) session.getAttribute("user");
        if (uploader == null) return "redirect:/signin";

        if (form.getModelFile() == null || form.getModelFile().isEmpty()) {
            redirectAttributes.addFlashAttribute("errorMessage", "Please select a model file to upload.");
            return "redirect:/upload";
        }

        Optional<String> uid = sketchfabService.uploadModel(
                form.getModelFile(),
                form.getTitle(),
                form.getDescription(),
                form.getTags()
        );

        String creatorName = (uploader.getName() != null && !uploader.getName().isBlank())
                ? uploader.getName() : "Marketplace Creator";

        Asset asset = new Asset();
        asset.setTitle(form.getTitle());
        asset.setDescription(form.getDescription());
        asset.setPrice(form.isFree() ? 0.0 : form.getPrice());
        asset.setFileType(form.getFileType());
        asset.setCategory(form.getCategory());
        asset.setTags(form.getTags());
        asset.setLicense(form.getLicense());
        asset.setSketchfabUid(uid.orElse(null));
        uid.ifPresent(u -> sketchfabService.fetchThumbnailUrl(u).ifPresent(asset::setThumbnailUrl));
        asset.setCreatorName(creatorName);
        asset.setCreator(uploader);
        asset.setCreatorAvatarUrl(uploader.getProfileImageUrl());
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
        categoryRepository.findByNameIgnoreCase(form.getCategory())
                .ifPresent(asset::setCategoryEntity);

        assetRepository.save(asset);

        String msg = "Your model \"" + asset.getTitle() + "\" is now listed on the marketplace.";
        if (uid.isPresent()) {
            msg += " 3D preview enabled.";
        } else {
            msg += " 3D preview will be available once processing is complete.";
        }
        redirectAttributes.addFlashAttribute("successMessage", msg);

        return "redirect:/upload";
    }

    @GetMapping("/my")
    public String myUploads(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) return "redirect:/signin";

        List<Asset> mine = assetRepository.findByCreator(user);
        model.addAttribute("user", user);
        model.addAttribute("uploads", mine);
        return "user/my-uploads";
    }
}
