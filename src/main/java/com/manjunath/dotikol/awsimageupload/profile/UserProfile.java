package com.manjunath.dotikol.awsimageupload.profile;

import java.util.Objects;
import java.util.UUID;

public class UserProfile {

    private UUID uuid;
    private String name;
    private String userProfileLink; //S3 link

    public UserProfile(UUID uuid, String name, String userProfileLink) {
        this.uuid = uuid;
        this.name = name;
        this.userProfileLink = userProfileLink;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserProfileLink() {
        return userProfileLink;
    }

    public void setUserProfileLink(String userProfileLink) {
        this.userProfileLink = userProfileLink;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserProfile that = (UserProfile) o;
        return Objects.equals(uuid, that.uuid) &&
                Objects.equals(name, that.name) &&
                Objects.equals(userProfileLink, that.userProfileLink);
    }

    @Override
    public int hashCode() {

        return Objects.hash(uuid, name, userProfileLink);
    }
}
