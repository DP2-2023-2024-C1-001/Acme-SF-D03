
package acme.features.sponsor.sponsorship;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.client.views.SelectChoices;
import acme.entities.project.Project;
import acme.entities.sponsorship.Sponsorship;
import acme.roles.Sponsor;

@Service
public class SponsorSponsorshipShowService extends AbstractService<Sponsor, Sponsorship> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private SponsorSponsorshipRepository repository;

	// AbstractService interface ----------------------------------------------


	@Override
	public void authorise() {
		super.getResponse().setAuthorised(true);
	}

	@Override
	public void load() {
		Sponsorship object;
		int id;

		id = super.getRequest().getData("id", int.class);

		object = this.repository.findSponsorshipById(id);

		super.getBuffer().addData(object);
	}

	@Override
	public void unbind(final Sponsorship object) {
		assert object != null;

		Dataset dataset;
		long duration;

		Collection<Project> projects;
		SelectChoices choices;
		int id;
		id = super.getRequest().getData("id", int.class);
		projects = this.repository.findProjectBySponsorshipId(id);
		choices = SelectChoices.from(projects, "code", object.getProject());

		//propiedad derivada duracion del sponsorship
		long res = object.getFinalDate().getTime() - object.getInitialDate().getTime();
		duration = res / (1000 * 60 * 60 * 24); // duracion en numero de dias

		dataset = super.unbind(object, "code", "moment", "amount", "type", "email", "link");
		dataset.put("project", choices);
		dataset.put("duration", duration);

		super.getResponse().addData(dataset);
	}

}
