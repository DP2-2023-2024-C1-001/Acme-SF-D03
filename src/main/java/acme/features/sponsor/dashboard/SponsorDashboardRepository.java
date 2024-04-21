
package acme.features.sponsor.dashboard;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;

@Repository
public interface SponsorDashboardRepository extends AbstractRepository {

	@Query("select count(i) from Invoice i where i.sponsorship.sponsor.id = :sponsorId AND i.tax <= 21.00")
	Integer totalNumberOfInvoicesWithTaxLessOrEquals21Percent(int sponsorId);

	@Query("select count(s) from Sponsorship s where s.sponsor.id = :sponsorId AND s.link is not null and s.link not like ''")
	Integer totalNumberOfSponsorshipWithLink(int sponsorId);

	@Query("select avg(s.amount.amount) from Sponsorship s where s.sponsor.id = :sponsorId")
	Double averageAmountOfSponsorship(int sponsorId);

	@Query("select stddev(s.amount.amount) from Sponsorship s where s.sponsor.id = :sponsorId")
	Double deviationAmountOfSponsorship(int sponsorId);

	@Query("select min(s.amount.amount) from Sponsorship s where s.sponsor.id = :sponsorId")
	Double minimumAmountOfSponsorship(int sponsorId);

	@Query("select max(s.amount.amount) from Sponsorship s where s.sponsor.id = :sponsorId")
	Double maximumAmountOfSponsorship(int sponsorId);

	@Query("select avg(i.quantity.amount) from Invoice i where i.sponsorship.sponsor.id = :sponsorId")
	Double averageQuantityOfInvoice(int sponsorId);

	@Query("select stddev(i.quantity.amount) from Invoice i where i.sponsorship.sponsor.id = :sponsorId")
	Double deviationQuantityOfInvoice(int sponsorId);

	@Query("select min(i.quantity.amount) from Invoice i where i.sponsorship.sponsor.id = :sponsorId")
	Double minimumQuantityOfInvoice(int sponsorId);

	@Query("select max(i.quantity.amount) from Invoice i where i.sponsorship.sponsor.id = :sponsorId")
	Double maximumQuantityOfInvoice(int sponsorId);

}
