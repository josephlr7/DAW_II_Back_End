package cibertec.pe.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DataDni {

    @JsonProperty("nombre")
    @JsonAlias({"nombres"})
    private String nombre;

    @JsonProperty("apellido_paterno")
    private String apellido_paterno;

    @JsonProperty("apellido_materno")
    private String apellido_materno;

    @JsonProperty("nombre_completo")
    private String nombre_completo;

    @JsonProperty("direccion")
    @JsonAlias({"direccion_completa"})
    private String direccion;
}

