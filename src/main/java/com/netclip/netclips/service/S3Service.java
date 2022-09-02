package com.netclip.netclips.service;

import com.amazonaws.HttpMethod;
import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.netclip.netclips.domain.Video;
import com.netclip.netclips.service.dto.UploadDTO;
import com.netclip.netclips.service.mapper.VideoMapper;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import liquibase.pro.packaged.P;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@PropertySource("classpath:aws.properties")
public class S3Service {

    private final Logger log = LoggerFactory.getLogger(S3Service.class);

    private final String prefix = "netclips/";

    @Value("${bucketName}")
    private String bucketName;

    @Value("${bucketUrl}")
    private String bucketUrl;

    @Autowired
    private final AmazonS3 s3Client;

    @Autowired
    private final VideoMapper videoMapper;

    public S3Service(AmazonS3 s3client, VideoMapper videoMapper) {
        this.s3Client = s3client;
        this.videoMapper = videoMapper;
    }

    public VideoMapper getMapper() {
        return this.videoMapper;
    }

    public Video convertUploadDTOtoVideo(UploadDTO uploadDTO) {
        return videoMapper.videoUploadDTOToVideo(uploadDTO);
    }

    public String getPrefix() {
        return this.prefix;
    }

    public List<Bucket> getAllBuckets() {
        return s3Client.listBuckets();
    }

    public List<String> getAllFileKeys() {
        return s3Client.listObjectsV2(bucketName).getObjectSummaries().stream().map(S3ObjectSummary::getKey).collect(Collectors.toList());
    }

    public String generatePresignedUrl(String fileKey) {
        Date expireTime = new Date();
        long expMs = Instant.now().toEpochMilli();
        expMs += 1000 * 60 * 60;
        expireTime.setTime(expMs);

        try {
            GeneratePresignedUrlRequest generatePresignedUrlRequest = new GeneratePresignedUrlRequest(bucketName, fileKey)
                .withMethod(HttpMethod.GET)
                .withExpiration(expireTime);
            URL url = s3Client.generatePresignedUrl(generatePresignedUrlRequest);
            return url.toString();
        } catch (SdkClientException e) {
            e.printStackTrace();
        }
        return "";
    }

    public String uploadFile(MultipartFile multipartFile) {
        String uniqueName = generateUniqueFileName(multipartFile);
        String fileKey = "";
        try {
            File file = multiPartToFile(multipartFile);
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, uniqueName, file);
            log.info("Uploading file: " + uniqueName);
            s3Client.putObject(putObjectRequest);
            log.info("Upload complete: " + uniqueName);
            fileKey = uniqueName;
            file.delete();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return fileKey;
    }

    private String generateUniqueFileName(MultipartFile multipartFile) {
        return prefix + new Date().getTime() + "-" + Objects.requireNonNull(multipartFile.getOriginalFilename()).replace(" ", "_");
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
        s3Client.deleteObject(bucketName, prefix + fileKey);
    }

    public void deleteFileByFullKey(String fullFileKey) {
        s3Client.deleteObject(bucketName, fullFileKey);
    }
}
