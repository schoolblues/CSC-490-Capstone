package com.backend.CreativityMarket.User;

import java.util.List;

public class Asset {
    private Long id;
    private String title;
    private String description;
    private double price;
    private String thumbnailUrl;
    private List<String> thumbnailUrls;
    private String sketchfabUid;
    private String creatorName;
    private String creatorAvatarUrl;
    private List<String> tags;
    private List<String> formats;
    private List<License> licenses;
    private double rating;
    private String publishedDate;
    private String lastUpdateDate;
    private String ageRating;
    private boolean allowsAiUsage;

    public Asset() {}

    public Asset(Long id, String title, double price, String thumbnailUrl, License license) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.thumbnailUrl = thumbnailUrl;
        this.licenses = List.of(license);
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public String getThumbnailUrl() { return thumbnailUrl; }
    public void setThumbnailUrl(String thumbnailUrl) { this.thumbnailUrl = thumbnailUrl; }

    public List<String> getThumbnailUrls() { return thumbnailUrls; }
    public void setThumbnailUrls(List<String> thumbnailUrls) { this.thumbnailUrls = thumbnailUrls; }

    public String getSketchfabUid() { return sketchfabUid; }
    public void setSketchfabUid(String sketchfabUid) { this.sketchfabUid = sketchfabUid; }

    public String getCreatorName() { return creatorName; }
    public void setCreatorName(String creatorName) { this.creatorName = creatorName; }

    public String getCreatorAvatarUrl() { return creatorAvatarUrl; }
    public void setCreatorAvatarUrl(String creatorAvatarUrl) { this.creatorAvatarUrl = creatorAvatarUrl; }

    public List<String> getTags() { return tags; }
    public void setTags(List<String> tags) { this.tags = tags; }

    public List<String> getFormats() { return formats; }
    public void setFormats(List<String> formats) { this.formats = formats; }

    public List<License> getLicenses() { return licenses; }
    public void setLicenses(List<License> licenses) { this.licenses = licenses; }

    public double getRating() { return rating; }
    public void setRating(double rating) { this.rating = rating; }

    public String getPublishedDate() { return publishedDate; }
    public void setPublishedDate(String publishedDate) { this.publishedDate = publishedDate; }

    public String getLastUpdateDate() { return lastUpdateDate; }
    public void setLastUpdateDate(String lastUpdateDate) { this.lastUpdateDate = lastUpdateDate; }

    public String getAgeRating() { return ageRating; }
    public void setAgeRating(String ageRating) { this.ageRating = ageRating; }

    public boolean isAllowsAiUsage() { return allowsAiUsage; }
    public void setAllowsAiUsage(boolean allowsAiUsage) { this.allowsAiUsage = allowsAiUsage; }

    public String getFormattedPrice() {
        return String.format("$%.2f", price);
    }
}
