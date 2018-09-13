<%@ include file="init.jsp" %>

<h1>Alta Factura</h1>

<p>
	<a href='<portlet:renderURL/>'>Volver</a>
</p>

<portlet:actionURL name="altaFactura" var="altaFacturaURL"></portlet:actionURL>

<form method="post" action="${altaFacturaURL}">
	<div class="form-group col-sm-4">
		<label for="usr">num:</label>
		<input type="text" class="form-control" name="<portlet:namespace />num">
	</div>
	
	<div class="form-group col-sm-4">
		<label for="usr">cliente:</label>
		<input type="text" class="form-control" name="<portlet:namespace />cliente">
	</div>
	
	<div class="form-group col-sm-4">
		<label for="usr">importe:</label>
		<input type="text" class="form-control" name="<portlet:namespace />importe">
	</div>
	
	<div class="text-center">
		<button type="submit" class="btn btn-success">Submit</button>
	</div>
	
</form>