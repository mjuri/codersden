package uk.codersden.hr.profiles;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @GetMapping("/profile/{identifier}")
    public ResponseEntity<List<RolePosition>> retrieveAllRolePositionsForUser(@PathVariable("identifier") String identifier){
    	List<RolePosition> roles = rolePositionService.findAllRolePositionsForUser(identifier);
    	return ResponseEntity.ok(roles);
    	
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
