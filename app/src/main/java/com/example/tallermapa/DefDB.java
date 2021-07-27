package com.example.tallermapa;

public class DefDB {
    public static final String nameDB = "Aeropuerto";

    public static final String tabla_est = "Aeropuerto";
    public static final String col_codigo = "codigo";
    public static final String col_nombre = "nombre";
    public static final String col_pais = "pais";
    public static final String col_ciudad = "ciudad";
    public static final String col_direccion= "direccion";
    public static final String col_latitud = "latitud";
    public static final String col_longitud= "longitud";
    public static final String create_tabla_est = "CREATE TABLE IF NOT EXISTS " + DefDB.tabla_est + " ( " +
            DefDB.col_codigo + " text primary key," +
            DefDB.col_nombre + " text," +
            DefDB.col_pais + " text," +
            DefDB.col_ciudad + " text," +
            DefDB.col_direccion + " text," +
            DefDB.col_latitud + " text," +
            DefDB.col_longitud + " text" +
            ");";
}
