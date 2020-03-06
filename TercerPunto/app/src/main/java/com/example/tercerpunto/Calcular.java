package com.example.tercerpunto;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class Calcular extends AppCompatActivity {

    private EditText Peso;
    private Spinner Options;
    private Button Calcular;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Peso = (EditText)findViewById(R.id.etPeso);
        Options = (Spinner)findViewById(R.id.sOptions);

        String[]Opciones={"America del Norte", "America Central", "America del Sur", "Europa", "Asia"};
       ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,Opciones);
        Options.setAdapter(adapter);

    }

    public void CalcularCosto(View view) {

        String peso = Peso.getText().toString();
        double ppeso = Double.parseDouble(peso);
        double op;
        String operacion;
        String select = Options.getSelectedItem().toString();

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

    }

    public void alerta(String cadena) {
        AlertDialog.Builder dialogbuilder = new AlertDialog.Builder(this);
        dialogbuilder.setMessage(cadena);
        dialogbuilder.setCancelable(true).setTitle("El costo total para enviar el paquete es de: ");
        dialogbuilder.create().show();
    }
}
