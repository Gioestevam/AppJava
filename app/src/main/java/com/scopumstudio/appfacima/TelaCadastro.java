package com.scopumstudio.appfacima;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class TelaCadastro extends AppCompatActivity implements View.OnClickListener {

    Button Btn_signup;
    EditText Field_username, Field_email, Field_password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_cadastro);

        if (SharedPredManager.getInstance(this).isLoggedIn()) {
            finish();
            startActivity(new Intent(this, Home.class));
            return;
        }

        Field_username  = (EditText)findViewById(R.id.Field_username);
        Field_email     = (EditText)findViewById(R.id.Field_email);
        Field_password  = (EditText)findViewById(R.id.Field_password);
        Btn_signup      = (Button)findViewById(R.id.Btn_signup);

        Btn_signup.setOnClickListener(this);
    }

    private void registerUser() {
        final String email    = Field_email.getText().toString().trim();
        final String password = Field_password.getText().toString().trim();
        final String username = Field_username.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constraints.URL_REGISTER,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);

                            startActivity(new Intent(getApplicationContext(), TelaLogin.class));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
               Map<String, String> params = new HashMap<>();
               params.put("email", email);
               params.put("password", password);
               params.put("username", username);

               return params;
            }
        };

        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);

    }

    public void onClick(View v) {
        if (v == Btn_signup) {
            registerUser();
        }
    }
}
