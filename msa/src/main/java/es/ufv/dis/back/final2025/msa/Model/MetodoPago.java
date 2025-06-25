// MetodoPago.java
package es.ufv.dis.back.final2025.msa.Model;

public class MetodoPago {
    private long numeroTarjeta;
    private String nombreAsociado;

    // Getters y Setters
    public long getNumeroTarjeta() { return numeroTarjeta; }
    public void setNumeroTarjeta(long numeroTarjeta) { this.numeroTarjeta = numeroTarjeta; }
    public String getNombreAsociado() { return nombreAsociado; }
    public void setNombreAsociado(String nombreAsociado) { this.nombreAsociado = nombreAsociado; }
}