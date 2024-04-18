
package acme.features.sponsor.sponsorship;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.project.Project;
import acme.entities.sponsorship.Sponsorship;

@Repository
public interface SponsorSponsorshipRepository extends AbstractRepository {

	@Query("select s from Sponsorship s where s.sponsor.id =:sponsorId")
	Collection<Sponsorship> findAllSponsorshipsBySponsorId(int sponsorId);

	@Query("select s from Sponsorship s where s.id =:sponsorshipId")
	Sponsorship findSponsorshipById(int sponsorshipId);

	@Query("select s.project from Sponsorship s where s.id =:sponsorshipId ")
	Collection<Project> findProjectBySponsorshipId(int sponsorshipId);
}
