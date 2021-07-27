package com.example.tallermapa;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ListadoAereopuerto extends AppCompatActivity {

    ListView listaAereopuerto;
    AeropuertoController ac;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_aereopuerto);

        listaAereopuerto = findViewById(R.id.lvlistaaereopuerto);
        ac = new AeropuertoController(this);

        ArrayList Items = ac.AllAereopuerto();
        ArrayList IDs = ac.ListaIDs();

        if (!Items.isEmpty()){
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, Items);
            listaAereopuerto.setAdapter(adapter);
        }else {
            Items.add("No se encontraron registros");
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, Items);
        }


        listaAereopuerto.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String ID = IDs.get(position).toString();

            }
        });
    }
}