package com.example.proyectosemestre;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //Metodo para entrar al Home desde login

    public void ingresarHome(View view) {
        Intent login = new Intent(this, HomeActivity.class);
        startActivity(login);
    }
    //Metodo para salir al login desde Home
    // por alguna razón la app se cierra cuando esto está aquí
    // Hay que agregar un destroy para saltar a la otra activity
    /*
    public void cerrarSesion(View view) {
        Intent login = new Intent(this, MainActivity.class);
        startActivity(login);
    }*/

}