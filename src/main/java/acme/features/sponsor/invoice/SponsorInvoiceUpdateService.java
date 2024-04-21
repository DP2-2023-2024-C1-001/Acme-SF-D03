
package acme.features.sponsor.invoice;

import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.models.Dataset;
import acme.client.helpers.MomentHelper;
import acme.client.services.AbstractService;
import acme.entities.invoice.Invoice;
import acme.entities.sponsorship.Sponsorship;
import acme.entities.systemconfiguration.SystemConfiguration;
import acme.roles.Sponsor;

@Service
public class SponsorInvoiceUpdateService extends AbstractService<Sponsor, Invoice> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private SponsorInvoiceRepository repository;

	// AbstractService interface ----------------------------------------------


	@Override
	public void authorise() {
		boolean status;
		int invoiceId;
		Sponsorship sponsorship;
		Invoice invoice;

		invoiceId = super.getRequest().getData("id", int.class);
		sponsorship = this.repository.findSponsorshipByInvoiceId(invoiceId);
		invoice = this.repository.findInvoiceById(invoiceId);
		status = sponsorship != null && invoice != null && !invoice.isPublished() && super.getRequest().getPrincipal().hasRole(sponsorship.getSponsor());

		super.getResponse().setAuthorised(status);

	}

	@Override
	public void load() {
		Invoice object;
		int id;

		id = super.getRequest().getData("id", int.class);
		object = this.repository.findInvoiceById(id);

		super.getBuffer().addData(object);
	}

	@Override
	public void bind(final Invoice object) {
		assert object != null;

		super.bind(object, "code", "registrationTime", "dueDate", "quantity", "tax", "link");
	}

	@Override
	public void validate(final Invoice object) {
		assert object != null;

		if (!super.getBuffer().getErrors().hasErrors("code")) {
			Invoice existing;

			existing = this.repository.findInvoiceByCode(object.getCode());
			super.state(existing == null || existing.getId() == object.getId(), "code", "sponsor.invoice.form.error.duplicated");

		}

		if (!super.getBuffer().getErrors().hasErrors("dueDate")) {
			Date minimumPeriod;

			minimumPeriod = MomentHelper.deltaFromMoment(object.getRegistrationTime(), 1, ChronoUnit.MONTHS);
			super.state(MomentHelper.isBeforeOrEqual(object.getDueDate(), minimumPeriod), "dueDate", "sponsor.invoice.form.error.too-close");
		}

		if (!super.getBuffer().getErrors().hasErrors("quantity")) {
			Double quantity;
			quantity = object.getQuantity().getAmount();
			super.state(quantity > 0, "quantity", "sponsor.invoice.form.error.negativeQuantity");

			final SystemConfiguration systemConfig = this.repository.findActualSystemConfiguration();
			final String currency = object.getQuantity().getCurrency();
			super.state(systemConfig.getAcceptedCurrencies().contains(" " + currency + " "), "quantity", "sponsor.invoice.form.error.currency");
		}

		if (!super.getBuffer().getErrors().hasErrors("tax")) {
			Double tax;

			tax = object.getTax();
			super.state(100 >= tax && tax >= 0, "code", "sponsor.invoice.form.error.invalid-tax");

		}
	}

	@Override
	public void perform(final Invoice object) {
		assert object != null;

		this.repository.save(object);
	}

	@Override
	public void unbind(final Invoice object) {
		assert object != null;

		Dataset dataset;

		dataset = super.unbind(object, "code", "registrationTime", "dueDate", "quantity", "tax", "link", "published");
		dataset.put("masterId", super.getRequest().getData("masterId", int.class));

		super.getResponse().addData(dataset);
	}
}
