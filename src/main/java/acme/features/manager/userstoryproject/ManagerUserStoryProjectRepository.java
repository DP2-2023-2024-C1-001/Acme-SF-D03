
package acme.features.manager.userstoryproject;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.project.Project;
import acme.entities.project.UserStoryProject;
import acme.entities.userStory.UserStory;
import acme.roles.Manager;

@Repository
public interface ManagerUserStoryProjectRepository extends AbstractRepository {

	@Query("select pus from UserStoryProject pus where pus.id = :id")
	UserStoryProject findOneProjectUserStoryById(int id);

	@Query("select pus from UserStoryProject pus where pus.project.manager.id = :id and pus.userStory.manager.id = :id")
	Collection<UserStoryProject> findProjectUserStoryByManagerId(int id);

	@Query("select m from Manager m where m.id = :id")
	Manager findOneManagerById(int id);

	@Query("select p from Project p where p.id = :id")
	Project findOneProjectById(int id);

	@Query("select p from Project p where p.manager.id = :id")
	Collection<Project> findProjectsByManagerId(int id);

	@Query("select us from UserStory us where us.id = :id")
	UserStory findOneUserStoryById(int id);

	@Query("select us from UserStory us where us.manager.id = :id")
	Collection<UserStory> findUserStoriesByManagerId(int id);
}
