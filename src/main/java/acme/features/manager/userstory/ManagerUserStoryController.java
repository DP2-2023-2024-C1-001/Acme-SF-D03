
package acme.features.manager.userstory;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.client.controllers.AbstractController;
import acme.entities.userStory.UserStory;
import acme.roles.Manager;

@Controller
public class ManagerUserStoryController extends AbstractController<Manager, UserStory> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected ManagerUserStoryListService			listService;

	@Autowired
	private ManagerUserStoryListByProjectService	listByProyectService;

	@Autowired
	private ManagerUserStoryShowService				showService;

	@Autowired
	private ManagerUserStoryCreateService			createService;

	@Autowired
	protected ManagerUserStoryUpdateService			updateService;

	// Constructors -----------------------------------------------------------


	@PostConstruct
	protected void initialise() {
		super.addBasicCommand("list", this.listService);
		super.addBasicCommand("show", this.showService);
		super.addBasicCommand("create", this.createService);
		super.addBasicCommand("update", this.updateService);

		super.addCustomCommand("list-by-project", "list", this.listByProyectService);

	}

}
