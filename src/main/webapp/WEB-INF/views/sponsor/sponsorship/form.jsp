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



<acme:form>
	<acme:input-textbox code="sponsor.sponsorship.form.label.code" path="code"/>	
	<acme:input-moment code="sponsor.sponsorship.form.label.moment" path="moment" />
	<acme:input-money code="sponsor.sponsorship.form.label.amount" path="amount"/>
	<acme:input-textbox code="sponsor.sponsorship.form.label.type" path="type"/>
	<acme:input-email code="sponsor.sponsorship.form.label.email" path="email"/>
	<acme:input-url code="sponsor.sponsorship.form.label.link" path="link"/>
	<acme:input-integer code="sponsor.sponsorship.form.label.duration" path="duration"/>
	<acme:input-select code="sponsor.sponsorship.form.label.project" path="project" choices= "${project}"/>
	<jstl:choose>	 
		<jstl:when test="${_command == 'show' }">
			<acme:button code="sponsor.sponsorship.form.button.invoices" action="/sponsor/invoice/list?masterId=${id}"/>			
		</jstl:when>	
	</jstl:choose>
</acme:form>