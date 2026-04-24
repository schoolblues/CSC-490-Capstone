package com.backend.CreativityMarket.User;

import com.backend.CreativityMarket.Marketplace.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {

    private final AssetRepository assetRepository;
    private final CategoryService categoryService;

    public HomeController(AssetRepository assetRepository, CategoryService categoryService) {
        this.assetRepository = assetRepository;
        this.categoryService = categoryService;
    }

    @GetMapping("/")
    public String homepage(Model model) {
        List<Asset> dbAssets = assetRepository.findAll();
        List<Asset> assets = dbAssets.isEmpty() ? sampleAssets() : dbAssets;
        model.addAttribute("featuredAssets", assets);
        model.addAttribute("newAssets", assets);

        List<Asset> trendingAssets = dbAssets.isEmpty()
                ? sampleAssets().stream().filter(a -> a.getPrice() > 0).toList()
                : dbAssets.stream().filter(a -> a.getPrice() > 0).toList();
        List<Asset> trendingFreeAssets = dbAssets.isEmpty()
                ? List.of()
                : dbAssets.stream().filter(a -> a.getPrice() == 0).toList();
        model.addAttribute("trendingAssets", trendingAssets);
        model.addAttribute("trendingFreeAssets", trendingFreeAssets);

        List<com.backend.CreativityMarket.Marketplace.Category> dbCategories = categoryService.getAllCategories();
        List<Category> categories = dbCategories.isEmpty()
                ? sampleCategories()
                : dbCategories.stream()
                    .map(c -> {
                        List<Asset> catAssets = assetsForCategory(c);
                        String mainUrl  = catAssets.isEmpty() ? null : catAssets.get(0).getThumbnailUrl();
                        String mainUid  = catAssets.isEmpty() ? null : catAssets.get(0).getSketchfabUid();
                        List<String> previewUrls = catAssets.stream().skip(1).map(Asset::getThumbnailUrl).toList();
                        List<String> previewUids = catAssets.stream().skip(1).map(Asset::getSketchfabUid).toList();
                        return new Category(c.getId(), c.getName().toUpperCase(), c.getDescription(),
                                mainUrl, mainUid, previewUrls, previewUids);
                    })
                    .toList();
        model.addAttribute("categories", categories);

        model.addAttribute("fileTypes", sampleFileTypes());
        return "homepage";
    }

    @GetMapping("/asset/{id}")
    public String detailedItemView(@PathVariable Long id, Model model) {
        Asset asset = assetRepository.findById(id).orElse(null);

        if (asset == null) {
            asset = sampleAssets().stream()
                    .filter(a -> a.getId() != null && a.getId().equals(id))
                    .findFirst()
                    .orElse(null);
        }

        if (asset == null) {
            return "redirect:/";
        }

        model.addAttribute("asset", asset);
        return "user/detailedItemView";
    }

    private List<Asset> sampleAssets() {
        List<Asset> assets = new ArrayList<>();

        Asset a1 = new Asset();
        a1.setId(1L);
        a1.setTitle("Modern Chair");
        a1.setDescription("A sleek, modern chair model suitable for architectural visualizations and interior design scenes.");
        a1.setPrice(9.99);
        a1.setThumbnailUrl("/images/apple.png");
        a1.setSketchfabUid("dGUVoNVSMYpGGkpuCo5jFMnGqTI");
        a1.setCreatorName("SampleCreator");
        a1.setCreatorAvatarUrl("/images/apple.png");
        a1.setTags("Furniture, Interior, Modern");
        a1.setFileType("BLEND");
        a1.setLicense("Commercial");
        a1.setRating(4.5);
        a1.setCategory("furniture");
        assets.add(a1);

        Asset a2 = new Asset();
        a2.setId(2L);
        a2.setTitle("Stylized Tree Set");
        a2.setDescription("A pack of hand-painted stylized trees perfect for games and illustrations.");
        a2.setPrice(14.99);
        a2.setThumbnailUrl("/images/banana.png");
        a2.setSketchfabUid("dGUVoNVSMYpGGkpuCo5jFMnGqTI");
        a2.setCreatorName("NatureArtist");
        a2.setCreatorAvatarUrl("/images/banana.png");
        a2.setTags("Nature, Stylized, Game-Ready");
        a2.setFileType("GLB");
        a2.setLicense("Commercial");
        a2.setRating(4.8);
        a2.setCategory("nature");
        assets.add(a2);

        Asset a3 = new Asset();
        a3.setId(3L);
        a3.setTitle("Sci-Fi Door");
        a3.setDescription("A high-poly sci-fi door with PBR textures, ideal for cinematic or game environments.");
        a3.setPrice(24.99);
        a3.setThumbnailUrl("/images/orange.webp");
        a3.setSketchfabUid("dGUVoNVSMYpGGkpuCo5jFMnGqTI");
        a3.setCreatorName("SciFiModeler");
        a3.setCreatorAvatarUrl("/images/orange.webp");
        a3.setTags("Sci-Fi, PBR, High-Poly");
        a3.setFileType("BLEND");
        a3.setLicense("Personal");
        a3.setRating(4.2);
        a3.setCategory("scifi");
        assets.add(a3);

        Asset a4 = new Asset();
        a4.setId(4L);
        a4.setTitle("Fantasy Sword Bundle");
        a4.setDescription("A collection of five fantasy swords with unique designs and textures.");
        a4.setPrice(19.99);
        a4.setThumbnailUrl("/images/pear.png");
        a4.setSketchfabUid("dGUVoNVSMYpGGkpuCo5jFMnGqTI");
        a4.setCreatorName("WeaponSmith3D");
        a4.setCreatorAvatarUrl("/images/pear.png");
        a4.setTags("Fantasy, Weapons, Bundle");
        a4.setFileType("FBX");
        a4.setLicense("Personal");
        a4.setRating(4.9);
        a4.setCategory("weapons");
        assets.add(a4);

        return assets;
    }

    private List<Category> sampleCategories() {
        return List.of(
                new Category(1L, "CHARACTERS", "Digital Art Assets",
                        "/images/apple.png", null,
                        List.of("/images/banana.png", "/images/orange.webp", "/images/pear.png"), List.of()),
                new Category(2L, "ENVIRONMENTS", "3D Scenes & Worlds",
                        "/images/banana.png", null,
                        List.of("/images/apple.png", "/images/orange.webp", "/images/pear.png"), List.of()),
                new Category(3L, "VEHICLES", "Cars, Ships & More",
                        "/images/orange.webp", null,
                        List.of("/images/apple.png", "/images/banana.png", "/images/pear.png"), List.of()),
                new Category(4L, "WEAPONS", "Swords, Guns & Gear",
                        "/images/pear.png", null,
                        List.of("/images/apple.png", "/images/banana.png", "/images/orange.webp"), List.of()),
                new Category(5L, "FURNITURE", "Interior Design Assets",
                        "/images/apple.png", null,
                        List.of("/images/banana.png", "/images/orange.webp", "/images/pear.png"), List.of()),
                new Category(6L, "NATURE", "Trees, Rocks & Terrain",
                        "/images/banana.png", null,
                        List.of("/images/apple.png", "/images/orange.webp", "/images/pear.png"), List.of())
        );
    }

    private List<Asset> assetsForCategory(com.backend.CreativityMarket.Marketplace.Category cat) {
        List<Asset> byRelationship = assetRepository.findByCategoryEntity(cat);
        List<Asset> byTag = assetRepository.findByTagKeyword(cat.getName());

        return java.util.stream.Stream.concat(byRelationship.stream(), byTag.stream())
                .collect(java.util.stream.Collectors.collectingAndThen(
                        java.util.stream.Collectors.toMap(Asset::getId, a -> a, (a, b) -> a, java.util.LinkedHashMap::new),
                        m -> m.values().stream().limit(5).toList()
                ));
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