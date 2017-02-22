package com.dashwood.simpletalk;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    EditText email, passwd;
    Button login;
    //String email_text, passwd_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        email = (EditText)findViewById(R.id.login_email);
        passwd = (EditText)findViewById(R.id.login_passwd);
        login = (Button)findViewById(R.id.login_button);
        final FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseAuth.AuthStateListener authStateListener;

        auth.signInWithEmailAndPassword(email.getText().toString(), passwd.getText().toString())
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        setResult(RESULT_OK);
                        finish();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        login.setError("登入失敗, 請檢查帳號密碼");
                    }
                });

        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user==null)
                {
                    Toast.makeText(MainActivity.this, "LOGIN SUCCESS", Toast.LENGTH_SHORT).show();
                }
            }
        };


    }
}
