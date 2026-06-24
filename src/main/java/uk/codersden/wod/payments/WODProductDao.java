package uk.codersden.wod.payments;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WODProductDao extends JpaRepository<WODProduct, String> {
	List<WODProduct> findAllByAccountIdentifier(String id);
	List<WODProduct> findAllByAccountIdentifierAndActiveTrue(String id);
	
}
