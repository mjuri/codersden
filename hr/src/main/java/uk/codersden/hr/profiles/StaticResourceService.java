package uk.codersden.hr.profiles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

@Service
public class StaticResourceService {

	@Autowired
    private final ResourceLoader resourceLoader;

	
    public StaticResourceService(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }
    
    public String getStaticDirectoryFilesPath() {
        try {
            // Load the "static" directory as a resource
        	Resource folderResource = resourceLoader.getResource("classpath:static");
        	System.out.println(folderResource.getFilename());
            return folderResource.getFilename() + "/files";
        } catch (Exception e) {
            // Handle the exception
            e.printStackTrace();
            return null;
        }
    }
    public String getStaticDirectoryPath(String subfolder) {
        try {
            // Load the "static" directory as a resource
        	Resource folderResource = resourceLoader.getResource("classpath:static");
        	System.out.println(folderResource.getFilename());
            return folderResource.getFilename() + "/" + subfolder;
        } catch (Exception e) {
            // Handle the exception
            e.printStackTrace();
            return null;
        }
    }
    public String getStaticDirectoryAvatarsPath() {
        try {
            // Load the "static" directory as a resource
        	Resource folderResource = resourceLoader.getResource("classpath:static");
        	System.out.println(folderResource.getFilename());
            return folderResource.getFilename() + "/avatars";
        } catch (Exception e) {
            // Handle the exception
            e.printStackTrace();
            return null;
        }
    }
}
