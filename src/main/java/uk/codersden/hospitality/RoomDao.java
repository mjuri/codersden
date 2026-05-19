package uk.codersden.hospitality;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


public interface RoomDao extends JpaRepository<Room, String>{

	public List<Room> findAllByAccountIdentifier(String accountIdentifier);

}
