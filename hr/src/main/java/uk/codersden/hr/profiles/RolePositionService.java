package uk.codersden.hr.profiles;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class RolePositionService {
	
	@Autowired
	private RolePositionDao rolePositionDao;
	
	@Autowired
	private ProfileDao profileDao;
	
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

    public RolePosition saveRolePosition(RolePosition rolePosition) throws ProfileNotFoundException {
        validateRolePosition(rolePosition);

        return rolePositionDao.save(rolePosition);
    }

	public List<RolePosition> findAllRolePositionsForUser(String identifier) {
		String assignedIdentifier = identifier;
		String requestorIdentifier = identifier;
		return this.rolePositionDao.findByAssignedIdentifierOrRequestorIdentifier(assignedIdentifier, requestorIdentifier);

		
	}
	
}
