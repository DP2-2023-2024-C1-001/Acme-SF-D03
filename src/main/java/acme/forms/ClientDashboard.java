
package acme.forms;

import acme.client.data.AbstractForm;
import acme.client.data.datatypes.Money;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientDashboard extends AbstractForm {

	// Serialisation identifier -----------------------------------------------

	private static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	int							totalNumberOfProgressLogsWithCompletenessRateBelow25Percent;
	int							totalNumberOfProgressLogsWithCompletenessRateBetween25And50Percent;
	int							totalNumberOfProgressLogsWithCompletenessRateBetween50And75Percent;
	int							totalNumberOfProgressLogsWithCompletenessRateAbove75Percent;

	Money						averageBudgetOfContracts;
	Money						deviationBudgetOfContracts;
	Money						minimumBudgetOfContracts;
	Money						maximumBudgetOfContracts;

	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------

}
