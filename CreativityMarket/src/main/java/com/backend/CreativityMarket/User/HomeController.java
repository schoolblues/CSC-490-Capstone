package com.backend.CreativityMarket.User;

import com.backend.CreativityMarket.Artist.MarketplaceAssetService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class HomeController {

        private final MarketplaceAssetService marketplaceAssetService;

        public HomeController(MarketplaceAssetService marketplaceAssetService) {
                this.marketplaceAssetService = marketplaceAssetService;
        }

        @GetMapping({"/", "/homepage.html"})
    public String homepage(Model model) {
                model.addAttribute("featuredAssets", marketplaceAssetService.listFeatured(8));
                model.addAttribute("newAssets", marketplaceAssetService.listNewest(8));
        model.addAttribute("categories", sampleCategories());
        model.addAttribute("fileTypes", sampleFileTypes());
        return "homepage";
    }

    @GetMapping("/asset/{id}")
    public String detailedItemView(@PathVariable Long id, Model model) {
                Asset asset = marketplaceAssetService.findById(id);

        if (asset == null) {
            return "redirect:/";
        }

        model.addAttribute("asset", asset);
        return "detailedItemView";
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
