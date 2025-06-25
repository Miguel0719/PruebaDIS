package es.ufv.dis.back.final2025.msa.model;

public class Usuario {
    private String id;
    private String nombre;
    private String apellidos;
    private String nif;
    private Direccion direccion;
    private String email;
    private MetodoPago metodoPago; // Cambiado de List<MetodoPago> a objeto único

    // Constructor, getters y setters
    public Usuario() {}

    // Getters y Setters (requeridos para GSON)
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getApellidos() { return apellidos; }
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }
    public String getNif() { return nif; }
    public void setNif(String nif) { this.nif = nif; }
    public Direccion getDireccion() { return direccion; }
    public void setDireccion(Direccion direccion) { this.direccion = direccion; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public MetodoPago getMetodoPago() { return metodoPago; }
    public void setMetodoPago(MetodoPago metodoPago) { this.metodoPago = metodoPago; }
}

class Direccion {
    private String calle;
    private int numero; // Cambiado a int para coincidir con el JSON
    private String codigoPostal;
    private String pisoLetra;
    private String ciudad;

    // Getters y Setters
}

class MetodoPago {
    private long numeroTarjeta; // Cambiado a long por el número grande
    private String nombreAsociado;

    // Getters y Setters
}