
package acme.features.manager.userstory;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.userStory.UserStory;

@Repository
public interface ManagerUserStoryRepository extends AbstractRepository {

	@Query("select up.userStory from UserStoryProject up where up.project.id = :projectId")
	Collection<UserStory> findUserStoriesByProjectId(int projectId);

	@Query("select us from UserStory us where us.id = :id")
	UserStory findOneUserStoryById(int id);

}
