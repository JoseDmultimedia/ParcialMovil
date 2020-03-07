package com.example.tercerpunto3;

import androidx.appcompat.app.AlertDialog;
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
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "paquetes", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String nombrePBD = nombreP.getText().toString();
        String pesoBD = peso.getText().toString();
        String continenteBD = continente.getText().toString();
        String costoBD = costoE.getText().toString();
        String codigoBD = codigoG.getText().toString();
        String spais = options.getSelectedItem().toString();
      /*  int coding = Integer.parseInt(codigoBD);
        int pesing = Integer.parseInt(pesoBD);
        int costing = Integer.parseInt(costoBD);*/
        ContentValues registro = new ContentValues();
        registro.put("numero", codigoBD);
        registro.put("nombreP",nombrePBD);
        registro.put("peso",pesoBD);
        registro.put("continente",continenteBD);
        registro.put("pais",spais);
        registro.put("costoE",costoBD);
        bd.insert("paquetes",null,registro);
        bd.close();
        peso.setText("");
        nombreP.setText("");
        continente.setText("");
        costoE.setText("");
        codigoG.setText("");

        Toast.makeText(this, "Se Cargaron los datos de la persona", Toast.LENGTH_SHORT).show();
    }

    public void consultar (View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "paquetes", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String coding = codigoG.getText().toString();

        Cursor fila =bd.rawQuery("select nombreP, peso, continente, pais, costoE from paquetes where numero =" + coding, null);
        if (fila.moveToFirst()){
            nombreP.setText(fila.getString(0));
            peso.setText(fila.getString(1));
            continente.setText(fila.getString(2));
            Pais.setText(fila.getString(3));
            costoE.setText(fila.getString(4));
        }else
            Toast.makeText(this, "No existe tal paquete", Toast.LENGTH_SHORT);
        bd.close();
    }
    public void eliminar (View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "paquetes", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String coding = codigoG.getText().toString();
        int cant = bd.delete("paquetes", "numero=" + coding, null );
        bd.close();
        nombreP.setText("");
        peso.setText("");
        continente.setText("");
        Pais.setText("");
        costoE.setText("");
        if (cant ==1){
            Toast.makeText(this, "Se borro el paquete", Toast.LENGTH_SHORT).show();
        }else
            Toast.makeText(this, "No existe tal paquete", Toast.LENGTH_SHORT).show();
    }
    public void modificar (View view){

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "paquetes", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String nombrePBD = nombreP.getText().toString();
        String pesoBD = peso.getText().toString();
        peso.setEnabled(true);
        String continenteBD = continente.getText().toString();
        continente.setEnabled(true);
        String costoBD = costoE.getText().toString();
        costoE.setEnabled(true);
        String codigoBD = codigoG.getText().toString();
        String spais = options.getSelectedItem().toString();
        ContentValues registro = new ContentValues();
        registro.put("numero", codigoBD);
        registro.put("nombreP",nombrePBD);
        registro.put("peso",pesoBD);
        registro.put("continente",continenteBD);
        registro.put("pais",spais);
        registro.put("costoE",costoBD);

        if(pesoBD != peso.getText().toString()){
                String peso = pesoBD;
                double ppeso = Double.parseDouble(peso);
                if (ppeso > 5 )alerta("Los productos no pueden ser transportados debido a su peso mayor a 5 Kg");

        }

        int cant = bd.update("paquetes", registro, "numero=" + codigoBD, null);
        bd.close();
        if (cant == 1){
            Toast.makeText(this, "Se modificaron los datos", Toast.LENGTH_SHORT).show();
        }else
            Toast.makeText(this, "No existe tal paquete", Toast.LENGTH_SHORT).show();
        nombreP.setText("");
        peso.setText("");
        continente.setText("");
        costoE.setText("");
        codigoG.setText("");
        options.getSelectedItem().toString();
    }
    public void alerta(String cadena) {
        AlertDialog.Builder dialogbuilder = new AlertDialog.Builder(this);
        dialogbuilder.setMessage(cadena);
        dialogbuilder.setCancelable(true).setTitle("Paquetería ");
        dialogbuilder.create().show();
    }
}
