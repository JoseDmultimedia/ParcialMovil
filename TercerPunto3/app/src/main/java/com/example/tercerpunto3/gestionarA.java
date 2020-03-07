package com.example.tercerpunto3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class gestionarA extends AppCompatActivity {


    private EditText peso, nombreP, continente, costoE, Pais, codigoG;
    private Spinner options;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestionar);

        peso = (EditText)findViewById(R.id.etPeso4);
        nombreP = (EditText) findViewById(R.id.etNombre2);
        continente = (EditText) findViewById(R.id.ediContinente);
        costoE = (EditText)findViewById(R.id.ediCosteE);
        Pais = (EditText)findViewById(R.id.etPais);
        codigoG = (EditText) findViewById(R.id.eCodgioG);
        Pais.setEnabled(false);

        options = (Spinner)findViewById(R.id.sPais2);

        String pesoBD = getIntent().getStringExtra("peso");
        String costeBD = getIntent().getStringExtra("operacion");
        String seleccionBD = getIntent().getStringExtra("select");
        String codigoBD = getIntent().getStringExtra("codigo");

        peso.setText(pesoBD);
        continente.setText(seleccionBD);
        costoE.setText(costeBD);
        codigoG.setText(codigoBD);

        if(seleccionBD.equals("America del Norte")){
            String[]Opciones={"Canadá", "Estados Unidos", "Mexico"};
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,Opciones);
            options.setAdapter(adapter);

        }else
            if(seleccionBD.equals("America del Sur") ){
            String[] Opciones = {"Venezuela", "Brasil", "Chile"};
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, Opciones);
            options.setAdapter(adapter);
        }else
            if(seleccionBD.equals("America Central")){
                String[]Opciones={"Honduras", "El Salvador", "Guatemala"};
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,Opciones);
                options.setAdapter(adapter);
        }else
            if(seleccionBD.equals("Europa")) {
                String[] Opciones = {"Rusia", "Alemania", "Francia"};
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, Opciones);
                options.setAdapter(adapter);
        }else
            if(seleccionBD.equals("Asia")) {
                String[] Opciones = {"Japón", "China", "India"};
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, Opciones);
                options.setAdapter(adapter);
        }


    }

    public void insertar (View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String nombrePBD = nombreP.getText().toString();
        String pesoBD = peso.getText().toString();
        String continenteBD = continente.getText().toString();
        String costoBD = costoE.getText().toString();
        String codigoBD = codigoG.getText().toString();
        ContentValues registro = new ContentValues();
        registro.put("codigo", codigoBD);
        registro.put("nombreP",nombrePBD);
        registro.put("continente",pesoBD);
        registro.put("pais",continenteBD);
        registro.put("costoE",costoBD);
        bd.insert("envios",null,registro);
        bd.close();
        peso.setText("");
        nombreP.setText("");
        continente.setText("");
        costoE.setText("");
        codigoG.setText("");

        Toast.makeText(this, "Se Cargaron los datos de la persona", Toast.LENGTH_SHORT).show();
    }

    public void consultar (View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "envios", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String code = codigoG.getText().toString();
        int codigoB = Integer.parseInt(code);
        String nombrePBD = nombreP.getText().toString();
        Cursor fila = bd.rawQuery("select nombreP, peso, continente, pais, costoE from envios where codigo=" + 12, null);
        if (fila.moveToFirst()){
                peso.setText(fila.getString(0));
                continente.setText(fila.getString(1));
                Pais.setText(fila.getString(2));
                costoE.setText(fila.getString(3));
        }
        else
            Toast.makeText(this, "No existe tal envio", Toast.LENGTH_SHORT);
        bd.close();

    }
}
