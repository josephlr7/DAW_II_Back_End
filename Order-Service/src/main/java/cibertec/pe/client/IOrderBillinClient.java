package cibertec.pe.client;

import cibertec.pe.dto.FacturaDto;

public interface IOrderBillinClient {
    FacturaDto emitirFactura(FacturaDto factura);
}
