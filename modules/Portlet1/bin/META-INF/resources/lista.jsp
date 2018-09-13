<%@include file="init.jsp" %>
<h1>Lista Facturas</h1>

<p>
	<a href='<portlet:renderURL/>'>Volver</a>
</p>


<div class="row">
	<div class="col-sm-3" style="background-color:lavender;">
		<liferay-ui:message key="portlet1.lista.factura.num"/>
	</div>
	<div class="col-sm-3" style="background-color:lavender;">
		<liferay-ui:message key="portlet1.lista.factura.cliente"/>
	</div>
	<div class="col-sm-3" style="background-color:lavender;">
		<liferay-ui:message key="portlet1.lista.factura.importe"/>
	</div>
	<div class="col-sm-3" style="background-color:lavender;">
		<liferay-ui:message key="portlet1.lista.factura.actualizar"/>
	</div>
</div>

<c:forEach var='factura' items='${facturas}'>
	<portlet:renderURL var="modifyURL">
		<portlet:param name="num" value="${factura.num}"/>
		<portlet:param name="cliente" value="${factura.cliente}"/>
		<portlet:param name="importe" value="${factura.importe}"/>
		<portlet:param name="jspPage" value="/modificar.jsp" />
	</portlet:renderURL>

	<div class="row">
		<div class="col-sm-3" style="background-color:honeydew;">${factura.num}</div>
		<div class="col-sm-3" style="background-color:honeydew;">${factura.cliente}</div>
		<div class="col-sm-3" style="background-color:honeydew;">${factura.importe}</div>
		<div class="col-sm-3" style="background-color:honeydew;">
			<a class='btn btn-primary m-1' href='${modifyURL}' role='button'><liferay-ui:message key="portlet1.lista.factura.actualizar"/></a>
		</div>
	</div>
</c:forEach>

<div style='margin-top:15px;'>
	<portlet:resourceURL var="exportCSV"/>
	<a href='${exportCSV}'>Exportar a CSV</a>
</div>

