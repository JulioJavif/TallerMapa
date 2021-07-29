package com.example.tallermapa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InfoAereopuerto extends AppCompatActivity {

    Button btnmapa, btnguardar, btneliminar;
    EditText edtcodigo, edtnombre, edtpais, edtciudad, edtdireccion, edtlat, edtlon;
    AeropuertoController ac;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_aereopuerto);

        String ID = getIntent().getStringExtra("ID");

        btnmapa = findViewById(R.id.btnVerMapa);
        btnguardar = findViewById(R.id.btnGuardarCambios);
        btneliminar = findViewById(R.id.btnEliminar);

        edtcodigo = findViewById(R.id.etxtCodigo);
        edtcodigo.setEnabled(false);
        edtnombre = findViewById(R.id.etxtNombre);
        edtpais = findViewById(R.id.etxtPais);
        edtciudad = findViewById(R.id.etxtCiudad);
        edtdireccion = findViewById(R.id.etxtDireccion);
        edtlat = findViewById(R.id.etxtLatitud);
        edtlon = findViewById(R.id.etxtLongitud);

        ac = new AeropuertoController(this);
        Cursor datos = ac.getAeropuertoByID(ID);

        if (datos.moveToNext()){
            edtcodigo.setText(datos.getString(0));
            edtnombre.setText(datos.getString(1));
            edtpais.setText(datos.getString(2));
            edtciudad.setText(datos.getString(3));
            edtdireccion.setText(datos.getString(4));
            edtlat.setText(datos.getString(5));
            edtlon.setText(datos.getString(6));
        }

        btnguardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cod = edtcodigo.getText().toString();
                String nombre = edtnombre.getText().toString();
                String pais = edtpais.getText().toString();
                String ciudad = edtciudad.getText().toString();
                String direccion = edtdireccion.getText().toString();
                String lat = edtlat.getText().toString();
                String lon = edtlon.getText().toString();

                Aeropuerto editAeropuerto = new Aeropuerto(cod, nombre, pais, ciudad, direccion, lat, lon);
                if (ac.EditAeropuerto(editAeropuerto)){
                    Toast.makeText(InfoAereopuerto.this, "Aeropuerto actualizado", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(getApplicationContext(), ListadoAereopuerto.class);
                    startActivity(i);
                    finish();
                }else {
                    Toast.makeText(InfoAereopuerto.this, "No fue posible editar", Toast.LENGTH_LONG).show();
                }
            }
        });

        btneliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ac.EliminarAeropuertoByID(ID)){
                    Toast.makeText(InfoAereopuerto.this, "Aeropuerto eliminado", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(getApplicationContext(), ListadoAereopuerto.class);
                    startActivity(i);
                    finish();
                }else {
                    Toast.makeText(InfoAereopuerto.this, "No fue posible eliminar", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnmapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(InfoAereopuerto.this, "Aeropuerto eliminado", Toast.LENGTH_LONG).show();
                Intent i = new Intent(getApplicationContext(), MapsActivity.class);
                i.putExtra("id", ID);
                startActivity(i);
                //finish();
            }
        });
    }
}