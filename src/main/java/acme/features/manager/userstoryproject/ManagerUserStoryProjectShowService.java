
package acme.features.manager.userstoryproject;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.client.views.SelectChoices;
import acme.entities.project.Project;
import acme.entities.project.UserStoryProject;
import acme.entities.userStory.UserStory;
import acme.roles.Manager;

@Service
public class ManagerUserStoryProjectShowService extends AbstractService<Manager, UserStoryProject> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private ManagerUserStoryProjectRepository repository;

	// AbstractService interface ----------------------------------------------


	@Override
	public void authorise() {
		boolean status;
		UserStoryProject usp;
		Manager manager;

		usp = this.repository.findOneUserStoryProjectById(super.getRequest().getData("id", int.class));
		manager = this.repository.findOneManagerById(super.getRequest().getPrincipal().getActiveRoleId());

		status = super.getRequest().getPrincipal().getActiveRole() == Manager.class && usp.getProject().getManager().equals(manager) && usp.getUserStory().getManager().equals(manager);

		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {
		UserStoryProject object;
		int id;

		id = super.getRequest().getData("id", int.class);
		object = this.repository.findOneUserStoryProjectById(id);

		super.getBuffer().addData(object);
	}

	@Override
	public void unbind(final UserStoryProject object) {
		//TO DO
		assert object != null;

		int managerId;
		Collection<Project> projects;
		Collection<UserStory> userStories;
		SelectChoices projectChoices;
		SelectChoices userStoryChoices;
		Dataset dataset;

		managerId = super.getRequest().getPrincipal().getActiveRoleId();

		projects = this.repository.findProjectsByManagerId(managerId);
		userStories = this.repository.findUserStoriesByManagerId(managerId);

		projectChoices = SelectChoices.from(projects, "title", object.getProject());
		userStoryChoices = SelectChoices.from(userStories, "title", object.getUserStory());

		dataset = super.unbind(object, "project", "userStory");
		dataset.put("projectChoices", projectChoices);
		dataset.put("userStoryChoices", userStoryChoices);

		super.getResponse().addData(dataset);
	}

}
