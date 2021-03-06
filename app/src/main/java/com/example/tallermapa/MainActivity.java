package com.example.tallermapa;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button registrar,listado,ver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        registrar=findViewById(R.id.btnregistrar);
        listado=findViewById(R.id.btnlistado);
        ver=findViewById(R.id.btnmapa);
        registrar.setOnClickListener(this);
        listado.setOnClickListener(this);
        ver.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent i;
        switch (v.getId()){
            case R.id.btnregistrar:
                i = new Intent(this, RegistrarAeropuerto.class);
                startActivity(i);
                break;
            case R.id.btnlistado:
                i = new Intent(this, ListadoAereopuerto.class);
                startActivity(i);
                break;
            case R.id.btnmapa:
                i= new Intent(this,MapsActivity.class);
                startActivity(i);
                break;
        }
    }
}