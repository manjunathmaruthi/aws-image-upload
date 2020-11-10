package com.manjunath.dotikol.awsimageupload.profile;

import com.manjunath.dotikol.awsimageupload.bucket.BucketName;
import com.manjunath.dotikol.awsimageupload.datastore.FakeUserProfileDataStore;
import com.manjunath.dotikol.awsimageupload.filestore.FileStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

import static org.apache.http.entity.ContentType.*;

@Service
public class UserProfileService {


    private FakeUserProfileDataStore fakeUserProfileDataStore;

    private final FileStoreService fileStoreService;

    @Autowired
    public UserProfileService(FileStoreService fileStoreService,
                              FakeUserProfileDataStore fakeUserProfileDataStore) {
        this.fakeUserProfileDataStore = fakeUserProfileDataStore;
        this.fileStoreService = fileStoreService;
    }

    public List<UserProfile> getUserProfiles() {
        return fakeUserProfileDataStore.getUserProfiles();
    }

    public void uploadImageProfie(UUID userProfieId, MultipartFile file) {
        //check if image is not empty
        if (file.isEmpty()) {
            throw new IllegalStateException("File is empty");
        }
        //if file is an image
        if (!Arrays.asList(
                IMAGE_JPEG.getMimeType(),
                IMAGE_PNG.getMimeType(),
                IMAGE_GIF.getMimeType()
        )
                .contains(file.getContentType())) {
            throw new IllegalStateException(("Invalid Image"));
        }
        //The user exists in our database
      /* UserProfile user = fakeUserProfileDataStore.getUserProfiles()
                .stream()
                .filter(userProfile -> userProfile.getUuid().equals(userProfieId))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(String.format("User %s not found",userProfieId)));*/

        //Grab some metadata from file if any
        Map<String,String> metadata = new HashMap<>();
        metadata.put("Content-Type",file.getContentType());
        metadata.put("Content-Length", String.valueOf(file.getSize()));

        //Store the image in s3 and update database with s3 image link
        String path = String.format("%s/%s", BucketName.PROFILE_IMAGE.getBucketName(),userProfieId);
        String fileName = String.format("%s-%s",file.getName(),UUID.randomUUID());
        try {
            fileStoreService.uploadImage(path,fileName,Optional.of(metadata), file.getInputStream());
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
}
