package kg.edu.iaau.cinema;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class StartActivity extends AppCompatActivity {

    String emailHolder;
    TextView Email;
    Button btnSignOut;
    FirebaseAuth mAuth;
    FirebaseAuth.AuthStateListener mAuthListener;
    FirebaseUser mUser;

    public static final String TAG="LOGIN";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        Email = findViewById(R.id.hello);
        btnSignOut = findViewById(R.id.sign_out);

        Intent intent = getIntent();

        emailHolder = intent.getStringExtra(LoginActivity.userEmail);

        btnSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();

                Toast.makeText(StartActivity.this, "Signed out successfully", Toast.LENGTH_LONG).show();
            }
        });



    }



}