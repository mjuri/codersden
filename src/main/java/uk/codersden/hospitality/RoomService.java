package uk.codersden.hospitality;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uk.codersden.hr.NotFoundException;
import uk.codersden.hospitality.Room;
import uk.codersden.hospitality.RoomDao;
import uk.codersden.hr.profiles.Account;
import uk.codersden.hr.profiles.AccountDao;
import uk.codersden.hr.profiles.AccountNotFoundException;
import uk.codersden.hr.profiles.ProfileNotFoundException;


@Service
public class RoomService {
	@Autowired
	private RoomDao dao;
	
	@Autowired
	private AccountDao accountDao;
	
	public List<Room> findAllByAccountIdentifier(String accountIdentifier) throws AccountNotFoundException {
		Optional<Account> op = accountDao.findById(accountIdentifier);
		if(op.isEmpty()) {
			throw new AccountNotFoundException(accountIdentifier + " not found");
		}
		
		List<Room> list = dao.findAllByAccountIdentifier(accountIdentifier);
		
		return list;
	}
	public Room createRoom(Room room) {
		return dao.save(room);
	}

	public Room updateRoom(String roomIdentifier, Room room) throws NotFoundException {
		Optional<Room> op = dao.findById(roomIdentifier);
		if(op.isEmpty()){
			throw new NotFoundException(roomIdentifier + " not found");
		}
		room.setIdentifier(roomIdentifier);
		
		return dao.save(room);
	}
	
	public Room findByIdentifier(String identifier) throws NotFoundException {
		Optional<Room> op = dao.findById(identifier);
		if(op.isEmpty()) {
			throw new NotFoundException(identifier + " not found");
		}
		return op.get();
	}
	
	public Room deleteRoom(String identifier) throws NotFoundException {
		Optional<Room> op = dao.findById(identifier);

		if(op.isEmpty()) {
			throw new NotFoundException(identifier);
		}
		Room room = op.get();
		
		dao.delete(room);
		
		
		return room;
	}
}
