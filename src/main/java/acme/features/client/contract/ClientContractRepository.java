
package acme.features.client.contract;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.contract.Contract;
import acme.entities.project.Project;

@Repository
public interface ClientContractRepository extends AbstractRepository {

	@Query("select c from Contract c where c.client.id = :clientId")
	Collection<Contract> findAllContractsByClientId(int clientId);

	@Query("select c from Contract c where c.id = :id")
	Contract findOneContractById(int id);

	@Query("select c.project from Contract c where c.id = :id ")
	Collection<Project> findProjectByContractId(int id);
}
