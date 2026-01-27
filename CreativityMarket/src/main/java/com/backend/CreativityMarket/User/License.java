package com.backend.CreativityMarket.User;

public class License {
    private String type;
    private String summary;

    public License(String type, String summary) {
        this.type = type;
        this.summary = summary;
    }

    public String getType() { return type; }
    public String getSummary() { return summary; }
}
