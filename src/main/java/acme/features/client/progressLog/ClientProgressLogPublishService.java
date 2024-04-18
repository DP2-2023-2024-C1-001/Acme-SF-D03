
package acme.features.client.progressLog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.entities.contract.Contract;
import acme.entities.progresslog.ProgressLog;
import acme.roles.Client;

@Service
public class ClientProgressLogPublishService extends AbstractService<Client, ProgressLog> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private ClientProgressLogRepository repository;

	// AbstractService interface ----------------------------------------------


	@Override
	public void authorise() {
		boolean status;
		int progressLogId;
		ProgressLog progressLog;
		Contract contract;
		Client client;

		progressLogId = super.getRequest().getData("id", int.class);
		progressLog = this.repository.findOneProgressLogById(progressLogId);
		contract = this.repository.findOneContractByProgressLogId(progressLogId);
		client = progressLog == null ? null : contract.getClient();
		status = progressLog != null && !progressLog.isPublished() && contract != null && super.getRequest().getPrincipal().hasRole(client);

		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {
		ProgressLog object;
		int id;

		id = super.getRequest().getData("id", int.class);
		object = this.repository.findOneProgressLogById(id);

		super.getBuffer().addData(object);
	}

	@Override
	public void bind(final ProgressLog object) {
		assert object != null;

		super.bind(object, "code", "completeness", "comment", "registrationMoment", "responsiblePerson");
	}

	@Override
	public void validate(final ProgressLog object) {
		assert object != null;

		if (!super.getBuffer().getErrors().hasErrors("code")) {
			ProgressLog existing;

			existing = this.repository.findOneProgressLogByCode(object.getCode());
			super.state(existing == null || existing.getId() == object.getId(), "code", "client.progress-log.form.error.duplicated");

		}

	}

	@Override
	public void perform(final ProgressLog object) {
		assert object != null;

		object.setPublished(true);
		this.repository.save(object);
	}

	@Override
	public void unbind(final ProgressLog object) {
		assert object != null;

		Dataset dataset;

		dataset = super.unbind(object, "code", "completeness", "comment", "registrationMoment", "responsiblePerson", "published");
		dataset.put("masterId", object.getContract().getId());

		super.getResponse().addData(dataset);
	}
}
