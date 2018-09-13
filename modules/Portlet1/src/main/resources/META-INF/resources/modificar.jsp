<%@ include file="init.jsp" %>

<h1>Modificar Factura</h1>

<portlet:renderURL var="lista">
	<portlet:param name="jspPage" value="/lista.jsp" />
</portlet:renderURL>

<p>
	<a href='${lista }'>Volver</a>
</p>

<portlet:actionURL name="modificaFactura" var="modificaFacturaURL"></portlet:actionURL>

<%
	String num = renderRequest.getParameter("num");
	String cliente = renderRequest.getParameter("cliente");
	String importe = renderRequest.getParameter("importe");
%>

<form method="post" action="${altaFacturaURL}">
	<div class="form-group col-sm-4">
		<label for="usr">num:</label>
		<input type="text" class="form-control" name="<portlet:namespace />num" value="<%= num %>" readonly>
	</div>
	
	<div class="form-group col-sm-4">
		<label for="usr">cliente:</label>
		<input type="text" class="form-control" name="<portlet:namespace />cliente" value="<%= cliente %>">
	</div>
	
	<div class="form-group col-sm-4">
		<label for="usr">importe:</label>
		<input type="text" class="form-control" name="<portlet:namespace />importe" value="<%= importe %>">
	</div>
	
	<div class="text-center">
		<button type="submit" class="btn btn-success"><liferay-ui:message key="portlet1.lista.factura.actualizar"/></button>
	</div>
	
</form>