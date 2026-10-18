package cibertec.pe.client;

import cibertec.pe.dto.ClientIdentityDto;

public interface IOrderIndentityClient {
    ClientIdentityDto obtenerCliente(String numeroDocumento);
}
