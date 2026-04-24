package com.backend.CreativityMarket.User;

import java.util.List;
//TODO: figure out naming convention as we have two categories, this could possibly be changed to CategoryDTO.
public class Category {
    private Long id;
    private String name;
    private String subtitle;
    private String mainImageUrl;
    private String mainImageUid;
    private List<String> previewImageUrls;
    private List<String> previewImageUids;

    public Category() {}

    public Category(Long id, String name, String subtitle,
                    String mainImageUrl, String mainImageUid,
                    List<String> previewImageUrls, List<String> previewImageUids) {
        this.id = id;
        this.name = name;
        this.subtitle = subtitle;
        this.mainImageUrl = mainImageUrl;
        this.mainImageUid = mainImageUid;
        this.previewImageUrls = previewImageUrls;
        this.previewImageUids = previewImageUids;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getSubtitle() { return subtitle; }
    public void setSubtitle(String subtitle) { this.subtitle = subtitle; }

    public String getMainImageUrl() { return mainImageUrl; }
    public void setMainImageUrl(String mainImageUrl) { this.mainImageUrl = mainImageUrl; }

    public String getMainImageUid() { return mainImageUid; }
    public void setMainImageUid(String mainImageUid) { this.mainImageUid = mainImageUid; }

    public List<String> getPreviewImageUrls() { return previewImageUrls; }
    public void setPreviewImageUrls(List<String> previewImageUrls) { this.previewImageUrls = previewImageUrls; }

    public List<String> getPreviewImageUids() { return previewImageUids; }
    public void setPreviewImageUids(List<String> previewImageUids) { this.previewImageUids = previewImageUids; }
}
