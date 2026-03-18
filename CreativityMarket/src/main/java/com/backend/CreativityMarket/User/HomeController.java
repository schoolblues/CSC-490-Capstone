package com.backend.CreativityMarket.User;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {

    @GetMapping("/")
    public String homepage(Model model) {
        List<Asset> assets = sampleAssets();
        model.addAttribute("featuredAssets", assets);
        model.addAttribute("newAssets", assets);
        model.addAttribute("categories", sampleCategories());
        model.addAttribute("fileTypes", sampleFileTypes());
        return "homepage";
    }

    @GetMapping("/asset/{id}")
    public String detailedItemView(@PathVariable Long id, Model model) {
        Asset asset = sampleAssets().stream()
                .filter(a -> a.getId().equals(id))
                .findFirst()
                .orElse(null);

        if (asset == null) {
            return "redirect:/";
        }

        model.addAttribute("asset", asset);
        return "detailedItemView";
    }

    private List<Asset> sampleAssets() {
        License personal = new License("Personal",
                "Allowed for personal projects. Do not resell or redistribute the raw file.");
        License commercial = new License("Commercial",
                "Allowed for commercial games/renders. Do not resell the raw model or use it for AI training.");

        List<Asset> assets = new ArrayList<>();

        Asset a1 = new Asset();
        a1.setId(1L);
        a1.setTitle("Modern Chair");
        a1.setDescription("A sleek, modern chair model suitable for architectural visualizations and interior design scenes.");
        a1.setPrice(9.99);
        a1.setThumbnailUrl("/images/apple.png");
        a1.setThumbnailUrls(List.of("/images/apple.png", "/images/banana.png", "/images/orange.webp", "/images/pear.png"));
        a1.setSketchfabUid("dGUVoNVSMYpGGkpuCo5jFMnGqTI");
        a1.setCreatorName("SampleCreator");
        a1.setCreatorAvatarUrl("/images/apple.png");
        a1.setTags(List.of("Furniture", "Interior", "Modern"));
        a1.setFormats(List.of(".blend", ".fbx", ".obj"));
        a1.setLicenses(List.of(personal, commercial));
        a1.setRating(4.5);
        a1.setPublishedDate("August 18, 2024");
        a1.setLastUpdateDate("January 10, 2025");
        a1.setAgeRating("Not Mature");
        a1.setAllowsAiUsage(false);
        assets.add(a1);

        Asset a2 = new Asset();
        a2.setId(2L);
        a2.setTitle("Stylized Tree Set");
        a2.setDescription("A pack of hand-painted stylized trees perfect for games and illustrations.");
        a2.setPrice(14.99);
        a2.setThumbnailUrl("/images/banana.png");
        a2.setThumbnailUrls(List.of("/images/banana.png", "/images/apple.png", "/images/orange.webp", "/images/pear.png"));
        a2.setSketchfabUid("dGUVoNVSMYpGGkpuCo5jFMnGqTI");
        a2.setCreatorName("NatureArtist");
        a2.setCreatorAvatarUrl("/images/banana.png");
        a2.setTags(List.of("Nature", "Stylized", "Game-Ready"));
        a2.setFormats(List.of(".glb", ".fbx"));
        a2.setLicenses(List.of(commercial));
        a2.setRating(4.8);
        a2.setPublishedDate("March 5, 2025");
        a2.setLastUpdateDate("March 17, 2025");
        a2.setAgeRating("Not Mature");
        a2.setAllowsAiUsage(false);
        assets.add(a2);

        Asset a3 = new Asset();
        a3.setId(3L);
        a3.setTitle("Sci-Fi Door");
        a3.setDescription("A high-poly sci-fi door with PBR textures, ideal for cinematic or game environments.");
        a3.setPrice(24.99);
        a3.setThumbnailUrl("/images/orange.webp");
        a3.setThumbnailUrls(List.of("/images/orange.webp", "/images/apple.png", "/images/banana.png", "/images/pear.png"));
        a3.setSketchfabUid("dGUVoNVSMYpGGkpuCo5jFMnGqTI");
        a3.setCreatorName("SciFiModeler");
        a3.setCreatorAvatarUrl("/images/orange.webp");
        a3.setTags(List.of("Sci-Fi", "PBR", "High-Poly"));
        a3.setFormats(List.of(".blend", ".fbx", ".obj", ".glb"));
        a3.setLicenses(List.of(personal, commercial));
        a3.setRating(4.2);
        a3.setPublishedDate("November 12, 2024");
        a3.setLastUpdateDate("February 1, 2025");
        a3.setAgeRating("Not Mature");
        a3.setAllowsAiUsage(true);
        assets.add(a3);

        Asset a4 = new Asset();
        a4.setId(4L);
        a4.setTitle("Fantasy Sword Bundle");
        a4.setDescription("A collection of five fantasy swords with unique designs and textures.");
        a4.setPrice(19.99);
        a4.setThumbnailUrl("/images/pear.png");
        a4.setThumbnailUrls(List.of("/images/pear.png", "/images/apple.png", "/images/banana.png", "/images/orange.webp"));
        a4.setSketchfabUid("dGUVoNVSMYpGGkpuCo5jFMnGqTI");
        a4.setCreatorName("WeaponSmith3D");
        a4.setCreatorAvatarUrl("/images/pear.png");
        a4.setTags(List.of("Fantasy", "Weapons", "Bundle"));
        a4.setFormats(List.of(".fbx", ".obj"));
        a4.setLicenses(List.of(personal));
        a4.setRating(4.9);
        a4.setPublishedDate("June 22, 2024");
        a4.setLastUpdateDate("December 15, 2024");
        a4.setAgeRating("Not Mature");
        a4.setAllowsAiUsage(false);
        assets.add(a4);

        return assets;
    }

    private List<Category> sampleCategories() {
        return List.of(
                new Category(1L, "CHARACTERS", "Digital Art Assets",
                        "/images/apple.png",
                        List.of("/images/banana.png", "/images/orange.webp", "/images/pear.png")),
                new Category(2L, "ENVIRONMENTS", "3D Scenes & Worlds",
                        "/images/banana.png",
                        List.of("/images/apple.png", "/images/orange.webp", "/images/pear.png")),
                new Category(3L, "VEHICLES", "Cars, Ships & More",
                        "/images/orange.webp",
                        List.of("/images/apple.png", "/images/banana.png", "/images/pear.png")),
                new Category(4L, "WEAPONS", "Swords, Guns & Gear",
                        "/images/pear.png",
                        List.of("/images/apple.png", "/images/banana.png", "/images/orange.webp")),
                new Category(5L, "FURNITURE", "Interior Design Assets",
                        "/images/apple.png",
                        List.of("/images/banana.png", "/images/orange.webp", "/images/pear.png")),
                new Category(6L, "NATURE", "Trees, Rocks & Terrain",
                        "/images/banana.png",
                        List.of("/images/apple.png", "/images/orange.webp", "/images/pear.png"))
        );
    }

    private List<String[]> sampleFileTypes() {
        return List.of(
                new String[]{"Blender",        ".blend"},
                new String[]{"OBJ",            ".obj, .mtl"},
                new String[]{"STL",            ".stl"},
                new String[]{"FBX",            ".fbx"},
                new String[]{"Unity 3D",       ".unitypackage, .prefab"},
                new String[]{"Unreal Engine",  ".uasset"},
                new String[]{"3DS Max",        ".max"},
                new String[]{"Maya",           ".ma, .mb"},
                new String[]{"Cinema 4D",      ".c4d"},
                new String[]{"glTF",           ".gltf, .glb"},
                new String[]{"Collada",        ".dae"},
                new String[]{"Beth",           ".nif"}
        );
    }
}
