package uk.codersden.wod.payments;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WODGymDao extends JpaRepository<WODGym, String> {
	List<WODGym> findAllByAccountIdentifier(String id);
}
