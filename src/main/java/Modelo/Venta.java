package Modelo;

import java.util.ArrayList;
import java.util.List;

public class Venta {
    private int idVenta;
    private List<DetalleVenta> detalles;

    
    public Venta() {
        this.detalles = new ArrayList<>();
    }

    
    public Venta(int idVenta) {
        this.idVenta = idVenta;
        this.detalles = new ArrayList<>();
    }

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public List<DetalleVenta> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetalleVenta> detalles) {
        this.detalles = detalles;
    }

    public void agregarDetalle(DetalleVenta detalle) {
        this.detalles.add(detalle);
    }

    public double calcularTotal() {
        return detalles.stream().mapToDouble(DetalleVenta::getSubtotal).sum();
    }
}
