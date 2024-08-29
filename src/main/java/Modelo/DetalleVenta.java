package Modelo;

public class DetalleVenta {
    private int item; 
    private int idProducto; 
    private String descripcionP; 
    private double precio; 
    private int cantidad; 
    private double subtotal; 

    
    public DetalleVenta(int item, int idProducto, String descripcionP, double precio, int cantidad, double subtotal) {
        this.item = item;
        this.idProducto = idProducto;
        this.descripcionP = descripcionP;
        this.precio = precio;
        this.cantidad = cantidad;
        this.subtotal = subtotal;
    }

    
    public int getItem() {
        return item;
    }

    public void setItem(int item) {
        this.item = item;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getDescripcionP() {
        return descripcionP;
    }

    public void setDescripcionP(String descripcionP) {
        this.descripcionP = descripcionP;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }
}
