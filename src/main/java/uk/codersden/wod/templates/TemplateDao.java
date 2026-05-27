package uk.codersden.wod.templates;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TemplateDao extends JpaRepository<Template, String> {
	List<Template> findAllByAccountIdentifier(String id);
}
