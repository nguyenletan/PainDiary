package com.example.paindiary;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.paindiary.databinding.ActivityLoginBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

    private static final String TAG = "EmailPassword";

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        auth = FirebaseAuth.getInstance();
        setContentView(R.layout.activity_login);
    }


    public void onLogin(View v) {
        Context context = getApplicationContext();

        int duration = Toast.LENGTH_LONG;
        //CharSequence text = "Hello toast!";
        EditText editText = findViewById(R.id.editTextTextEmailAddress);
        EditText passwordText = findViewById(R.id.editTextTextPassword);
        String email = editText.getText().toString();
        String password = passwordText.getText().toString();
        if (email.equals("") || password.equals("")) {
            Toast.makeText(context, "Authentication failed.", duration).show();
        } else {
            auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d(TAG, "signInWithEmail:success");
                                FirebaseUser user = auth.getCurrentUser();
                                Toast.makeText(context, "Login successfully",
                                        duration).show();
                                Intent intent = new Intent(Login.this, MainActivity.class);
                                startActivity(intent);
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w(TAG, "signInWithEmail:failure", task.getException());
                                Toast.makeText(context, "Authentication failed.",
                                        duration).show();

                            }
                        }
                    });
        }
    }

    public void onSignup(View v) {
        Intent intent = new Intent(Login.this, SignUp.class);
        startActivity(intent);

    }
}