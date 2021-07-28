package com.example.tallermapa;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

public class AeropuertoController {
    private BaseDatos bd;
    private Context c;
    public AeropuertoController(Context c) {
        this.bd = new BaseDatos(c,1);
        this.c = c;
    }

    public void agregarAeropuerto(Aeropuerto a){
        try {
            SQLiteDatabase sql = bd.getWritableDatabase();
            ContentValues valores = new ContentValues();
            valores.put(DefDB.col_codigo,a.getCodigo());
            valores.put(DefDB.col_nombre,a.getNombre());
            valores.put(DefDB.col_pais,a.getPais());
            valores.put(DefDB.col_ciudad,a.getCiudad());
            valores.put(DefDB.col_direccion,a.getDireccion());
            valores.put(DefDB.col_latitud,a.getLatitud());
            valores.put(DefDB.col_longitud,a.getLongitud());
            long id = sql.insert(DefDB.tabla_est, null, valores);
            Toast.makeText(c, "Aeropuerto registrado", Toast.LENGTH_LONG).show();
        }
        catch (Exception ex){
            Toast.makeText(c, "Error agregando Aeropuerto " + ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public boolean buscarAeropuerto(Aeropuerto a){
        String args[] = new String[] {a.getCodigo()};
        SQLiteDatabase sql = bd.getReadableDatabase();
        Cursor e = sql.query(DefDB.tabla_est,null,"codigo=?",args,null,null,null);
        if (e.getCount()>0){
            bd.close();
            return true;
        }
        else{
            bd.close();
            return false;
        }
    }
    public Cursor allAeropuerto(){
        try{
            SQLiteDatabase sql = bd.getReadableDatabase();
            Cursor cur = sql.rawQuery("select codigo as _id , nombre, pais,ciudad, direccion, latitud, longitud from Aeropuerto order by " + DefDB.col_codigo, null);
            return cur;
        }
        catch (Exception ex){
            Toast.makeText(c, "Error consulta Restaurantes " + ex.getMessage(), Toast.LENGTH_LONG).show();
            return null;
        }
    }
}
