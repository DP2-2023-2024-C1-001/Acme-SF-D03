
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

	Double						averageNumberOfAuditRecords;
	Double						deviationNumberOfAuditRecords;
	int							minimumNumberOfAuditRecords;
	int							maximumNumberOfAuditRecords;

	Double						averageTimeOfPeriodLegthOfAuditRecord;
	Double						deviationTimeOfPeriodLegthOfAuditRecord;
	double						minimumTimeOfPeriodLegthOfAuditRecord;
	double						maximumTimeOfPeriodLegthOfAuditRecord;

	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------

}
