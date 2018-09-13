package com.fihoca.formacion.portlet;

import com.fihoca.formacion.constants.Portlet1PortletKeys;
import com.liferay.portal.kernel.io.OutputStreamWriter;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.servlet.HttpHeaders;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Hashtable;
import java.util.Vector;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.osgi.service.component.annotations.Component;

/**
 * @author User
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=Fihoca",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + Portlet1PortletKeys.Portlet1,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class Portlet1Portlet extends MVCPortlet {
	
	private static Vector<Hashtable<String,String>> FACTURAS = new Vector<Hashtable<String,String>>();
			
	static {
		Hashtable<String,String> facturaIbm = new Hashtable<String,String>();
		facturaIbm.put("num", "2018/01");
		facturaIbm.put("cliente", "IBM");
		facturaIbm.put("importe", "18.000");
		FACTURAS.add(facturaIbm);
		
		Hashtable<String,String> facturaGoogle = new Hashtable<String,String>();
		facturaGoogle.put("num", "2018/02");
		facturaGoogle.put("cliente", "Google");
		facturaGoogle.put("importe", "28.000");
		FACTURAS.add(facturaGoogle);
		
		Hashtable<String,String> facturaNetflix = new Hashtable<String,String>();
		facturaNetflix.put("num", "2018/03");
		facturaNetflix.put("cliente", "Netflix");
		facturaNetflix.put("importe", "12.000");
		FACTURAS.add(facturaNetflix);
			
	}
	
	
	@Override
	public void serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws IOException, PortletException {
		
		resourceResponse.setContentType("application/csv");
		resourceResponse.addProperty(HttpHeaders.CONTENT_DISPOSITION, "attachment: filename=listado.csv");
		
		try(OutputStreamWriter osw = new OutputStreamWriter(resourceResponse.getPortletOutputStream());
				CSVPrinter printer = new CSVPrinter(osw, CSVFormat.DEFAULT.withHeader("num","cliente","importe"))){
		
			for(Hashtable<String, String> factura : FACTURAS) {
				printer.printRecord(factura.get("num"), factura.get("cliente"), factura.get("importe"));
			}
			printer.flush();
			
		}catch(UnsupportedEncodingException e) {
			System.out.println(e.getMessage());
			throw new IOException("Error with encoding", e);
		}
		super.serveResource(resourceRequest, resourceResponse);
	}
	
	public void altaFactura(ActionRequest actionRequest, ActionResponse actionResponse)
		throws IOException, PortletException{
		
		String num = actionRequest.getParameter("num");
		String cliente = actionRequest.getParameter("cliente");
		String importe = actionRequest.getParameter("importe");
		
		Hashtable<String,String> facturaNueva = new Hashtable<String,String>();
		facturaNueva.put("num", num);
		facturaNueva.put("cliente", cliente);
		facturaNueva.put("importe", importe);
		FACTURAS.add(facturaNueva);
	}
	
	public void modificaFactura(ActionRequest actionRequest, ActionResponse actionResponse)
			throws IOException, PortletException{
			
			String num = actionRequest.getParameter("num");
			String cliente = actionRequest.getParameter("cliente");
			String importe = actionRequest.getParameter("importe");
			
			Hashtable<String,String> facturaNueva = new Hashtable<String,String>();
			facturaNueva.put("num", num);
			facturaNueva.put("cliente", cliente);
			facturaNueva.put("importe", importe);
			FACTURAS.add(facturaNueva);
		}
	
	
	
	@Override
	public void render(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		String jspPage;
		
		if((jspPage = renderRequest.getParameter("jspPage")) != null) {
			if(jspPage.equals("/lista.jsp")) {
				renderRequest.setAttribute("facturas", FACTURAS);
			}
		}
		
		super.render(renderRequest, renderResponse);
	}
}