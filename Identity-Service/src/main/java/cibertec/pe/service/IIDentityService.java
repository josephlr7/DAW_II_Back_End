package cibertec.pe.service;

import cibertec.pe.entity.ClientIdentity;

public interface IIDentityService {
    ClientIdentity consultarReniec(String dni);
    ClientIdentity consultarSunat(String ruc);
}
