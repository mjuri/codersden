package uk.codersden.hr.groups;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupDao extends JpaRepository<Group, String> {

	public List<Group> findAllByAccountIdentifier(String accountIdentifier);
}
