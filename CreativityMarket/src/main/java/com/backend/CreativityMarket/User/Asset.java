package com.backend.CreativityMarket.User;

public class Asset {
    private Long id;
    private String title;
    private double price;
    private String fileType;
    private License license;

    public Asset(Long id, String title, double price, String fileType, License license) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.fileType = fileType;
        this.license = license;
    }

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public double getPrice() { return price; }
    public String getFileType() { return fileType; }
    public License getLicense() { return license; }
}
