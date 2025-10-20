package uk.codersden.hr.profiles;

import org.springframework.web.multipart.MultipartFile;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.net.URI;
import java.util.Map;
import java.util.UUID;

import javax.annotation.PostConstruct;

@Service
public class R2StorageService implements StorageService {

    @Value("${bst.r2.bucket}") private String bucket;
    @Value("${bst.r2.account}") private String accountId;
    @Value("${bst.r2.accessKey}") private String accessKey;
    @Value("${bst.r2.secretKey}") private String secretKey;
    @Value("${bst.r2.publicDomain}") private String publicDomain;

    private S3Client s3;

    @PostConstruct
    void init() {
        s3 = S3Client.builder()
                .endpointOverride(URI.create("https://" + accountId + ".r2.cloudflarestorage.com"))
                .region(Region.of("auto"))
                .credentialsProvider(StaticCredentialsProvider.create(
                        AwsBasicCredentials.create(accessKey, secretKey)))
                .build();
    }

    @Override
    public String save(String folder, String ownerId, MultipartFile file) {
        try {
            String ext = guessExtension(file.getContentType(), file.getOriginalFilename());
            String filename = UUID.randomUUID() + (ext.isEmpty() ? "" : "." + ext);
            String key = (folder + "/" + ownerId + "/" + filename).replaceAll("//+", "/");

            s3.putObject(
                    PutObjectRequest.builder()
                            .bucket(bucket)
                            .key(key)
                            .contentType(file.getContentType() == null ? "application/octet-stream" : file.getContentType())
                            .build(),
                    RequestBody.fromBytes(file.getBytes())
            );

            // Public URL (bucketless style for R2 public domain)
            return publicDomain + "/" + key;
        } catch (Exception e) {
            throw new RuntimeException("Upload to R2 failed: " + e.getMessage(), e);
        }
    }

    private String guessExtension(String contentType, String originalName) {
        if (originalName != null && originalName.contains(".")) {
            String ext = originalName.substring(originalName.lastIndexOf('.') + 1).toLowerCase();
            if (!ext.isBlank()) return ext;
        }
        if (contentType == null) return "";
        Map<String,String> map = Map.of(
                "image/jpeg","jpg","image/png","png","image/webp","webp",
                "application/pdf","pdf","image/gif","gif"
        );
        return map.getOrDefault(contentType.toLowerCase(), "");
    }
}

