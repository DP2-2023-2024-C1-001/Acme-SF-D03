
package acme.features.manager.userstoryproject;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

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
public class ManagerUserStoryProjectCreateService extends AbstractService<Manager, UserStoryProject> {
	// Internal state ---------------------------------------------------------

	@Autowired
	protected ManagerUserStoryProjectRepository repository;

	// AbstractService interface ----------------------------------------------


	@Override
	public void authorise() {
		boolean status;

		status = super.getRequest().getPrincipal().hasRole(Manager.class);
		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {
		UserStoryProject object;

		object = new UserStoryProject();
		super.getBuffer().addData(object);
	}

	@Override
	public void bind(final UserStoryProject object) {
		assert object != null;

		int projectId;
		Project project;

		projectId = super.getRequest().getData("project", int.class);
		project = this.repository.findOneProjectById(projectId);

		int userStoryId;
		UserStory userStory;

		userStoryId = super.getRequest().getData("userStory", int.class);
		userStory = this.repository.findOneUserStoryById(userStoryId);

		object.setProject(project);
		object.setUserStory(userStory);
	}

	@Override
	public void validate(final UserStoryProject object) {
		assert object != null;

	}

	@Override
	public void perform(final UserStoryProject object) {
		assert object != null;

		this.repository.save(object);
	}

	@Override
	public void unbind(final UserStoryProject object) {
		assert object != null;

		final SelectChoices projectChoices;
		final SelectChoices userStoryChoices;
		final Dataset dataset;

		final Collection<Project> projects;
		final Collection<UserStory> userStoriesPublished;
		final Collection<UserStory> userStories;

		int managerId;
		managerId = super.getRequest().getPrincipal().getActiveRoleId();

		projects = this.repository.findProjectsByManagerId(managerId);
		userStoriesPublished = this.repository.findAllPublishedUserStories();
		userStories = this.repository.findUserStoriesByManagerId(managerId);

		Set<UserStory> userStoriesAll = new HashSet<>(userStories);
		userStoriesAll.addAll(userStoriesPublished);

		projectChoices = SelectChoices.from(projects, "title", object.getProject());
		userStoryChoices = SelectChoices.from(userStories, "title", object.getUserStory());

		dataset = super.unbind(object, "project", "userStory");
		dataset.put("projectChoices", projectChoices);
		dataset.put("userStoryChoices", userStoryChoices);

		super.getResponse().addData(dataset);
	}

}
