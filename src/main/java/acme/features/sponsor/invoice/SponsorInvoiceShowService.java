
package acme.features.sponsor.invoice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.datatypes.Money;
import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.entities.invoice.Invoice;
import acme.roles.Sponsor;

@Service
public class SponsorInvoiceShowService extends AbstractService<Sponsor, Invoice> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private SponsorInvoiceRepository repository;

	// AbstractService interface ----------------------------------------------


	@Override
	public void authorise() {
		super.getResponse().setAuthorised(true);
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
	public void unbind(final Invoice object) {
		assert object != null;

		Dataset dataset;

		Money totalAmount = object.totalAmount();
		dataset = super.unbind(object, "code", "registrationTime", "dueDate", "quantity", "tax", "link");
		dataset.put("totalAmount", totalAmount);

		super.getResponse().addData(dataset);
	}
}
