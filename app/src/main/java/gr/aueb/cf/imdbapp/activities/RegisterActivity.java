package gr.aueb.cf.imdbapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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

                if (isUserValid(password, confirmPassword)) {
                    ApiService.getInstance().getMovieService().registerUser(username, password  , email).enqueue(new Callback<User>() {
                        @Override
                        public void onResponse(Call<User> call, Response<User> response) {
                            User user = response.body();
                            if (user == null) {
                                Toast.makeText(RegisterActivity.this, "Register failed. Try again..!", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(RegisterActivity.this, "Register success. Please login!", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        }

                        @Override
                        public void onFailure(Call<User> call, Throwable t) {
                            Toast.makeText(RegisterActivity.this, "Network error", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }

    private Boolean isUserValid(String password, String confirmPassword) {
        Boolean isValid = false;
        if (Objects.equals(password, confirmPassword)) {
            isValid = true;
        } else {
            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
        }
        return isValid;
    }

    private void registerRequest(String email, String username, String password) {

    }
}