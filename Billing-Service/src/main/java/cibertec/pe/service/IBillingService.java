package cibertec.pe.service;

import cibertec.pe.model.Factura;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;

@WebService
public interface IBillingService {

    @WebMethod
    Factura emitirComprobante(Factura factura);

    @WebMethod
    Factura buscarComprobante(int codigo);
}
