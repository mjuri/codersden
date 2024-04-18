package uk.codersden.hr.groups;

import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uk.codersden.hr.groups.Group;
import uk.codersden.hr.groups.GroupNotFoundException;
import uk.codersden.hr.profiles.Profile;
import uk.codersden.hr.profiles.ProfileDao;
import uk.codersden.hr.profiles.ProfileNotFoundException;

@Service
public class GroupService {
	@Autowired
	private GroupDao dao;
	
	@Autowired
	private ProfileDao profileDao;
	
	@Autowired
	private ModelMapper	 modelMapper;
	
	public Group createGroup(Group group) {
		// Check audience
		List<Map<String, String>> audienceValues = group.getMembersValues();
		Profile profile;
		Set<Profile> members = new HashSet<>();
		
		for (Map<String, String> map : audienceValues) {
			try {
				Optional<Profile> p = this.profileDao.findById(map.get("value"));
				if (p.isEmpty()) {
					throw new ProfileNotFoundException();
				}
				profile = p.get();
				members.add(profile);
				
			} catch (ProfileNotFoundException e) {
				System.out.println(e);
			}
		}
		group.setMembers(members);
		
		return this.dao.save(group);
	}
	
	public List<Group> findAllGroupsByAccountIdentifier(String accountIdentifier){
		
		return this.dao.findAllByAccountIdentifier(accountIdentifier);
	}

	public Group findGroupByIdentifier(String identifier) throws ProfileNotFoundException {
		Optional<Group> op = dao.findById(identifier);

		if(op.isEmpty()) {
			throw new ProfileNotFoundException();
		}
		return op.get();
		
	}

	public Group updateGroup(String groupIdentifier, Group group) throws GroupNotFoundException {
		Optional<Group> optional = dao.findById(groupIdentifier);
		if(optional.isEmpty()) {
			throw new GroupNotFoundException();
		}
		Group existingGroup = optional.get();

		
		Set<Profile> updatedMembers = new HashSet<Profile>();
		List<Map<String, String>> membersValues = group.getMembersValues();
		Profile profile;
		for (Map<String, String> map : membersValues) {
			try {
				Optional<Profile> p = this.profileDao.findById(map.get("value"));
				if (p.isEmpty()) {
					throw new ProfileNotFoundException();
				}
				profile = p.get();
				if(!existingGroup.getMembers().contains(profile)) {
					group.addMember(profile);
				}	
				updatedMembers.add(profile);
				
			} catch (ProfileNotFoundException e) {
				System.out.println(e);
			}
		}
		//Set<Profile> updatedList = existingGroup.getMembers();
		try {
			for(Profile p: existingGroup.getMembers()) {
				if(!updatedMembers.contains(p)) {
					existingGroup.getMembers().remove(p);
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		modelMapper.getConfiguration().setAmbiguityIgnored(true);


		
		modelMapper.map(group, existingGroup); // To copy all the attributes from group to the existing one.
		Group newGroup = this.dao.save(existingGroup);
		
		return newGroup;
	}
}
