<%--
- form.jsp
-
- Copyright (C) 2012-2024 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<h2>
	<acme:message code="client.client-dashboard.form.title.progress-log-indicators"/>
</h2>

<table class="table table-sm">
	<tr>
		<th scope="row">
			<acme:message code="client.client-dashboard.form.label.progress-log-below-25"/>
		</th>
		<td>
			<acme:print value="${totalNumberOfProgressLogsWithCompletenessRateBelow25Percent}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="client.client-dashboard.form.label.progress-log-between-25-50"/>
		</th>
		<td>
			<acme:print value="${totalNumberOfProgressLogsWithCompletenessRateBetween25And50Percent}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="client.client-dashboard.form.label.progress-log-between-50-75"/>
		</th>
		<td>
			<acme:print value="${totalNumberOfProgressLogsWithCompletenessRateBetween50And75Percent}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="client.client-dashboard.form.label.progress-log-above-75"/>
		</th>
		<td>
			<acme:print value="${totalNumberOfProgressLogsWithCompletenessRateAbove75Percent}"/>
		</td>
	</tr>
</table>

<h2>
	<acme:message code="client.client-dashboard.form.title.budget-indicators"/>
</h2>

<table class="table table-sm">
	<tr>
		<th scope="row">
			<acme:message code="client.client-dashboard.form.label.average-budget"/>
		</th>
		<td>
			<acme:print value="${averageBudgetOfContracts}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="client.client-dashboard.form.label.deviation-budget"/>
		</th>
		<td>
			<acme:print value="${deviationBudgetOfContracts}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="client.client-dashboard.form.label.minimum-budget"/>
		</th>
		<td>
			<acme:print value="${minimumBudgetOfContracts}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="client.client-dashboard.form.label.maximum-budget"/>
		</th>
		<td>
			<acme:print value="${maximumBudgetOfContracts}"/>
		</td>
	</tr>
	
</table>


<acme:return/>

