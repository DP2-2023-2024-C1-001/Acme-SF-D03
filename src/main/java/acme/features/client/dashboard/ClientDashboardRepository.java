
package acme.features.client.dashboard;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;

@Repository
public interface ClientDashboardRepository extends AbstractRepository {

	@Query("select count(p) from ProgressLog p where p.contract.client.id = :clientId AND p.completeness < 25.00")
	Integer countOfProgressLogsWithCompletenessRateBelow25Percent(int clientId);

	@Query("select count(p) from ProgressLog p where p.contract.client.id = :clientId AND p.completeness < 50.00 AND p.completeness >= 25.00")
	Integer countOfProgressLogsWithCompletenessRateBetween25And50Percent(int clientId);

	@Query("select count(p) from ProgressLog p where p.contract.client.id = :clientId AND p.completeness <= 75.00 AND p.completeness >= 50.00")
	Integer countOfProgressLogsWithCompletenessRateBetween50And75Percent(int clientId);

	@Query("select count(p) from ProgressLog p where p.contract.client.id = :clientId AND p.completeness > 75.00")
	Integer countOfProgressLogsWithCompletenessRateAbove75Percent(int clientId);

	@Query("select avg(c.budget.amount) from Contract c where c.client.id = :clientId")
	Double avgBudgetOfContracts(int clientId);

	@Query("select stddev(c.budget.amount) from Contract c where c.client.id = :clientId")
	Double stddevBudgetOfContracts(int clientId);

	@Query("select min(c.budget.amount) from Contract c where c.client.id = :clientId")
	Double minBudgetOfContracts(int clientId);

	@Query("select max(c.budget.amount) from Contract c where c.client.id = :clientId")
	Double maxBudgetOfContracts(int clientId);

}
