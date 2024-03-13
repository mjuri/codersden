package uk.codersden.hr.profiles;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import uk.codersden.hr.profiles.documents.Document;
import uk.codersden.hr.profiles.documents.DocumentPayload;

@RestController
@RequestMapping("/roleposition")
public class RolePositionController {
	@Autowired
	private RolePositionService rolePositionService;
	
	
	@CrossOrigin
	@GetMapping("/{identifier}")
	public ResponseEntity<?> retrieveRolePosition(@PathVariable("identifier") String identifier){
        Optional<RolePosition> rolePosition = rolePositionService.findRolePositionByIdentifier(identifier);
        return rolePosition.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
		
	}
    @CrossOrigin
    @PostMapping
    public ResponseEntity<RolePosition> saveRolePosition(@RequestBody RolePosition rolePosition) throws ProfileNotFoundException {
        RolePosition savedRolePosition = rolePositionService.saveRolePosition(rolePosition);
        return ResponseEntity.ok(savedRolePosition);
    }
    @CrossOrigin
    @PostMapping("/pdf")
	public ResponseEntity<RolePosition> saveRolePosition(@RequestParam("payload") String rolePayload,
			@RequestParam("fileName") String fileName, @RequestParam("file") MultipartFile file)
			throws ProfileNotFoundException, JsonMappingException, JsonProcessingException {
    	System.out.println(rolePayload);
		ObjectMapper mapper = new ObjectMapper();
	    RolePosition rolePosition = mapper.readValue(rolePayload, RolePosition.class);
    	RolePosition savedRolePosition = rolePositionService.saveRolePosition(rolePosition, file);
    	
        return ResponseEntity.ok(savedRolePosition);
    }
    @CrossOrigin
    @PutMapping("/{identifier}/pdf")
	public ResponseEntity<RolePosition> updateRolePosition(@PathVariable("identifier") String identifier, @RequestParam("payload") String rolePayload,
			@RequestParam("fileName") String fileName, @RequestParam("file") MultipartFile file)
			throws ProfileNotFoundException, JsonMappingException, JsonProcessingException {
        Optional<RolePosition> op = rolePositionService.findRolePositionByIdentifier(identifier);
        if (op.isEmpty() ) {
            return ResponseEntity.notFound().build();
        }
        
		ObjectMapper mapper = new ObjectMapper();
	    RolePosition updatedRolePosition = mapper.readValue(rolePayload, RolePosition.class);
        RolePosition role = op.get();
        
        role.setGrade(updatedRolePosition.getGrade());
        role.setSalaryLevel(updatedRolePosition.getSalaryLevel());
        role.setJobDescription(updatedRolePosition.getJobDescription());
        role.setContractType(updatedRolePosition.getContractType());
        role.setStartDate(updatedRolePosition.getStartDate());
        role.setHeader(updatedRolePosition.getHeader());
        role.setStatus(updatedRolePosition.getStatus());
        role.setFileName(updatedRolePosition.getFileName());
        
    	RolePosition savedRolePosition = rolePositionService.saveRolePosition(role, file);
    	
        return ResponseEntity.ok(savedRolePosition);
    }
    @CrossOrigin
    @DeleteMapping("/{identifier}")
    public ResponseEntity<?> archiveRolePosition(@PathVariable("identifier") String identifier){
    	
		RolePosition rolePosition = null;
		try {
			rolePosition = rolePositionService.archiveRolePosition(identifier);
		}catch(Exception e) {
			e.printStackTrace();
		    return ResponseEntity.internalServerError().build();
		}

		return ResponseEntity.ok(rolePosition);   	
    }
    
    @CrossOrigin
    @GetMapping("/profile/{identifier}")
    public ResponseEntity<?> retrieveAllRolePositionsForUser(@PathVariable("identifier") String identifier){
    	List<RolePosition> roles = null;
    	try{
    		roles = rolePositionService.findAllRolePositionsForUser(identifier);
    	}catch(Exception e) {
    		return ResponseEntity.internalServerError().build();
    	}
    	return ResponseEntity.ok(roles);
    	
    }
    @CrossOrigin
    @PostMapping("/{identifier}/status")
    public ResponseEntity<RolePosition> updateRolePositionStatus(@PathVariable("identifier") String identifier, 
            @RequestBody RolePositionStatusLog statusLog) throws ProfileNotFoundException {
        Optional<RolePosition> op = rolePositionService.findRolePositionByIdentifier(identifier);
        if (op.isEmpty() ) {
            return ResponseEntity.notFound().build();
        }
        RolePosition role = op.get();
        if(!role.getStatus().equalsIgnoreCase(statusLog.getStatus())) {
        	String log = new String();
        	log = role.getLog() + System.lineSeparator();
        	log += statusLog.getStatus() + " on " + new Date() + " " + statusLog.getLog();
        	role.setLog(log);
        	role.setStatus(statusLog.getStatus());
        }
    	
        RolePosition newRolePosition = rolePositionService.saveRolePosition(role);
        return ResponseEntity.ok(newRolePosition);
    	
    }
    @CrossOrigin
    @PutMapping("/{identifier}")
    public ResponseEntity<RolePosition> updateRolePosition(@PathVariable("identifier") String identifier, 
                                                           @RequestBody RolePosition updatedRolePosition) throws ProfileNotFoundException {
        Optional<RolePosition> op = rolePositionService.findRolePositionByIdentifier(identifier);
        if (op.isEmpty() ) {
            return ResponseEntity.notFound().build();
        }
        RolePosition role = op.get();
        
        role.setGrade(updatedRolePosition.getGrade());
        role.setSalaryLevel(updatedRolePosition.getSalaryLevel());
        role.setJobDescription(updatedRolePosition.getJobDescription());
        role.setContractType(updatedRolePosition.getContractType());
        role.setStartDate(updatedRolePosition.getStartDate());
        role.setHeader(updatedRolePosition.getHeader());
        role.setStatus(updatedRolePosition.getStatus());

        updatedRolePosition = rolePositionService.saveRolePosition(role);
        return ResponseEntity.ok(updatedRolePosition);
    }
}
