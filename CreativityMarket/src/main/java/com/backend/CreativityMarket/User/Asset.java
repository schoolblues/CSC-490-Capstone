package com.backend.CreativityMarket.User;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.*;

@Entity
@Table(name = "assets")
public class Asset {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    private double price;

    private String fileType;
    private String category;
    private String tags;
    private String license;

    private String thumbnailUrl;
    private String sketchfabUid;
    private String creatorName;
    private String creatorAvatarUrl;

    private Integer polyCount;
    private Integer vertices;
    private Integer polygons;
    private String geometry;
    private String uvMapping;
    private boolean rigged;
    private boolean animated;

    private String texturesIncluded;
    private String textureResolution;
    private String materials;

    private double rating;
    private boolean allowsAiUsage;

    @ManyToOne
    @JoinColumn(name = "category_id")
    @JsonIgnore
    private com.backend.CreativityMarket.Marketplace.Category categoryEntity;

    @ManyToOne(optional = true)
    @JoinColumn(name = "creator_id")
    @JsonIgnore
    private User creator;

    public Asset() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public String getFileType() { return fileType; }
    public void setFileType(String fileType) { this.fileType = fileType; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getTags() { return tags; }
    public void setTags(String tags) { this.tags = tags; }

    public String getLicense() { return license; }
    public void setLicense(String license) { this.license = license; }

    public String getThumbnailUrl() { return thumbnailUrl; }
    public void setThumbnailUrl(String thumbnailUrl) { this.thumbnailUrl = thumbnailUrl; }

    public String getSketchfabUid() { return sketchfabUid; }
    public void setSketchfabUid(String sketchfabUid) { this.sketchfabUid = sketchfabUid; }

    public String getCreatorName() { return creatorName; }
    public void setCreatorName(String creatorName) { this.creatorName = creatorName; }

    public String getCreatorAvatarUrl() { return creatorAvatarUrl; }
    public void setCreatorAvatarUrl(String creatorAvatarUrl) { this.creatorAvatarUrl = creatorAvatarUrl; }

    public Integer getPolyCount() { return polyCount; }
    public void setPolyCount(Integer polyCount) { this.polyCount = polyCount; }

    public Integer getVertices() { return vertices; }
    public void setVertices(Integer vertices) { this.vertices = vertices; }

    public Integer getPolygons() { return polygons; }
    public void setPolygons(Integer polygons) { this.polygons = polygons; }

    public String getGeometry() { return geometry; }
    public void setGeometry(String geometry) { this.geometry = geometry; }

    public String getUvMapping() { return uvMapping; }
    public void setUvMapping(String uvMapping) { this.uvMapping = uvMapping; }

    public boolean isRigged() { return rigged; }
    public void setRigged(boolean rigged) { this.rigged = rigged; }

    public boolean isAnimated() { return animated; }
    public void setAnimated(boolean animated) { this.animated = animated; }

    public String getTexturesIncluded() { return texturesIncluded; }
    public void setTexturesIncluded(String texturesIncluded) { this.texturesIncluded = texturesIncluded; }

    public String getTextureResolution() { return textureResolution; }
    public void setTextureResolution(String textureResolution) { this.textureResolution = textureResolution; }

    public String getMaterials() { return materials; }
    public void setMaterials(String materials) { this.materials = materials; }

    public double getRating() { return rating; }
    public void setRating(double rating) { this.rating = rating; }

    public boolean isAllowsAiUsage() { return allowsAiUsage; }
    public void setAllowsAiUsage(boolean allowsAiUsage) { this.allowsAiUsage = allowsAiUsage; }

    public com.backend.CreativityMarket.Marketplace.Category getCategoryEntity() { return categoryEntity; }
    public void setCategoryEntity(com.backend.CreativityMarket.Marketplace.Category categoryEntity) { this.categoryEntity = categoryEntity; }

    public User getCreator() { return creator; }
    public void setCreator(User creator) { this.creator = creator; }

    @JsonProperty("creatorUserId")
    public Long getCreatorUserId() { return creator != null ? creator.getId() : null; }

    @JsonProperty("categoryId")
    public Long getCategoryId() {
        return categoryEntity != null ? categoryEntity.getId() : null;
    }

    public String getFormattedPrice() {
        return String.format("$%.2f", price);
    }
}