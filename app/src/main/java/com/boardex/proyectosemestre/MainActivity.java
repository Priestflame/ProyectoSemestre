package com.boardex.proyectosemestre;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.concurrent.Executor;

public class MainActivity extends AppCompatActivity {
    private BiometricPrompt biometricPrompt;
    private BiometricPrompt.PromptInfo promptInfo;
    private Executor executor;
    MaterialButton btn_huella;

    private String userID;
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;
    private EditText txtmail;
    private EditText txtpass;

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();
        if(user != null){
            startActivity(new Intent(MainActivity.this, HomeActivity.class));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        txtmail = findViewById(R.id.tb_user);
        txtpass = findViewById(R.id.tb_pass);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        btn_huella=(MaterialButton) findViewById(R.id.btn_huella);
        executor= ContextCompat.getMainExecutor(this);

        btn_huella.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                huella();
            }
        });
    }

    public void userLogin(){
        String mail = txtmail.getText().toString();
        String pass = txtpass.getText().toString();

        if(TextUtils.isEmpty(mail)){
            txtmail.setError("Ingrese un correo");
            txtmail.requestFocus();
        }else if(TextUtils.isEmpty(pass)){
            txtpass.setError("Ingrese una contraseña");
            txtpass.requestFocus();
        }else{
            mAuth.signInWithEmailAndPassword(mail, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Intent login = new Intent(getApplicationContext(), HomeActivity.class);
                        startActivity(login);
                        finish();
                    }else{
                        Log.w("TAG","Error: ", task.getException());
                    }
                }
            });
        }
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