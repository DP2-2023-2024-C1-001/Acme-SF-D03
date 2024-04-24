
package acme.forms;

import acme.client.data.AbstractForm;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuditorDashboard extends AbstractForm {

	// Serialisation identifier -----------------------------------------------

	private static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	int							totalNumberOfCodeAuditsWithTypeStatic;
	int							totalNumberOfCodeAuditsWithTypeDynamic;

	double						averageNumberOfAuditRecords;
	double						deviationNumberOfAuditRecords;
	int							minimumNumberOfAuditRecords;
	int							maximumNumberOfAuditRecords;

	double						averageTimeOfPeriodLegthOfAuditRecord;
	double						deviationTimeOfPeriodLegthOfAuditRecord;
	int							minimumTimeOfPeriodLegthOfAuditRecord;
	int							maximumTimeOfPeriodLegthOfAuditRecord;

	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------

}
