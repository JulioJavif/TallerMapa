package com.example.tallermapa;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.util.ArrayList;

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

    public ArrayList AllAereopuerto(){
        ArrayList array = new ArrayList();

        SQLiteDatabase sql = bd.getReadableDatabase();
        Cursor c = sql.query(DefDB.tabla_est, new String[]{"codigo", "nombre", "pais", "ciudad"},
                null, null, null, null, "codigo ASC");
        if (c.moveToFirst()){
            do {
                array.add(c.getString(1)+" - ("+c.getString(3)+","+c.getString(2)+")");
            }while (c.moveToNext());
        }
        return array;
    }

    public ArrayList ListaIDs(){
        SQLiteDatabase sql = bd.getReadableDatabase();
        Cursor c = sql.query(DefDB.tabla_est, new String[]{"codigo"},
                null, null, null, null, "codigo ASC");
        ArrayList<String> lista = new ArrayList<>();
        if (c.moveToFirst()){
            do {
                lista.add(c.getString(0));
            }while (c.moveToNext());
        }
        return lista;
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

    public Cursor getAeropuertoByID(String ID){
        SQLiteDatabase sql = bd.getReadableDatabase();
        Cursor c = sql.query(DefDB.tabla_est, new String[]{"codigo", "nombre", "pais", "ciudad", "direccion", "latitud", "longitud"},
                "codigo = ?", new String[]{ID}, null, null, null);
        ArrayList<String> lista = new ArrayList<>();
        if (c != null){
            return c;
        }
        return null;
    }

    public boolean EditAeropuerto(Aeropuerto editAeropuerto){
        SQLiteDatabase sql = bd.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("nombre", editAeropuerto.getNombre());
        cv.put("pais", editAeropuerto.getPais());
        cv.put("ciudad", editAeropuerto.getCiudad());
        cv.put("direccion", editAeropuerto.getDireccion());
        cv.put("latitud", editAeropuerto.getLatitud());
        cv.put("longitud", editAeropuerto.getLongitud());
        int result = sql.update("Aeropuerto", cv, "codigo=?", new String[]{editAeropuerto.getCodigo()});

        if (result==1){
            return true;
        }

        return false;
    }

    public boolean EliminarAeropuertoByID(String codigo){
        SQLiteDatabase sql = bd.getWritableDatabase();
        int result = sql.delete("Aeropuerto", "codigo=?", new String[]{codigo});
        if (result==1){
            return true;
        }
        return false;
    }
}
