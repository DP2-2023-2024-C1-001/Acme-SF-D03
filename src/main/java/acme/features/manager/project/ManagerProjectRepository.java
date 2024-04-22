
package acme.features.manager.project;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.project.Project;
import acme.entities.project.UserStoryProject;
import acme.entities.systemconfiguration.SystemConfiguration;
import acme.entities.userStory.UserStory;
import acme.roles.Manager;

@Repository
public interface ManagerProjectRepository extends AbstractRepository {

	@Query("select p from Project p where p.manager.id = :managerId")
	Collection<Project> findAllProjectsByManagerId(int managerId);

	@Query("select p from Project p where p.id = :id")
	Project findOneProjectById(int id);

	@Query("select m from Manager m where m.id = :managerId")
	Manager findOneManagerById(int managerId);

	@Query("select p from Project p where p.code = :code")
	Optional<Project> findOneProjectByCode(String code);

	@Query("select up from UserStoryProject up where up.project.id = :projectId")
	Collection<UserStoryProject> findUserStoryProjectsByProjectId(int projectId);

	@Query("select up.userStory from UserStoryProject up where up.project.id = :projectId")
	Collection<UserStory> findAllUserStoriesByProjectId(int projectId);

	@Query("select p from Project p")
	Collection<Project> findAllProjects();

	@Query("select us from UserStory us")
	Collection<UserStory> findAllUserStories();

	@Query("select sc from SystemConfiguration sc")
	SystemConfiguration findActualSystemConfiguration();

	@Query("select us from UserStory us where us.manager.id =: managerId ")
	Collection<UserStory> findAllUserStoriesByManagerId(int managerId);

}
