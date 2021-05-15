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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUp extends AppCompatActivity {
    private static final String TAG = "EmailPassword";
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        auth = FirebaseAuth.getInstance();
        setContentView(R.layout.activity_sign_up);
    }

    public void onSignup(View view) {
        Context context = getApplicationContext();

        int duration = Toast.LENGTH_LONG;
        //CharSequence text = "Hello toast!";
        EditText editText = findViewById(R.id.editTextTextEmailAddress);
        EditText passwordText = findViewById(R.id.editTextTextPassword);
        String email = editText.getText().toString();
        String password = passwordText.getText().toString();
        if (email.equals("")) {
            Toast.makeText(context, "Please enter email.", duration).show();
            return;
        } else if (!Helpers.isValidEmail(email)) {
            Toast.makeText(context, "Please enter a valid email.", duration).show();
            return;
        }

        if (password.equals("")) {
            Toast.makeText(context, "Please enter password.", duration).show();
            return;
        }

        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            Toast.makeText(context, "Sign up successfully",
                                    Toast.LENGTH_SHORT).show();
                            auth.signOut();
                            Intent intent = new Intent(SignUp.this, Login.class);
                            startActivity(intent);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(context, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }
}