package com.backend.CreativityMarket.Artist;

import com.backend.CreativityMarket.User.Asset;
import com.backend.CreativityMarket.User.License;
import com.backend.CreativityMarket.User.User;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class MarketplaceAssetService {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("MMMM d, yyyy");

    private final CopyOnWriteArrayList<Asset> assets = new CopyOnWriteArrayList<>();
    private final AtomicLong idGenerator = new AtomicLong(100);

    public MarketplaceAssetService() {
        seed();
    }

    public List<Asset> listNewest(int limit) {
        List<Asset> ordered = new ArrayList<>(assets);
        ordered.sort(Comparator.comparing(Asset::getId).reversed());
        return ordered.subList(0, Math.min(limit, ordered.size()));
    }

    public List<Asset> listFeatured(int limit) {
        List<Asset> ordered = new ArrayList<>(assets);
        ordered.sort(Comparator.comparingDouble(Asset::getRating).reversed());
        return ordered.subList(0, Math.min(limit, ordered.size()));
    }

    public List<Asset> allAssets() {
        return Collections.unmodifiableList(assets);
    }

    public Asset findById(Long id) {
        return assets.stream().filter(a -> a.getId().equals(id)).findFirst().orElse(null);
    }

    public Asset publish(ModelUploadForm form, User uploader, String sketchfabUid) {
        Asset asset = new Asset();
        asset.setId(idGenerator.incrementAndGet());
        asset.setTitle(nonBlank(form.getTitle(), "Untitled Model"));
        asset.setDescription(nonBlank(form.getDescription(), "No description provided."));
        asset.setPrice(form.isFree() ? 0.00 : Math.max(0.00, form.getPrice()));
        asset.setFileType(nonBlank(form.getFileType(), "Unknown"));
        asset.setSketchfabUid(sketchfabUid);
        asset.setThumbnailUrl(defaultThumbnail());
        asset.setCreatorName(resolveCreatorName(uploader));
        asset.setCreatorAvatarUrl(defaultThumbnail());
        asset.setFormats(List.of(form.getFileType() == null ? "" : ("." + form.getFileType().toLowerCase())));
        asset.setTags(splitTags(form.getTags()));

        License license = new License(nonBlank(form.getLicense(), "standard"),
                nonBlank(form.getLicenseNotes(), "Standard marketplace license terms apply."));
        asset.setLicense(license);

        asset.setRating(0.0);
        String today = LocalDate.now().format(DATE_FORMATTER);
        asset.setPublishedDate(today);
        asset.setLastUpdateDate(today);
        asset.setAgeRating("Not Mature");
        asset.setAllowsAiUsage(false);

        assets.add(asset);
        return asset;
    }

    private List<String> splitTags(String csv) {
        if (csv == null || csv.isBlank()) {
            return List.of("New Upload");
        }

        return List.of(csv.split(","))
                .stream()
                .map(String::trim)
                .filter(s -> !s.isBlank())
                .limit(4)
                .toList();
    }

    private String resolveCreatorName(User uploader) {
        if (uploader == null || uploader.getName() == null || uploader.getName().isBlank()) {
            return "Marketplace Creator";
        }
        return uploader.getName();
    }

    private String defaultThumbnail() {
        return "/images/apple.png";
    }

    private String nonBlank(String value, String fallback) {
        return value == null || value.isBlank() ? fallback : value;
    }

    private void seed() {
        License personal = new License("Personal", "Allowed for personal projects. Do not resell raw files.");
        License commercial = new License("Commercial", "Commercial use is allowed; redistribution of raw files is prohibited.");

        Asset a1 = new Asset();
        a1.setId(idGenerator.incrementAndGet());
        a1.setTitle("Modern Chair");
        a1.setDescription("A sleek chair for interior renders.");
        a1.setPrice(9.99);
        a1.setFileType("FBX");
        a1.setThumbnailUrl("/images/apple.png");
        a1.setSketchfabUid("a5eb5c78e5a14955802e7eb64b76e1a1");
        a1.setCreatorName("SampleCreator");
        a1.setCreatorAvatarUrl("/images/apple.png");
        a1.setTags(List.of("Furniture", "Interior", "Modern"));
        a1.setFormats(List.of(".fbx", ".obj"));
        a1.setLicense(personal);
        a1.setRating(4.7);
        a1.setPublishedDate("March 1, 2026");
        a1.setLastUpdateDate("March 10, 2026");
        a1.setAgeRating("Not Mature");
        a1.setAllowsAiUsage(false);
        assets.add(a1);

        Asset a2 = new Asset();
        a2.setId(idGenerator.incrementAndGet());
        a2.setTitle("Stylized Tree Set");
        a2.setDescription("A game-ready stylized nature pack.");
        a2.setPrice(14.99);
        a2.setFileType("GLB");
        a2.setThumbnailUrl("/images/banana.png");
        a2.setSketchfabUid("a5eb5c78e5a14955802e7eb64b76e1a1");
        a2.setCreatorName("NatureArtist");
        a2.setCreatorAvatarUrl("/images/banana.png");
        a2.setTags(List.of("Nature", "Stylized", "Game-Ready"));
        a2.setFormats(List.of(".glb", ".fbx"));
        a2.setLicense(commercial);
        a2.setRating(4.5);
        a2.setPublishedDate("March 3, 2026");
        a2.setLastUpdateDate("March 12, 2026");
        a2.setAgeRating("Not Mature");
        a2.setAllowsAiUsage(false);
        assets.add(a2);
    }
}
