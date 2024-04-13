package com.example.hostelmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.github.leandroborgesferreira.loadingbutton.R.drawable;
import com.github.leandroborgesferreira.loadingbutton.customViews.CircularProgressButton;

import org.json.JSONException;
import org.json.JSONObject;

public class Login extends AppCompatActivity {
    CircularProgressButton btn;
    EditText emailEditText, passwordEditText;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //for changing status bar icon colors
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        setContentView(R.layout.activity_login);
        requestQueue = Volley.newRequestQueue(this);
        emailEditText = findViewById(R.id.editTextEmail);
        passwordEditText = findViewById(R.id.editTextPassword);
        btn = (CircularProgressButton) findViewById(R.id.cirLoginButton);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onLoginClick(v);
            }
        });


    }

    public void onLoginClick(View View) {

            String loginUrl = "http://localhost/Login/login.php"; // Replace with your PHP login URL
            String email = emailEditText.getText().toString();
            String password = passwordEditText.getText().toString();

            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("email", email);
                jsonObject.put("password", password);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, loginUrl, jsonObject,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                int status = response.getInt("status");
                                String message = response.getString("message");

                                if (status == 0) {
                                    String fullName = response.getString("full_name");
                                    Toast.makeText(Login.this, "Welcome, " + fullName, Toast.LENGTH_SHORT).show();
                                    // Redirect to next activity or perform other actions
                                } else {
                                    Toast.makeText(Login.this, message, Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("LoginError", "Error: " + error.getMessage());
                    Toast.makeText(Login.this, "Error occurred. Please try again.", Toast.LENGTH_SHORT).show();
                }
            });

            Volley.newRequestQueue(this).add(jsonObjectRequest);
        }

    }
}