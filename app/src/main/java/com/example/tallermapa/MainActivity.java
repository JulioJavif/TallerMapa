package com.example.tallermapa;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button registrar,consultar,actualizar,eliminar,listado,ver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        registrar=findViewById(R.id.btnregistrar);
        consultar=findViewById(R.id.btnconsultar);
        actualizar=findViewById(R.id.btnactualizar);
        eliminar=findViewById(R.id.btneliminar);
        listado=findViewById(R.id.btnlistado);
        ver=findViewById(R.id.btnmapa);
        registrar.setOnClickListener(this);
        consultar.setOnClickListener(this);
        actualizar.setOnClickListener(this);
        eliminar.setOnClickListener(this);
        listado.setOnClickListener(this);
        ver.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnregistrar:
                Intent i= new Intent(this, RegistrarAeropuerto.class);
                startActivity(i);
                break;
        }

    }
}