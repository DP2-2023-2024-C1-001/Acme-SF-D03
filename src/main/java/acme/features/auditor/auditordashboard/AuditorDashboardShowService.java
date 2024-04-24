
package acme.features.auditor.auditordashboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.forms.AuditorDashboard;
import acme.roles.Auditor;

@Service
public class AuditorDashboardShowService extends AbstractService<Auditor, AuditorDashboard> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AuditorDashboardRepository repository;

	// AbstractService interface ----------------------------------------------


	@Override
	public void authorise() {
		boolean status;

		status = super.getRequest().getPrincipal().hasRole(Auditor.class);

		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {
		AuditorDashboard dashboard;

		int totalNumberOfCodeAuditsWithTypeStatic;
		int totalNumberOfCodeAuditsWithTypeDynamic;

		double averageNumberOfAuditRecords;
		double deviationNumberOfAuditRecords;
		int minimumNumberOfAuditRecords;
		int maximumNumberOfAuditRecords;

		double averageTimeOfPeriodLegthOfAuditRecord;
		double deviationTimeOfPeriodLegthOfAuditRecord;
		int minimumTimeOfPeriodLegthOfAuditRecord;
		int maximumTimeOfPeriodLegthOfAuditRecord;

		totalNumberOfCodeAuditsWithTypeStatic = this.repository.totalNumberOfCodeAuditsWithTypeStatic(super.getRequest().getPrincipal().getActiveRoleId());
		totalNumberOfCodeAuditsWithTypeDynamic = this.repository.totalNumberOfCodeAuditsWithTypeDynamic(super.getRequest().getPrincipal().getActiveRoleId());

		//		averageNumberOfAuditRecords = this.repository.averageNumberOfAuditRecords(super.getRequest().getPrincipal().getActiveRoleId());
		//		deviationNumberOfAuditRecords = this.repository.deviationNumberOfAuditRecords(super.getRequest().getPrincipal().getActiveRoleId());
		//		minimumNumberOfAuditRecords = this.repository.minimumNumberOfAuditRecords(super.getRequest().getPrincipal().getActiveRoleId());
		//		maximumNumberOfAuditRecords = this.repository.maximumNumberOfAuditRecords(super.getRequest().getPrincipal().getActiveRoleId());

		averageTimeOfPeriodLegthOfAuditRecord = this.repository.averageTimeOfPeriodLegthOfAuditRecord(super.getRequest().getPrincipal().getActiveRoleId());
		deviationTimeOfPeriodLegthOfAuditRecord = this.repository.deviationTimeOfPeriodLegthOfAuditRecord(super.getRequest().getPrincipal().getActiveRoleId());
		minimumTimeOfPeriodLegthOfAuditRecord = this.repository.minimumTimeOfPeriodLegthOfAuditRecord(super.getRequest().getPrincipal().getActiveRoleId());
		maximumTimeOfPeriodLegthOfAuditRecord = this.repository.maximumTimeOfPeriodLegthOfAuditRecord(super.getRequest().getPrincipal().getActiveRoleId());

		dashboard = new AuditorDashboard();
		dashboard.setTotalNumberOfCodeAuditsWithTypeStatic(totalNumberOfCodeAuditsWithTypeStatic);
		dashboard.setTotalNumberOfCodeAuditsWithTypeDynamic(totalNumberOfCodeAuditsWithTypeDynamic);
		//		dashboard.setAverageNumberOfAuditRecords(averageNumberOfAuditRecords);
		//		dashboard.setDeviationNumberOfAuditRecords(deviationNumberOfAuditRecords);
		//		dashboard.setMinimumNumberOfAuditRecords(minimumNumberOfAuditRecords);
		//		dashboard.setMaximumNumberOfAuditRecords(maximumNumberOfAuditRecords);
		dashboard.setAverageTimeOfPeriodLegthOfAuditRecord(averageTimeOfPeriodLegthOfAuditRecord);
		dashboard.setDeviationTimeOfPeriodLegthOfAuditRecord(deviationTimeOfPeriodLegthOfAuditRecord);
		dashboard.setMinimumTimeOfPeriodLegthOfAuditRecord(minimumTimeOfPeriodLegthOfAuditRecord);
		dashboard.setMaximumTimeOfPeriodLegthOfAuditRecord(maximumTimeOfPeriodLegthOfAuditRecord);

		super.getBuffer().addData(dashboard);
	}

	@Override
	public void unbind(final AuditorDashboard object) {
		Dataset dataset;

		dataset = super.unbind(object, //
			"totalNumberOfCodeAuditsWithTypeStatic", "totalNumberOfCodeAuditsWithTypeDynamic", //
			"averageNumberOfAuditRecords", "deviationNumberOfAuditRecords", //
			"minimumNumberOfAuditRecords", "maximumNumberOfAuditRecords", "averageTimeOfPeriodLegthOfAuditRecord", "deviationTimeOfPeriodLegthOfAuditRecord", //
			"minimumTimeOfPeriodLegthOfAuditRecord", "maximumTimeOfPeriodLegthOfAuditRecord");

		super.getResponse().addData(dataset);
	}
}
