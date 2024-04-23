<%@page%>




<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>

<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:list>
	<acme:list-column code="manager.project.list.label.code" path="code" width="20%"/>
	<acme:list-column code="manager.project.list.label.title" path="title" width="40%"/>
	<acme:list-column code="manager.project.list.label.cost" path="cost" width="20%"/>
	<acme:list-column code="manager.project.list.label.draftMode" path="draftMode" width="20%"/>
</acme:list>

<jstl:if test="${_command == 'list'}">
	<acme:button code="manager.project.list.button.create" action="/manager/project/create"/>
</jstl:if>