
package acme.features.administrator.dashboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.accounts.Administrator;
import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.forms.AdministratorDashboard;

@Service
public class AdministratorDashboardShowService extends AbstractService<Administrator, AdministratorDashboard> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AdministratorDashboardRepository repository;

	// AbstractService interface ----------------------------------------------


	@Override
	public void authorise() {
		boolean status;

		status = super.getRequest().getPrincipal().hasRole(Administrator.class);

		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {
		AdministratorDashboard dashboard;

		Integer totalNumberOfPrincipalsWithRoleAdministrator;
		Integer totalNumberOfPrincipalsWithRoleAuditor;
		Integer totalNumberOfPrincipalsWithRoleClient;
		Integer totalNumberOfPrincipalsWithRoleConsumer;
		Integer totalNumberOfPrincipalsWithRoleDeveloper;
		Integer totalNumberOfPrincipalsWithRoleManager;
		Integer totalNumberOfPrincipalsWithRoleProvider;
		Integer totalNumberOfPrincipalsWithRoleSponsor;

		Double ratioOfNoticesWithEmailAndLink;
		Double ratioOfCriticalObjectives;
		Double ratioOfNonCriticalObjectives;

		Double averageOfTheValueInTheRisks;
		Double deviationOfTheValueInTheRisks;
		Double minimumOfTheValueInTheRisks;
		Double maximumOfTheValueInTheRisks;

		Double averageOfTheNumberOfClaimsPostedOverTheLast10Weeks;
		Double deviationOfTheNumberOfClaimsPostedOverTheLast10Weeks;
		Integer minimumOfTheNumberOfClaimsPostedOverTheLast10Weeks;
		Integer maximumOfTheNumberOfClaimsPostedOverTheLast10Weeks;

		totalNumberOfPrincipalsWithRoleAdministrator = this.repository.totalNumberOfPrincipalsWithRoleAdministrator();
		totalNumberOfPrincipalsWithRoleAuditor = this.repository.totalNumberOfPrincipalsWithRoleAuditor();
		totalNumberOfPrincipalsWithRoleClient = this.repository.totalNumberOfPrincipalsWithRoleClient();
		totalNumberOfPrincipalsWithRoleConsumer = this.repository.totalNumberOfPrincipalsWithRoleConsumer();
		totalNumberOfPrincipalsWithRoleDeveloper = this.repository.totalNumberOfPrincipalsWithRoleDeveloper();
		totalNumberOfPrincipalsWithRoleManager = this.repository.totalNumberOfPrincipalsWithRoleManager();
		totalNumberOfPrincipalsWithRoleProvider = this.repository.totalNumberOfPrincipalsWithRoleProvider();
		totalNumberOfPrincipalsWithRoleSponsor = this.repository.totalNumberOfPrincipalsWithRoleSponsor();

		ratioOfNoticesWithEmailAndLink = this.repository.ratioOfNoticesWithEmailAndLink();
		ratioOfCriticalObjectives = this.repository.ratioOfCriticalObjectives();
		ratioOfNonCriticalObjectives = this.repository.ratioOfNonCriticalObjectives();

		averageOfTheValueInTheRisks = this.repository.avgRiskValue();
		deviationOfTheValueInTheRisks = this.repository.stddevRiskValue();
		minimumOfTheValueInTheRisks = this.repository.minRiskValue();
		maximumOfTheValueInTheRisks = this.repository.maxRiskValue();

		dashboard = new AdministratorDashboard();
		dashboard.setTotalNumberOfPrincipalsWithRoleAdministrator(totalNumberOfPrincipalsWithRoleAdministrator);
		dashboard.setTotalNumberOfPrincipalsWithRoleAuditor(totalNumberOfPrincipalsWithRoleAuditor);
		dashboard.setTotalNumberOfPrincipalsWithRoleClient(totalNumberOfPrincipalsWithRoleClient);
		dashboard.setTotalNumberOfPrincipalsWithRoleConsumer(totalNumberOfPrincipalsWithRoleConsumer);
		dashboard.setTotalNumberOfPrincipalsWithRoleDeveloper(totalNumberOfPrincipalsWithRoleDeveloper);
		dashboard.setTotalNumberOfPrincipalsWithRoleManager(totalNumberOfPrincipalsWithRoleManager);
		dashboard.setTotalNumberOfPrincipalsWithRoleProvider(totalNumberOfPrincipalsWithRoleProvider);
		dashboard.setTotalNumberOfPrincipalsWithRoleSponsor(totalNumberOfPrincipalsWithRoleSponsor);
		dashboard.setRatioOfNoticesWithEmailAndLink(ratioOfNoticesWithEmailAndLink);
		dashboard.setRatioOfCriticalObjectives(ratioOfCriticalObjectives);
		dashboard.setRatioOfNonCriticalObjectives(ratioOfNonCriticalObjectives);
		dashboard.setMaximumOfTheValueInTheRisks(maximumOfTheValueInTheRisks);
		dashboard.setMinimumOfTheValueInTheRisks(minimumOfTheValueInTheRisks);
		dashboard.setAverageOfTheValueInTheRisks(averageOfTheValueInTheRisks);
		dashboard.setDeviationOfTheValueInTheRisks(deviationOfTheValueInTheRisks);

		super.getBuffer().addData(dashboard);
	}

	@Override
	public void unbind(final AdministratorDashboard object) {
		Dataset dataset;

		dataset = super.unbind(object, //
			"totalNumberOfPrincipalsWithRoleAdministrator", "totalNumberOfPrincipalsWithRoleAuditor",//
			"totalNumberOfPrincipalsWithRoleClient", "totalNumberOfPrincipalsWithRoleConsumer",//
			"totalNumberOfPrincipalsWithRoleDeveloper", "totalNumberOfPrincipalsWithRoleManager", //
			"totalNumberOfPrincipalsWithRoleProvider", "totalNumberOfPrincipalsWithRoleSponsor", //
			"ratioOfNoticesWithEmailAndLink", "ratioOfCriticalObjectives", "ratioOfNonCriticalObjectives", //
			"averageOfTheValueInTheRisks", "deviationOfTheValueInTheRisks", "maximumOfTheValueInTheRisks", //
			"minimumOfTheValueInTheRisks");

		super.getResponse().addData(dataset);
	}
}
