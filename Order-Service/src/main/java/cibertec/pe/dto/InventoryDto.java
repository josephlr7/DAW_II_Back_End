package cibertec.pe.dto;

public class InventoryDto {
    private String producto;
    private Integer stock;

    public InventoryDto() {}

    public InventoryDto(String producto, Integer stock) {
        this.producto = producto;
        this.stock = stock;
    }

    public String getProducto() { return producto; }
    public void setProducto(String producto) { this.producto = producto; }
    public Integer getStock() { return stock; }
    public void setStock(Integer stock) { this.stock = stock; }
}
