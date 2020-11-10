package com.manjunath.dotikol.awsimageupload.profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/user-profile")
@CrossOrigin("*")
public class UserProfileController {

    @Autowired
    private UserProfileService userProfileService;

    @GetMapping
    public @ResponseBody
    List<UserProfile> getUserProfiles() {
        return userProfileService.getUserProfiles();
    }

    @PostMapping(
            path = "{uuid}/image/upload",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public void uploadImageProfie(@PathVariable("uuid") UUID uuid,
                                  @RequestParam("file") MultipartFile file) {
        userProfileService.uploadImageProfie(uuid, file);
    }
}
