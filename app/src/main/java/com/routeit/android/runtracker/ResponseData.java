package com.raywenderlich.android.runtracker;

public class ResponseData {
    private TagLocation tagLocation;
    private String assetType;
    private String assetID;
    private String tagID;

    public ResponseData(TagLocation tagLocation, String assetType, String assetID, String tagID) {
        this.tagLocation = tagLocation;
        this.assetType = assetType;
        this.assetID = assetID;
        this.tagID = tagID;
    }

    public TagLocation getTagLocation() {
        return tagLocation;
    }

    public void setTagLocation(TagLocation tagLocation) {
        this.tagLocation = tagLocation;
    }

    public String getAssetType() {
        return assetType;
    }

    public void setAssetType(String assetType) {
        this.assetType = assetType;
    }

    public String getAssetID() {
        return assetID;
    }

    public void setAssetID(String assetID) {
        this.assetID = assetID;
    }

    public String getTagID() {
        return tagID;
    }

    public void setTagID(String tagID) {
        this.tagID = tagID;
    }
}
