package com.manjunath.dotikol.awsimageupload.datastore;

import com.manjunath.dotikol.awsimageupload.profile.UserProfile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class FakeUserProfileDataStore {
    public static final List<UserProfile> userProfileList = new ArrayList<>();
    static {
        userProfileList.add(new UserProfile(UUID.randomUUID(),"Ram",null));
        userProfileList.add(new UserProfile(UUID.randomUUID(),"Sita",null));
    }

    public List<UserProfile> getUserProfiles(){
        return userProfileList;
    }
}
