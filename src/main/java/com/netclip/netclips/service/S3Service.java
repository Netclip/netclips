package com.netclip.netclips.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import liquibase.pro.packaged.P;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class S3Service {

    private final Logger log = LoggerFactory.getLogger(S3Service.class);

    @Value("${aws.bucket.name}")
    private String bucketName;

    @Autowired
    private AmazonS3 s3Client;


    public List<Bucket> getAllBuckets() {
        return s3Client.listBuckets();
    }


    public List<String> getAllFileKeys() {
        return s3Client.listObjectsV2(bucketName).getObjectSummaries().stream()
            .map(S3ObjectSummary::getKey)
            .collect(Collectors.toList());
    }

    public void uploadFile(MultipartFile multipartFile) {
        String uniqueName = generateUniqueFileName(multipartFile);
        try {
            File file = multiPartToFile(multipartFile);
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, uniqueName, file);
            log.info("Uploading file: " + uniqueName);
            s3Client.putObject(putObjectRequest);
            log.info("Upload complete: " + uniqueName);
            file.delete();
        } catch (Exception e){
            e.printStackTrace();
        }

    }

    private String generateUniqueFileName(MultipartFile multipartFile) {
        return multipartFile.getOriginalFilename().replace(" ", "_")
            + "-" + new Date().getTime();
    }

    private File multiPartToFile(MultipartFile multiFile) {
        File resFile = new File(Objects.requireNonNull(multiFile.getOriginalFilename()));
        try {
            FileOutputStream outStream = new FileOutputStream(resFile);
            outStream.write(multiFile.getBytes());
            outStream.close();
        } catch (IOException e) {
            log.error("Could not convert multipart file to File: " + e.getMessage());
        }
        return resFile;
    }

    public void deleteFile(String fileKey) {
        s3Client.deleteObject(bucketName, fileKey);
    }
}
