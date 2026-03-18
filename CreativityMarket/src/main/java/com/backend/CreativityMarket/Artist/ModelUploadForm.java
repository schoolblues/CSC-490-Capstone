package com.backend.CreativityMarket.Artist;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class ModelUploadForm {

    private MultipartFile modelFile;
    private String fileType;

    private String title;
    private String description;
    private String category;
    private String tags;

    private List<MultipartFile> thumbnails;

    private String embedUrl;
    private String sketchfabUid;

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
    private List<MultipartFile> textureFiles;

    private double price;
    private boolean free;
    private String license;
    private String licenseNotes;

    public ModelUploadForm() {}

    public MultipartFile getModelFile() { return modelFile; }
    public void setModelFile(MultipartFile modelFile) { this.modelFile = modelFile; }

    public String getFileType() { return fileType; }
    public void setFileType(String fileType) { this.fileType = fileType; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getTags() { return tags; }
    public void setTags(String tags) { this.tags = tags; }

    public List<MultipartFile> getThumbnails() { return thumbnails; }
    public void setThumbnails(List<MultipartFile> thumbnails) { this.thumbnails = thumbnails; }

    public String getEmbedUrl() { return embedUrl; }
    public void setEmbedUrl(String embedUrl) { this.embedUrl = embedUrl; }

    public String getSketchfabUid() { return sketchfabUid; }
    public void setSketchfabUid(String sketchfabUid) { this.sketchfabUid = sketchfabUid; }

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

    public List<MultipartFile> getTextureFiles() { return textureFiles; }
    public void setTextureFiles(List<MultipartFile> textureFiles) { this.textureFiles = textureFiles; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public boolean isFree() { return free; }
    public void setFree(boolean free) { this.free = free; }

    public String getLicense() { return license; }
    public void setLicense(String license) { this.license = license; }

    public String getLicenseNotes() { return licenseNotes; }
    public void setLicenseNotes(String licenseNotes) { this.licenseNotes = licenseNotes; }
}
