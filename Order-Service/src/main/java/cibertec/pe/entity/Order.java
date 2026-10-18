package cibertec.pe.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "tbl_Order")

public class Order {

    @Id
    @GeneratedValue
    private Long id;

    private String numeroDocumentoCliente;
    private String nombreCliente;
    private String direccionCliente;

    private String producto;
    private Double total;


}
