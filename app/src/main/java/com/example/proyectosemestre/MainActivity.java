package com.example.proyectosemestre;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText tb_user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Capturar texto del Edit text
        tb_user = (EditText) findViewById(R.id.tb_user);
    }

    //Metodo para entrar al Home desde login

    public void ingresarHome(View view) {

        if(!tb_user.getText().toString().isEmpty()){
            Intent login = new Intent(this, HomeActivity.class);
            login.putExtra("captura_usuario", tb_user.getText().toString());
            startActivity(login);
        }
        else{
            Toast.makeText(MainActivity.this, "Ingrese un usuario", Toast.LENGTH_SHORT).show();
        }
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