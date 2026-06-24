package uk.codersden.wod.achievements;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AthleteAchievementDao extends JpaRepository<AthleteAchievement, String> {
	List<AthleteAchievement> findAllByProfileIdentifier(String id);

	List<AthleteAchievement> findAllByProfileIdentifierAndUnlocked(String profileIdentifier, Boolean unlocked);
}

