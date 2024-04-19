
package acme.features.developer.trainingModule;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.entities.trainingmodule.TrainingModule;
import acme.entities.trainingsession.TrainingSession;
import acme.roles.Developer;

@Service
public class DeveloperTrainingModuleListService extends AbstractService<Developer, TrainingModule> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private DeveloperTrainingModuleRepository repository;

	// AbstractService interface ----------------------------------------------


	@Override
	public void authorise() {
		super.getResponse().setAuthorised(true);
	}

	@Override
	public void load() {
		Collection<TrainingModule> objects;

		int id;

		id = super.getRequest().getPrincipal().getActiveRoleId();

		objects = this.repository.findAllTrainingModuleByDeveloperId(id);

		super.getBuffer().addData(objects);
	}

	@Override
	public void unbind(final TrainingModule object) {
		assert object != null;

		Dataset dataset;

		Collection<TrainingSession> ts = this.repository.findTrainingSessionsByTrainingModuleId(object.getId());
		int totalHours = 0;
		for (TrainingSession t : ts) {
			Date inicio = t.getInitialPeriod();
			Date fin = t.getFinalPeriod();
			long diferenciaMilisegundos = fin.getTime() - inicio.getTime();
			int horasDiferencia = (int) Math.round(diferenciaMilisegundos / (1000.0 * 60 * 60));
			totalHours = totalHours + horasDiferencia;
		}

		dataset = super.unbind(object, "code", "creationMoment", "difficultLevel", "details");
		dataset.put("totalTime", totalHours);

		super.getResponse().addData(dataset);
	}

}
