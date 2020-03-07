package com.example.tercerpunto3;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.content.Intent;

public class calcularA extends AppCompatActivity {


    private EditText Peso, codigo;
    private Spinner Options;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calcular);
        Peso = (EditText)findViewById(R.id.etPeso);
        Options = (Spinner)findViewById(R.id.sOptions);
        codigo = (EditText) findViewById(R.id.eCodigo);

        String[]Opciones={"America del Norte", "America Central", "America del Sur", "Europa", "Asia"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,Opciones);
        Options.setAdapter(adapter);

    }

    public void CalcularCosto(View view) {

        String peso = Peso.getText().toString();
        double ppeso = Double.parseDouble(peso);
        double op = 0;
        String operacion ="";
        String select = Options.getSelectedItem().toString();
        String codigoP = codigo.getText().toString();

        if (ppeso <= 5 ){
            if (select.equals("America del Norte")) {
                op = ppeso * 3800;
                operacion = String.valueOf(op);
                alerta(operacion);
            } else if (select.equals("America Central")) {
                op = ppeso * 3100;
                operacion = String.valueOf(op);
                alerta(operacion);
            } else if (select.equals("America del Sur")) {
                op = ppeso * 2900;
                operacion = String.valueOf(op);
                alerta(operacion);
            } else if (select.equals("Europa")) {
                op = ppeso * 4200;
                operacion = String.valueOf(op);
                alerta(operacion);
            } else if (select.equals("Asia")) {
                op = ppeso * 5300;
                operacion = String.valueOf(op);
                alerta(operacion);
            }
        } else alerta("Los productos no pueden ser transportados debido a su peso mayor a 5 Kg");

        Intent i = new Intent(calcularA.this, gestionarA.class);
        i.putExtra("peso", peso + "");
        i.putExtra("operacion",  operacion +"");
        i.putExtra("select", select +"");
        i.putExtra("codigo", codigoP + "");
        startActivity(i);
    }

    public void alerta(String cadena) {
        AlertDialog.Builder dialogbuilder = new AlertDialog.Builder(this);
        dialogbuilder.setMessage(cadena);
        dialogbuilder.setCancelable(true).setTitle("El costo total para enviar el paquete es de: ");
        dialogbuilder.create().show();
    }

}
