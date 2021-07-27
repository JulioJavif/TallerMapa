package com.example.tallermapa;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class RegistrarAeropuerto extends AppCompatActivity implements View.OnClickListener {
    EditText codigo,nombre,pais,ciudad,direccion,latitud,longitud;
    Button registar1;
    Aeropuerto a;
    AeropuertoController ac;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_aeropuerto);
        codigo=findViewById(R.id.txtcodigo);
        nombre=findViewById(R.id.txtnombre);
        pais=findViewById(R.id.txtpais);
        ciudad=findViewById(R.id.txtciudad);
        direccion=findViewById(R.id.txtdireccion);
        latitud=findViewById(R.id.txtlatitud);
        longitud=findViewById(R.id.txtlongitud);
        registar1=findViewById(R.id.btnregistrar1);
        registar1.setOnClickListener(this);
        ac = new AeropuertoController(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnregistrar1:
                if(TextUtils.isEmpty(codigo.getText().toString()) || TextUtils.isEmpty(nombre.getText().toString()) || TextUtils.isEmpty(pais.getText().toString())
                        || TextUtils.isEmpty(ciudad.getText().toString()) || TextUtils.isEmpty(direccion.getText().toString()) || TextUtils.isEmpty(latitud.getText().toString())
                    || TextUtils.isEmpty(longitud.getText().toString())){
                    Toast.makeText(this,"Los datos no pueden ser vacíos", Toast.LENGTH_LONG).show();
                }else{
                    a= new Aeropuerto(codigo.getText().toString(),nombre.getText().toString(),pais.getText().toString(),
                                         ciudad.getText().toString(),direccion.getText().toString(),latitud.getText().toString(),
                                         longitud.getText().toString());

                    ac.agregarAeropuerto(a);

                    /*if(ac.buscarAeropuerto(aero)){
                        Toast.makeText(this,"Código ya existe, no se podra registar", Toast.LENGTH_LONG).show();

                    }else{
                        Toast.makeText(this,"registrado", Toast.LENGTH_LONG).show();
                        ac.agregarAeropuerto(aero);
                    }*/
                }
        }
    }
}