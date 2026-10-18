package cibertec.pe.controller;

import cibertec.pe.entity.ClientIdentity;
import cibertec.pe.service.IIDentityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/identity")
public class IdentityController {

    @Autowired
    private IIDentityService identityService;

    @GetMapping("/dni/{dni}")
    public ResponseEntity<ClientIdentity> getDni(@PathVariable String dni) {
        return ResponseEntity.ok(identityService.consultarReniec(dni));
    }

    @GetMapping("/ruc/{ruc}")
    public ResponseEntity<ClientIdentity> getRuc(@PathVariable String ruc) {
        return ResponseEntity.ok(identityService.consultarSunat(ruc));
    }
}
