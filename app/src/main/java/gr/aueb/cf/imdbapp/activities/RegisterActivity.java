package gr.aueb.cf.imdbapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Objects;

import gr.aueb.cf.imdbapp.Parameters;
import gr.aueb.cf.imdbapp.R;
import gr.aueb.cf.imdbapp.models.User;
import gr.aueb.cf.imdbapp.network.ApiService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    private EditText usernameET;
    private EditText passwordET;
    private EditText confirmPasswordET;
    private EditText emailET;

    private Button registerBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        usernameET = findViewById(R.id.usernameET);
        passwordET = findViewById(R.id.passwordET);
        confirmPasswordET = findViewById(R.id.confirmPasswordET);
        emailET = findViewById(R.id.emailET);
        registerBtn = findViewById(R.id.registerBtn);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailET.getText().toString();
                String username = usernameET.getText().toString();
                String password = passwordET.getText().toString();
                String confirmPassword = confirmPasswordET.getText().toString();


                if(!isEmailValid(email)){
                    Toast.makeText(RegisterActivity.this, "Invalid email format.", Toast.LENGTH_SHORT).show();
                }else if (!isUserValid(password, confirmPassword)) {
                    Toast.makeText(RegisterActivity.this, "Passwords do not match.", Toast.LENGTH_SHORT).show();
                }else{
                    ApiService.getInstance().getMovieService().registerUser(username, password, email).enqueue(new Callback<User>() {
                        @Override
                        public void onResponse(Call<User> call, Response<User> response) {
                            User user = response.body();
                            if(response.code() >= 500){
                                System.out.println(response);
                                try{
                                    Toast.makeText(RegisterActivity.this, response.errorBody().string(), Toast.LENGTH_SHORT).show();
                                }catch(Exception e){
                                    e.printStackTrace();
                                }

                            }else if(response.code() >= 200){
                                if (user == null) {
                                    Toast.makeText(RegisterActivity.this, "Register failed. Try again..!", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(RegisterActivity.this, "Register success. Please login!", Toast.LENGTH_SHORT).show();
                                    finish();
                                }
                            }

                        }

                        @Override
                        public void onFailure(Call<User> call, Throwable t) {
                            Toast.makeText(RegisterActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }

    private boolean isUserValid(String password, String confirmPassword) {
        return password.equals(confirmPassword);
    }

    private boolean isEmailValid(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }




}