package com.davideferrara.jx.dto;

public class ProfileAddNewPostRequest {
    private long profileId;
    private String description;

    // Costruttore di default necessario per l'uso con Spring!
    public ProfileAddNewPostRequest() {}

    public ProfileAddNewPostRequest(long profileId, String description) {
        this.profileId = profileId;
        this.description = description;
    }

    public long getProfileId() {
        return profileId;
    }

    public void setProfileId(long profileId) {
        this.profileId = profileId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
