<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:form>
	<acme:input-select code="manager.user-story-project.form.label.project-title" path="projectTitle" choices="${projectChoices}"/>
	<acme:input-select code="manager.user-story-project.form.label.user-story-title" path="userStoryTitle" choices="${userStoryChoices}"/>
</acme:form>