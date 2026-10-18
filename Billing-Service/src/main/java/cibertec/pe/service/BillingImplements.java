package cibertec.pe.service;

import cibertec.pe.model.Factura;
import cibertec.pe.repository.IBillingRepository;
import jakarta.jws.WebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@WebService(endpointInterface = "cibertec.pe.service.IBillingService")
public class BillingImplements implements IBillingService {

    @Autowired
    private IBillingRepository bRepo;

    @Override
    public Factura emitirComprobante(Factura factura) {
        factura.setEstadoSunat("Aceptado");
        return bRepo.save(factura);
    }

    @Override
    public Factura buscarComprobante(int codigo) {
        return bRepo.findById(codigo).orElse(null);
    }
}
