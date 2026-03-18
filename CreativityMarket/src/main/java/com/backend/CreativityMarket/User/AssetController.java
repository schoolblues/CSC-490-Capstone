package com.backend.CreativityMarket.User;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/assets")
public class AssetController {

    @GetMapping("/{id}")
    public String asset(@PathVariable Long id, Model model) {
        Asset found = sampleAssets().stream()
                .filter(a -> a.getId().equals(id))
                .findFirst()
                .orElse(null);

        if (found == null) {
            return "redirect:/users/home";
        }

        model.addAttribute("asset", found);
        return "user/asset"; 
    }

    private List<Asset> sampleAssets() {
        License personal = new License("Personal",
                "Allowed for personal projects. Do not resell or redistribute the raw file.");

        License commercial = new License("Commercial",
                "Allowed for commercial games/renders. Do not resell the raw model or use it for AI training.");

        List<Asset> assets = new java.util.ArrayList<>();

        Asset a1 = new Asset();
        a1.setId(1L);
        a1.setTitle("Modern Chair");
        a1.setDescription("A sleek, modern chair model.");
        a1.setPrice(12.00);
        a1.setThumbnailUrl("FBX");
        a1.setSketchfabUid("dGUVoNVSMYpGGkpuCo5jFMnGqTI");
        a1.setCreatorName("SampleCreator");
        a1.setFormats(List.of(".fbx", ".obj"));
        a1.setLicenses(List.of(personal));
        assets.add(a1);

        Asset a2 = new Asset();
        a2.setId(2L);
        a2.setTitle("Stylized Tree Set");
        a2.setDescription("A pack of hand-painted stylized trees.");
        a2.setPrice(10.00);
        a2.setThumbnailUrl("GLB");
        a2.setSketchfabUid("dGUVoNVSMYpGGkpuCo5jFMnGqTI");
        a2.setCreatorName("NatureArtist");
        a2.setFormats(List.of(".glb", ".fbx"));
        a2.setLicenses(List.of(commercial));
        assets.add(a2);

        Asset a3 = new Asset();
        a3.setId(3L);
        a3.setTitle("Sci-Fi Door");
        a3.setDescription("A high-poly sci-fi door with PBR textures.");
        a3.setPrice(15.00);
        a3.setThumbnailUrl("OBJ");
        a3.setSketchfabUid("dGUVoNVSMYpGGkpuCo5jFMnGqTI");
        a3.setCreatorName("SciFiModeler");
        a3.setFormats(List.of(".blend", ".fbx", ".obj"));
        a3.setLicenses(List.of(commercial));
        assets.add(a3);

        return assets;
    }
    @GetMapping("/new")
public String newAsset() {
    return "user/new";
}
}
