
package acme.features.administrator.dashboard;

import java.util.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;

@Repository
public interface AdministratorDashboardRepository extends AbstractRepository {

	@Query("select count(a) from Administrator a")
	Integer totalNumberOfPrincipalsWithRoleAdministrator();

	@Query("select count(a) from Auditor a")
	Integer totalNumberOfPrincipalsWithRoleAuditor();

	@Query("select count(c) from Client c")
	Integer totalNumberOfPrincipalsWithRoleClient();

	@Query("select count(c) from Consumer c")
	Integer totalNumberOfPrincipalsWithRoleConsumer();

	@Query("select count(d) from Developer d")
	Integer totalNumberOfPrincipalsWithRoleDeveloper();

	@Query("select count(m) from Manager m")
	Integer totalNumberOfPrincipalsWithRoleManager();

	@Query("select count(p) from Provider p")
	Integer totalNumberOfPrincipalsWithRoleProvider();

	@Query("select count(s) from Sponsor s")
	Integer totalNumberOfPrincipalsWithRoleSponsor();

	@Query("select 1.0 * count(n) / (select count(t) from Notice t) from Notice n where n.emailAddress !=null AND n.emailAddress not like '' AND n.link !=null AND n.link not like ''")
	Double ratioOfNoticesWithEmailAndLink();

	@Query("select 1.0 * count(o) / (select count(t) from Objective t) from Objective o where o.status = true")
	Double ratioOfCriticalObjectives();

	@Query("select 1.0 * count(o) / (select count(t) from Objective t) from Objective o where o.status = false")
	Double ratioOfNonCriticalObjectives();

	@Query("select avg(r.impact * r.probability) from Risk r")
	Double avgRiskValue();

	@Query("select stddev(r.impact * r.probability) from Risk r")
	Double stddevRiskValue();

	@Query("select max(r.impact * r.probability) from Risk r")
	Double maxRiskValue();

	@Query("select min(r.impact * r.probability) from Risk r")
	Double minRiskValue();

	@Query("select avg(count(c)) from Claim c where c.instantiationMoment >= deadline")
	Double avgNumberOfClaims(Date deadline);

	@Query("select stddev(count(c)) from Claim c where c.instantiationMoment >= deadline")
	Double stddevNumberOfClaims(Date deadline);

	@Query("select min(count(c)) from Claim c where c.instantiationMoment >= deadline")
	Double minNumberOfClaims(Date deadline);

	@Query("select max(count(c)) from Claim c where c.instantiationMoment >= deadline")
	Double maxNumberOfClaims(Date deadline);

}
