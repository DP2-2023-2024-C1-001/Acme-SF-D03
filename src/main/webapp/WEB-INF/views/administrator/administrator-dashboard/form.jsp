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
	<acme:message code="administrator.administrator-dashboard.form.title.count-users"/>
</h2>

<table class="table table-sm">
	<tr>
		<th scope="row">
			<acme:message code="administrator.administrator-dashboard.form.label.count-administrator"/>
		</th>
		<td>
			<acme:print value="${totalNumberOfPrincipalsWithRoleAdministrator}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.administrator-dashboard.form.label.count-auditor"/>
		</th>
		<td>
			<acme:print value="${totalNumberOfPrincipalsWithRoleAuditor}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.administrator-dashboard.form.label.count-client"/>
		</th>
		<td>
			<acme:print value="${totalNumberOfPrincipalsWithRoleClient}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.administrator-dashboard.form.label.count-consumer"/>
		</th>
		<td>
			<acme:print value="${totalNumberOfPrincipalsWithRoleConsumer}"/>
		</td>
	</tr>
		<tr>
		<th scope="row">
			<acme:message code="administrator.administrator-dashboard.form.label.count-developer"/>
		</th>
		<td>
			<acme:print value="${totalNumberOfPrincipalsWithRoleDeveloper}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.administrator-dashboard.form.label.count-manager"/>
		</th>
		<td>
			<acme:print value="${totalNumberOfPrincipalsWithRoleManager}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.administrator-dashboard.form.label.count-provider"/>
		</th>
		<td>
			<acme:print value="${totalNumberOfPrincipalsWithRoleProvider}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.administrator-dashboard.form.label.count-sponsor"/>
		</th>
		<td>
			<acme:print value="${totalNumberOfPrincipalsWithRoleSponsor}"/>
		</td>
	</tr>
</table>

<h2>
	<acme:message code="administrator.administrator-dashboard.form.title.ratios"/>
</h2>

<table class="table table-sm">
	<tr>
		<th scope="row">
			<acme:message code="administrator.administrator-dashboard.form.label.notice-ratio"/>
		</th>
		<td>
			<acme:print value="${ratioOfNoticesWithEmailAndLink}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.administrator-dashboard.form.label.critical-ratio"/>
		</th>
		<td>
			<acme:print value="${ratioOfCriticalObjectives}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.administrator-dashboard.form.label.noncritical-ratio"/>
		</th>
		<td>
			<acme:print value="${ratioOfNonCriticalObjectives}"/>
		</td>
	</tr>

</table>
<h2>
	<acme:message code="administrator.administrator-dashboard.form.title.riskValue"/>
</h2>

<table class="table table-sm">
	<tr>
		<th scope="row">
			<acme:message code="administrator.administrator-dashboard.form.label.max-risk"/>
		</th>
		<td>
			<acme:print value="${maximumOfTheValueInTheRisks}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.administrator-dashboard.form.label.min-risk"/>
		</th>
		<td>
			<acme:print value="${minimumOfTheValueInTheRisks}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.administrator-dashboard.form.label.avg-risk"/>
		</th>
		<td>
			<acme:print value="${averageOfTheValueInTheRisks}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.administrator-dashboard.form.label.stddev-risk"/>
		</th>
		<td>
			<acme:print value="${deviationOfTheValueInTheRisks}"/>
		</td>
	</tr>

</table>



<acme:return/>

