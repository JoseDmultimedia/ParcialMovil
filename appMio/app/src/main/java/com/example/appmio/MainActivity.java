package com.example.appmio;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.*;
import android.content.Intent;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {


    private EditText eTiquete, editEdad, eCantidad;
    private RadioButton r1, r2, r3, r4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        eTiquete = (EditText) findViewById(R.id.ediPrecio);
        editEdad = (EditText) findViewById(R.id.ediEdad);
        eCantidad = (EditText) findViewById(R.id.ediCantidad);
        r1 = (RadioButton) findViewById(R.id.rPensionado);
        r2 = (RadioButton) findViewById(R.id.rEstudiante);
        r3 = (RadioButton) findViewById(R.id.rTrabajador);
        r4 = (RadioButton) findViewById(R.id.rOtro);

    }

    public void calcularDescuento(View view){
        String precioCadena = eTiquete.getText().toString();
        String edadCadena = editEdad.getText().toString();
        String cantidadCadena = eCantidad.getText().toString();

        int edadNumerica = Integer.parseInt(edadCadena);
        int cantidadNumerica = Integer.parseInt(cantidadCadena);
        double precioExacto =  Double.parseDouble(precioCadena);

        //Aqui se realiza una operación que multiplica la cantidad de tiquetes por el precio del tiquete
        double precioNumerico = precioExacto * cantidadNumerica;

        double precioFinal = 0;
        String porcentaje ="";

        int casos = 0;

        if((edadNumerica < 4) && (r4.isChecked())){
            casos += 2;
        }
        if ((edadNumerica > 4) && (edadNumerica < 7) && r4.isChecked()){
            casos += 4;
        }
        if (edadNumerica > 65){
            casos += 8;
        }
        if (r2.isChecked()){
            casos += 16;
        }
        if (cantidadNumerica > 5){
            casos += 32;
        }
        else {
            precioFinal = precioNumerico;
            porcentaje = "0%";
        }
        switch (casos)
        {
            case 2:
                precioFinal = 0;
                porcentaje = "100%";
                break;
            case 4:
                precioFinal = precioNumerico/2;
                porcentaje ="50%";
                break;
            case 8:
                precioFinal = precioNumerico - ((precioNumerico*40)/100);
                porcentaje = "40%";
                break;
            case 16:
                precioFinal = precioNumerico - ((precioNumerico*40)/100);
                porcentaje = "40%";
                break;
            case 32:
                precioFinal = precioNumerico - ((precioNumerico*70)/100);
                porcentaje = "70%";
                break;
            case 34:
                precioFinal = precioNumerico - ((precioNumerico*70)/100);
                porcentaje = "70%";
                break;
            case 36:
                precioFinal = precioNumerico - ((precioNumerico*70)/100);
                porcentaje = "70%";
                break;
            case 40:
                precioFinal = precioNumerico - ((precioNumerico*70)/100);
                porcentaje = "70%";
                break;
            case 48:
                precioFinal = precioNumerico - ((precioNumerico*70)/100);
                porcentaje = "70%";
                break;
            case 24:
                precioFinal = precioNumerico - ((precioNumerico*40)/100);
                porcentaje = "40%";
                break;
        }

        Intent i = new Intent(MainActivity.this, outputAppMio.class);
        i.putExtra("edad" , edadCadena + "");
        i.putExtra("precio", precioCadena + "");
        i.putExtra("cantidad", cantidadCadena + "");
        i.putExtra("porcentaje", porcentaje + "");
        i.putExtra("precioFinal", precioFinal + "");
        startActivity(i);

    }
    public void limpiarVal (View view){
        eTiquete.setText("");
        editEdad.setText("");
        eCantidad.setText("");
    }
}
