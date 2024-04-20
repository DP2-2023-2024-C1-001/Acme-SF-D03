
package acme.features.administrator.systemconfiguration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.accounts.Administrator;
import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.entities.systemconfiguration.SystemConfiguration;

@Service
public class AdministratorSystemConfigurationShowService extends AbstractService<Administrator, SystemConfiguration> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AdministratorSystemConfigurationRepository repository;

	// AbstractService interface ----------------------------------------------


	@Override
	public void authorise() {
		super.getResponse().setAuthorised(true);
	}

	@Override
	public void load() {
		SystemConfiguration object;

		object = this.repository.findActualSystemConfiguration();

		super.getBuffer().addData(object);
	}

	@Override
	public void unbind(final SystemConfiguration object) {
		assert object != null;

		Dataset dataset;

		dataset = super.unbind(object, "acceptedCurrencies", "systemCurrency");

		super.getResponse().addData(dataset);
	}

}
