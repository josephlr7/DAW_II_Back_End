package cibertec.pe.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DataRuc {

    @JsonProperty("nombre_o_razon_social")
    @JsonAlias({"razon_social", "nombre_completo"})
    private String razon_social;

    @JsonProperty("direccion")
    @JsonAlias({"direccion_completa", "direccion_fiscal"})
    private String direccion;
}

