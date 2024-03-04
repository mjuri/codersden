package uk.codersden.hr.profiles;


import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import uk.codersden.hr.profiles.documents.Document;
import uk.codersden.hr.profiles.documents.DocumentStatus;

@Service
public class RolePositionService {
	
	@Autowired
	private RolePositionDao rolePositionDao;
	
	@Autowired
	private ProfileDao profileDao;
	
    @Autowired
    private StaticResourceService resourceService;
	
	public Optional<RolePosition> findRolePositionByIdentifier(String identifier) {
		return this.rolePositionDao.findById(identifier);
	}
	
    private void validateRolePosition(RolePosition rolePosition) throws ProfileNotFoundException {
        Profile requestor = profileDao.findById(rolePosition.getRequestorIdentifier())
                .orElseThrow(ProfileNotFoundException::new);
        rolePosition.setRequestedBy(requestor);

        if (rolePosition.getAssignedIdentifier() != null) {
            Profile approver = profileDao.findById(rolePosition.getAssignedIdentifier())
                    .orElseThrow(ProfileNotFoundException::new);
            rolePosition.setAssigned(approver);
        }
    }
    public RolePosition saveRolePosition(RolePosition rolePosition, MultipartFile file) throws ProfileNotFoundException {
    	
    	if(null == rolePosition.getIdentifier()) {
    		UUID uuid = UUID.randomUUID();
    		rolePosition.setIdentifier(uuid.toString());
    	}	

		
		Path pathFolder = Paths.get(resourceService.getStaticDirectoryPath("files") + '/' + rolePosition.getIdentifier());
		

		try {
			Files.createDirectories(pathFolder);
			Files.copy(file.getInputStream(), pathFolder.resolve(file.getOriginalFilename()),StandardCopyOption.REPLACE_EXISTING);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
		}

		String fileName = pathFolder.resolve(file.getOriginalFilename()).toString();
		fileName = fileName.replaceAll("static", "");
		rolePosition.setFile(fileName);
    	
    	
    	
        validateRolePosition(rolePosition);
        if(rolePosition.getLog() == null) {
        	String log = rolePosition.getStatus().toUpperCase() + " by " + rolePosition.getRequestedBy().getEmail();
        	rolePosition.setLog(log);
        }
        
        return rolePositionDao.save(rolePosition);
    }
    public RolePosition saveRolePosition(RolePosition rolePosition) throws ProfileNotFoundException {
        validateRolePosition(rolePosition);
        if(rolePosition.getLog() == null) {
        	String log = rolePosition.getStatus().toUpperCase() + " by " + rolePosition.getRequestedBy().getEmail();
        	rolePosition.setLog(log);
        }
        
        return rolePositionDao.save(rolePosition);
    }

	public List<RolePosition> findAllRolePositionsForUser(String identifier) {
		String assignedIdentifier = identifier;
		String requestorIdentifier = identifier;
		return this.rolePositionDao.findByAssignedIdentifier(assignedIdentifier);

		
	}

	public RolePosition archiveRolePosition(String identifier) {

		Optional<RolePosition> op = rolePositionDao.findById(identifier);
		RolePosition role = op.get();
		role.setStatus("ARCHIVED");
		return rolePositionDao.save(role);

	}
	
}
