<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:form>
	<acme:input-textbox code="manager.project.form.label.code" path="code"/>
	<acme:input-textbox code="manager.project.form.label.title" path="title"/>
	<acme:input-textarea code="manager.project.form.label.projectAbstract" path="projectAbstract"/>
	<acme:input-checkbox code="manager.project.label.indication" path="indication"/>
	<acme:input-money code="manager.project.form.label.cost" path="cost"/>
	<acme:input-url code="manager.project.form.label.link" path="link"/>

	<acme:button code="manager.project.form.button.user-stories-list" action="/manager/user-story/list?projectId=${id}"/>
	
	<jstl:choose>	 
		<jstl:when test="${_command == 'create'}">
			<acme:submit code="manager.project.list.button.create" action="/manager/project/create"/>
		</jstl:when>
	</jstl:choose>	
	
</acme:form>