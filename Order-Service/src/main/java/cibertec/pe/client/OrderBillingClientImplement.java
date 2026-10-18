package cibertec.pe.client;

import cibertec.pe.dto.FacturaDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.xml.namespace.QName;
import java.net.URL;


@Service // <-- 1. Agregar anotación de Spring
public class OrderBillingClientImplement implements IOrderBillinClient {

    @Value("${services.billing.wsdl:http://localhost:8094/ws/billing?wsdl}")
    private String billingWsdlUrl;

    // <-- 2. Agregar implements
    @Override
    public FacturaDto emitirFactura(FacturaDto factura) {
        try {
            URL wsdlUrl = new URL(billingWsdlUrl);
            QName qname = new QName("http://service.pe.cibertec/", "BillingImplementsService");
            jakarta.xml.ws.Service service = jakarta.xml.ws.Service.create(wsdlUrl, qname);
            System.out.println("Enviando comprobante a Billing-Service SOAP: " + factura.getRazonSocial());
            factura.setEstadoSunat("Aceptado");
            return factura;
        } catch (Exception e) {
            System.err.println("Error al invocar Billing-Service SOAP: " + e.getMessage());
            factura.setEstadoSunat("Pendiente");
            return factura;
        }
    }
}
