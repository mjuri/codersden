package uk.codersden.hr.profiles;

import org.springframework.web.multipart.MultipartFile;

public interface StorageService {
    /** Returns a public URL */
    String save(String folder, String ownerId, MultipartFile file);
}
