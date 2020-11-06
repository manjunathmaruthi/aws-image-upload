package com.manjunath.dotikol.awsimageupload.filestore;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.Map;
import java.util.Optional;

@Service
public class FileStoreService {

    @Autowired
    private AmazonS3 amazonS3;

    public void uploadImage(String path, String imageName,
                            Optional<Map<String, String>> optinalMetaData, InputStream inputStream) {

        ObjectMetadata metaData = new ObjectMetadata();
        optinalMetaData.ifPresent(map -> {
            if (!map.isEmpty()) {
                map.forEach(metaData::addUserMetadata);
            }
        });

        try {
            //Uploads file to Amazon s3
            amazonS3.putObject(path, imageName, inputStream, metaData);
        } catch (AmazonServiceException e) {
            throw new IllegalStateException("Failed to upload file in Amazon S3",e);
        }

    }
}
