package Modelo;

public class Producto {
    private int idProducto;
    private String nombres;
    private double precio;
    private int stock;

  
    public Producto(int idProducto, String nombres, double precio, int stock) {
        this.idProducto = idProducto;
        this.nombres = nombres;
        this.precio = precio;
        this.stock = stock;
    }

   
    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}