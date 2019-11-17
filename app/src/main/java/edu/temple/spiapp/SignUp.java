package edu.temple.spiapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class SignUp extends AppCompatActivity {

    public EditText emailID, pswd;
    Button btnSignUp;
    FirebaseAuth firebaseAuth;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        firebaseAuth = FirebaseAuth.getInstance();
        emailID = findViewById(R.id.signUpEmail);
        pswd = findViewById(R.id.signUpPassword);
        btnSignUp = findViewById(R.id.signUpButton);


        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailId = emailID.getText().toString();
                String paswd = pswd.getText().toString();
                if (emailId.isEmpty()) {
                    emailID.setError("Provide your Email first!");
                    emailID.requestFocus();
                } else if (paswd.isEmpty()) {
                    pswd.setError("Set your password");
                    pswd.requestFocus();
                } else if (emailId.isEmpty() && paswd.isEmpty()) {
                    Toast.makeText(SignUp.this, "Fields Empty!", Toast.LENGTH_SHORT).show();
                } else if (!(emailId.isEmpty() && paswd.isEmpty())) {
                    firebaseAuth.createUserWithEmailAndPassword(emailId, paswd).addOnCompleteListener(SignUp.this, new OnCompleteListener() {

                        @Override
                        public void onComplete(@NonNull Task task) {

                            if (!task.isSuccessful()) {
                                Toast.makeText(SignUp.this.getApplicationContext(),
                                        "SignUp unsuccessful: " + task.getException().getMessage(),
                                        Toast.LENGTH_SHORT).show();
                            } else {
                                startActivity(new Intent(SignUp.this, MainActivity.class));
                            }
                        }
                    });
                } else {
                    Toast.makeText(SignUp.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}
