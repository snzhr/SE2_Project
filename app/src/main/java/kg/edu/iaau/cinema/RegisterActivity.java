package kg.edu.iaau.cinema;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    EditText regUsername, regEmail, regPassword;
    TextView regLogin;
    Button btnRegister;
    FirebaseAuth mAuth;
    DatabaseReference mDatabase;
    String Username, Email, Password;
    ProgressDialog mDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        regUsername = findViewById(R.id.username);
        regEmail = findViewById(R.id.user_email);
        regPassword = findViewById(R.id.user_pass);
        regLogin = findViewById(R.id.login);
        btnRegister = findViewById(R.id.reg);
        //Firebase Authentication

        mAuth = FirebaseAuth.getInstance();
        btnRegister.setOnClickListener(this);
        regLogin.setOnClickListener(this);
        mDialog = new ProgressDialog(this);
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Users");

    }

    public void onClick(View v){
        if (v==btnRegister){
            UserRegister();
        }else if(v==regLogin){
            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
        }
    }

    private void UserRegister() {
        Username = regUsername.getText().toString().trim();
        Email = regEmail.getText().toString().trim();
        Password = regPassword.getText().toString().trim();

        if (TextUtils.isEmpty(Username)){
            Toast.makeText(RegisterActivity.this, "Enter Name", Toast.LENGTH_SHORT).show();
            return;
        }else if (TextUtils.isEmpty(Email)){
            Toast.makeText(RegisterActivity.this, "Enter Email", Toast.LENGTH_SHORT).show();
            return;
        }else if (TextUtils.isEmpty(Password)){
            Toast.makeText(RegisterActivity.this, "Enter Password", Toast.LENGTH_SHORT).show();
            return;
        }else if (Password.length()<6){
            Toast.makeText(RegisterActivity.this,"Passwor must be greater then 6 digit",Toast.LENGTH_SHORT).show();
            return;
        }
        mDialog.setMessage("Creating User please wait...");
        mDialog.setCanceledOnTouchOutside(false);
        mDialog.show();
        mAuth.createUserWithEmailAndPassword(Email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    sendEmailVerification();
                    mDialog.dismiss();
                    OnAuth(task.getResult().getUser());
                    mAuth.signOut();
                }else{
                    Toast.makeText(RegisterActivity.this,"error on creating user",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void OnAuth(FirebaseUser user) {
        createAnewUser(user.getUid());
    }

    private void createAnewUser(String uid) {
        User user = BuildNewuser();
        mDatabase.child(uid).setValue(user);
    }

    private User BuildNewuser(){
        return new User(
                getUsername(),
                getEmail(),
                new Date().getTime()
        );
    }

    //Email verification code using FirebaseUser object and using isSucccessful()function.
    private void sendEmailVerification() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user!=null){
            user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(RegisterActivity.this,"Check your Email for verification",Toast.LENGTH_SHORT).show();
                        FirebaseAuth.getInstance().signOut();
                    }
                }
            });
        }
    }

    public String getUsername() {
        return Username;
    }

    public String getEmail() {
        return Email;
    }










/*
                regLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, StartActivity.class);
                startActivity(intent);
            }
        });

*/

}
