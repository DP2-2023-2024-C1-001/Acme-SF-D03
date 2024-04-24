<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:form>
	<acme:input-select code="manager.user-story-project.form.label.project-title" path="projectTitle" choices="${projectChoices}"/>
	<acme:input-select code="manager.user-story-project.form.label.user-story-title" path="userStoryTitle" choices="${userStoryChoices}"/>
	<jstl:choose>
		<jstl:when test="${acme:anyOf(_command, 'show|delete')}">
			<acme:submit code="manager.user-story-project.form.button.delete" action="/manager/user-story-project/delete"/>
		</jstl:when>
		<jstl:when test="${_command == 'create'}">
			<acme:button code="manager.user-story-project.list.button.create" action="/manager/user-story-project/create"/>
		</jstl:when>		
	</jstl:choose>	
</acme:form>