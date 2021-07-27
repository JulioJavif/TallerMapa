package com.example.tallermapa;

public class Aeropuerto {
    private String codigo;
    private String nombre;
    private String pais;
    private String ciudad;
    private String direccion;
    private String latitud;
    private  String longitud;

    public Aeropuerto(String codigo, String nombre, String pais, String ciudad, String direccion, String latitud, String longitud) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.pais = pais;
        this.ciudad = ciudad;
        this.direccion = direccion;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public String getCodigo() { return codigo; }

    public String getNombre() { return nombre; }

    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getPais() { return pais; }

    public void setPais(String pais) { this.pais = pais; }

    public String getCiudad() { return ciudad; }

    public void setCiudad(String ciudad) { this.ciudad = ciudad; }

    public String getDireccion() { return direccion; }

    public void setDireccion(String direccion) { this.direccion = direccion; }

    public String getLatitud() { return latitud; }

    public void setLatitud(String latitud) { this.latitud = latitud; }

    public String getLongitud() { return longitud; }

    public void setLongitud(String longitud) { this.longitud = longitud; }
}
