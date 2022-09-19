package com.jnphilippe.instagpdef;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class LoginActivity extends AppCompatActivity {

    public static final String TAG="LoginActivity";
    private EditText etUsername;
    private EditText etPassword;
    private Button btnLogin;
    private Button btnSignup;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        //if user have already session open
        if(ParseUser.getCurrentUser()!=null){
            goMainActivity();
        }


        etUsername=findViewById(R.id.etUsername);
        etPassword=findViewById(R.id.etPassword);
        btnLogin=findViewById(R.id.btnLogin);
        btnSignup=findViewById(R.id.btnSignup);


        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.i(TAG, "onClick login button");
                String username=etUsername.getText().toString();
                String password=etPassword.getText().toString();
                SignUp(username, password);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.i(TAG, "onClick login button");
                String username=etUsername.getText().toString();
                String password=etPassword.getText().toString();
                LoginUser(username, password);

            }
        });
    }

    private void SignUp(String username, String password) {

        // Create the ParseUser
        ParseUser user = new ParseUser();
        // Set core properties
        user.setUsername(username);
        user.setPassword(password);
        // Invoke signUpInBackground
        user.signUpInBackground(new SignUpCallback() {
            public void done(ParseException e) {
                if (e != null) {
                    // Hooray! Let them use the app now.
                    Log.e(TAG, "Issue with signup", e);
                    Toast.makeText(LoginActivity.this, "Issue with singup...", Toast.LENGTH_SHORT).show();
                    return;

                }
                goMainActivity();
                Toast.makeText(LoginActivity.this, "Signup success! user: "+username +"has been created", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void LoginUser(String username, String password) {
        Log.i(TAG,"Attending to login" + username);
        ParseUser.logInInBackground(username, password, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if (e != null) {
                    //TODO: better error handling
                    Log.e(TAG, "Issue with login", e);
                    Toast.makeText(LoginActivity.this, "Issue with Login...", Toast.LENGTH_SHORT).show();
                    return;
                }
                goMainActivity();
                Toast.makeText(LoginActivity.this, "Login Success! user: "+username + "is connect", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void goMainActivity() {
        Intent i=new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }
}