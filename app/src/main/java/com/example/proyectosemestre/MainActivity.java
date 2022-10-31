package com.example.proyectosemestre;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricManager;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

import java.util.concurrent.Executor;

public class MainActivity extends AppCompatActivity {
    private BiometricPrompt biometricPrompt;
    private BiometricPrompt.PromptInfo promptInfo;
    private Executor executor;
    MaterialButton btn_huella;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_huella=(MaterialButton) findViewById(R.id.btn_huella);
        executor= ContextCompat.getMainExecutor(this);

        btn_huella.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                huella();
            }
        });
    }

    public void huella(){
        biometricPrompt= new BiometricPrompt(MainActivity.this, executor, new BiometricPrompt.AuthenticationCallback(){
            @Override
            public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
                Toast.makeText(getApplicationContext(), "Error al ingresar huella", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                Intent login = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(login);
                finish();
            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
                Toast.makeText(getApplicationContext(), "Huella no reconocida", Toast.LENGTH_SHORT).show();
            }
        });
        promptInfo=new BiometricPrompt.PromptInfo.Builder()
                .setTitle("Autenticacion")
                .setSubtitle("Ingrese su huella")
                .setNegativeButtonText("Cancelar")
                .build();
        biometricPrompt.authenticate(promptInfo);
    }

    //Metodo para entrar al Home desde login

    public void ingresarHome(View view) {
        Intent login = new Intent(getApplicationContext(), HomeActivity.class);
        startActivity(login);
        finish();
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