package com.fastchat.forchatting;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.fastchat.forchatting.Models.Users;
import com.fastchat.forchatting.databinding.ActivitySignUpBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {

    ActivitySignUpBinding binding;
    private FirebaseAuth auth;
    FirebaseDatabase database;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().hide();

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        progressDialog = new ProgressDialog(SignUp.this);
        progressDialog.setTitle("Creating Account");
        progressDialog.setMessage("We are creating your account.");

        binding.signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (binding.signUpEmail.getText().toString().isEmpty()) {
                    binding.signUpEmail.setError("Please enter your email");
                    return;
                }

                if (binding.signUpUsername.getText().toString().isEmpty()) {
                    binding.signUpUsername.setError("Please enter your email");
                    return;
                }

                if (binding.signUpPassword.getText().toString().isEmpty()) {
                    binding.signUpPassword.setError("Please enter your email");
                    return;
                }

                progressDialog.show();

                auth.createUserWithEmailAndPassword(binding.signUpEmail.getText().toString(), binding.signUpPassword.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();

                        if (task.isSuccessful()) {

                            Users user = new Users(binding.signUpUsername.getText().toString(), binding.signUpEmail.getText().toString(), binding.signUpPassword.getText().toString());
                            String id = task.getResult().getUser().getUid();
                            database.getReference().child("user").child(id).setValue(user);

                            Toast.makeText(SignUp.this, "Create Account Successfully", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(SignUp.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }
                });
            }
        });

        binding.tvSignUpAlready.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent go_sign_in = new Intent(SignUp.this, SignIn.class);
                startActivity(go_sign_in);
            }
        });
    }
}