
package acme.features.developer.trainingModule;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.trainingmodule.TrainingModule;

@Repository
public interface DeveloperTrainingModuleRepository extends AbstractRepository {

	@Query("select tm from TrainingModule tm")
	Collection<TrainingModule> findAllClaims();

	@Query("select tm from TrainingModule tm where tm.id = :id")
	TrainingModule findOneClaimById(int id);

}
