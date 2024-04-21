
package acme.features.sponsor.dashboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.datatypes.Money;
import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.forms.SponsorDashboard;
import acme.roles.Sponsor;

@Service
public class SponsorDashboardShowService extends AbstractService<Sponsor, SponsorDashboard> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private SponsorDashboardRepository repository;

	// AbstractService interface ----------------------------------------------


	@Override
	public void authorise() {
		boolean status;

		status = super.getRequest().getPrincipal().hasRole(Sponsor.class);

		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {
		SponsorDashboard dashboard;
		Integer totalNumberOfInvoicesWithTaxLessOrEquals21Percent;
		Integer totalNumberOfSponsorshipWithLink;

		Money averageAmountOfSponsorship;
		Money deviationAmountOfSponsorship;
		Money minimumAmountOfSponsorship;
		Money maximumAmountOfSponsorship;

		Money averageQuantityOfInvoice;
		Money deviationQuantityOfInvoice;
		Money minimumQuantityOfInvoice;
		Money maximumQuantityOfInvoice;

		//--------
		totalNumberOfInvoicesWithTaxLessOrEquals21Percent = this.repository.totalNumberOfInvoicesWithTaxLessOrEquals21Percent(super.getRequest().getPrincipal().getActiveRoleId());
		totalNumberOfSponsorshipWithLink = this.repository.totalNumberOfSponsorshipWithLink(super.getRequest().getPrincipal().getActiveRoleId());

		averageAmountOfSponsorship = new Money();
		averageAmountOfSponsorship.setAmount(this.repository.averageAmountOfSponsorship(super.getRequest().getPrincipal().getActiveRoleId()));
		averageAmountOfSponsorship.setCurrency("EUR");

		deviationAmountOfSponsorship = new Money();
		deviationAmountOfSponsorship.setAmount(this.repository.deviationAmountOfSponsorship(super.getRequest().getPrincipal().getActiveRoleId()));
		deviationAmountOfSponsorship.setCurrency("EUR");

		minimumAmountOfSponsorship = new Money();
		minimumAmountOfSponsorship.setAmount(this.repository.minimumAmountOfSponsorship(super.getRequest().getPrincipal().getActiveRoleId()));
		minimumAmountOfSponsorship.setCurrency("EUR");

		maximumAmountOfSponsorship = new Money();
		maximumAmountOfSponsorship.setAmount(this.repository.maximumAmountOfSponsorship(super.getRequest().getPrincipal().getActiveRoleId()));
		maximumAmountOfSponsorship.setCurrency("EUR");

		averageQuantityOfInvoice = new Money();
		averageQuantityOfInvoice.setAmount(this.repository.averageQuantityOfInvoice(super.getRequest().getPrincipal().getActiveRoleId()));
		averageQuantityOfInvoice.setCurrency("EUR");

		deviationQuantityOfInvoice = new Money();
		deviationQuantityOfInvoice.setAmount(this.repository.deviationQuantityOfInvoice(super.getRequest().getPrincipal().getActiveRoleId()));
		deviationQuantityOfInvoice.setCurrency("EUR");

		minimumQuantityOfInvoice = new Money();
		minimumQuantityOfInvoice.setAmount(this.repository.minimumQuantityOfInvoice(super.getRequest().getPrincipal().getActiveRoleId()));
		minimumQuantityOfInvoice.setCurrency("EUR");

		maximumQuantityOfInvoice = new Money();
		maximumQuantityOfInvoice.setAmount(this.repository.maximumQuantityOfInvoice(super.getRequest().getPrincipal().getActiveRoleId()));
		maximumQuantityOfInvoice.setCurrency("EUR");

		dashboard = new SponsorDashboard();
		dashboard.setTotalNumberOfInvoicesWithTaxLessOrEquals21Percent(totalNumberOfInvoicesWithTaxLessOrEquals21Percent);
		dashboard.setTotalNumberOfSponsorshipWithLink(totalNumberOfSponsorshipWithLink);
		dashboard.setAverageAmountOfSponsorship(averageAmountOfSponsorship);
		dashboard.setDeviationAmountOfSponsorship(deviationAmountOfSponsorship);
		dashboard.setMinimumAmountOfSponsorship(minimumAmountOfSponsorship);
		dashboard.setMaximumAmountOfSponsorship(maximumAmountOfSponsorship);
		dashboard.setAverageQuantityOfInvoice(averageQuantityOfInvoice);
		dashboard.setDeviationQuantityOfInvoice(deviationQuantityOfInvoice);
		dashboard.setMinimumQuantityOfInvoice(minimumQuantityOfInvoice);
		dashboard.setMaximumQuantityOfInvoice(maximumQuantityOfInvoice);

		super.getBuffer().addData(dashboard);

	}

	@Override
	public void unbind(final SponsorDashboard object) {
		Dataset dataset;

		dataset = super.unbind(object, "totalNumberOfInvoicesWithTaxLessOrEquals21Percent", "totalNumberOfSponsorshipWithLink", //
			"averageAmountOfSponsorship", "deviationAmountOfSponsorship", "minimumAmountOfSponsorship", "maximumAmountOfSponsorship", //
			"averageQuantityOfInvoice", "deviationQuantityOfInvoice", "minimumQuantityOfInvoice", "maximumQuantityOfInvoice");

		super.getResponse().addData(dataset);

	}

}
