package com.example.tercerpunto3;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.os.Bundle;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void irCalcular (View view){
        Intent i = new Intent(MainActivity.this, calcularA.class);
        startActivity(i);
    }
    public void irGestionar (View view){
        Intent i = new Intent(MainActivity.this, gestionarA.class);
        startActivity(i);
    }
}
