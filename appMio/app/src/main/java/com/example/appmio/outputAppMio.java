package com.example.appmio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;
import android.widget.*;


import android.os.Bundle;

public class outputAppMio extends AppCompatActivity {

    private EditText descripcion, ePorcentaje, precioFinla;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_output_app_mio);
        descripcion = (EditText) findViewById(R.id.ediDes);
        ePorcentaje = (EditText) findViewById(R.id.ediPorcentaje);
        precioFinla = (EditText) findViewById(R.id.ediPrecioF);

        String edad = getIntent().getStringExtra("edad");
        String precio = getIntent().getStringExtra("precio");
        String cantidad = getIntent().getStringExtra("cantidad");
        String porcentaje = getIntent().getStringExtra("porcentaje");
        String precioFinal = getIntent().getStringExtra("precioFinal");

        descripcion.setText("Los datos ingresados anteriormente corresponden a edad" + " " + edad + " " + "cantidad" + " " +  cantidad + " " +"precio" + " " + precio);
        ePorcentaje.setText(porcentaje);
        precioFinla.setText(precioFinal);
    }

    public void volver(View view){
        descripcion.setText("");
        ePorcentaje.setText("");
        precioFinla.setText("");

        Intent i = new Intent( outputAppMio.this, MainActivity.class);
        startActivity(i);
    }
}
