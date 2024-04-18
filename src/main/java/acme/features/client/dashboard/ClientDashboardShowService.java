
package acme.features.client.dashboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.datatypes.Money;
import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.forms.ClientDashboard;
import acme.roles.Client;

@Service
public class ClientDashboardShowService extends AbstractService<Client, ClientDashboard> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private ClientDashboardRepository repository;

	// AbstractService interface ----------------------------------------------


	@Override
	public void authorise() {
		boolean status;

		status = super.getRequest().getPrincipal().hasRole(Client.class);

		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {
		ClientDashboard dashboard;
		Integer countOfProgressLogsWithCompletenessRateBelow25Percent;
		Integer countOfProgressLogsWithCompletenessRateBetween25And50Percent;
		Integer countOfProgressLogsWithCompletenessRateBetween50And75Percent;
		Integer countOfProgressLogsWithCompletenessRateAbove75Percent;

		Money averageBudgetOfContracts;
		Money deviationBudgetOfContracts;
		Money minimumBudgetOfContracts;
		Money maximumBudgetOfContracts;

		countOfProgressLogsWithCompletenessRateBelow25Percent = this.repository.countOfProgressLogsWithCompletenessRateBelow25Percent(super.getRequest().getPrincipal().getActiveRoleId());
		countOfProgressLogsWithCompletenessRateBetween25And50Percent = this.repository.countOfProgressLogsWithCompletenessRateBetween25And50Percent(super.getRequest().getPrincipal().getActiveRoleId());
		countOfProgressLogsWithCompletenessRateBetween50And75Percent = this.repository.countOfProgressLogsWithCompletenessRateBetween50And75Percent(super.getRequest().getPrincipal().getActiveRoleId());
		countOfProgressLogsWithCompletenessRateAbove75Percent = this.repository.countOfProgressLogsWithCompletenessRateAbove75Percent(super.getRequest().getPrincipal().getActiveRoleId());

		averageBudgetOfContracts = new Money();
		averageBudgetOfContracts.setAmount(this.repository.avgBudgetOfContracts(super.getRequest().getPrincipal().getActiveRoleId()));
		averageBudgetOfContracts.setCurrency("EUR");

		deviationBudgetOfContracts = new Money();
		deviationBudgetOfContracts.setAmount(this.repository.stddevBudgetOfContracts(super.getRequest().getPrincipal().getActiveRoleId()));
		deviationBudgetOfContracts.setCurrency("EUR");

		minimumBudgetOfContracts = new Money();
		minimumBudgetOfContracts.setAmount(this.repository.minBudgetOfContracts(super.getRequest().getPrincipal().getActiveRoleId()));
		minimumBudgetOfContracts.setCurrency("EUR");

		maximumBudgetOfContracts = new Money();
		maximumBudgetOfContracts.setAmount(this.repository.maxBudgetOfContracts(super.getRequest().getPrincipal().getActiveRoleId()));
		maximumBudgetOfContracts.setCurrency("EUR");

		dashboard = new ClientDashboard();
		dashboard.setTotalNumberOfProgressLogsWithCompletenessRateBelow25Percent(countOfProgressLogsWithCompletenessRateBelow25Percent);
		dashboard.setTotalNumberOfProgressLogsWithCompletenessRateBetween25And50Percent(countOfProgressLogsWithCompletenessRateBetween25And50Percent);
		dashboard.setTotalNumberOfProgressLogsWithCompletenessRateBetween50And75Percent(countOfProgressLogsWithCompletenessRateBetween50And75Percent);
		dashboard.setTotalNumberOfProgressLogsWithCompletenessRateAbove75Percent(countOfProgressLogsWithCompletenessRateAbove75Percent);
		dashboard.setAverageBudgetOfContracts(averageBudgetOfContracts);
		dashboard.setDeviationBudgetOfContracts(deviationBudgetOfContracts);
		dashboard.setMinimumBudgetOfContracts(minimumBudgetOfContracts);
		dashboard.setMaximumBudgetOfContracts(maximumBudgetOfContracts);

		super.getBuffer().addData(dashboard);
	}

	@Override
	public void unbind(final ClientDashboard object) {
		Dataset dataset;

		dataset = super.unbind(object, //
			"totalNumberOfProgressLogsWithCompletenessRateBelow25Percent", "totalNumberOfProgressLogsWithCompletenessRateBetween25And50Percent", //
			"totalNumberOfProgressLogsWithCompletenessRateBetween50And75Percent", "totalNumberOfProgressLogsWithCompletenessRateAbove75Percent", //
			"averageBudgetOfContracts", "deviationBudgetOfContracts", "minimumBudgetOfContracts", "maximumBudgetOfContracts");

		super.getResponse().addData(dataset);
	}
}
