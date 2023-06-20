package gr.aueb.cf.imdbapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import gr.aueb.cf.imdbapp.R;
import gr.aueb.cf.imdbapp.models.User;
import gr.aueb.cf.imdbapp.network.MovieService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private EditText usernameET;
    private EditText passwordET;
    private Button lgnBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameET = findViewById(R.id.usernameET);
        passwordET = findViewById(R.id.passwordET);

        lgnBtn = findViewById(R.id.loginBtn);



        lgnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = usernameET.getText().toString();
                String password = passwordET.getText().toString();

                MovieService.getInstance().getMovieService().getUser(username, password).enqueue(new Callback<User>() {

                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        User user = response.body();

                        if (user != null) {
                            startActivity(new Intent(LoginActivity.this, SearchActivity.class));
                        } else {
                            Toast.makeText(LoginActivity.this, "Not valid credentials", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Toast.makeText(LoginActivity.this, "Fail to fetch data", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });




        Button registerBtn = findViewById(R.id.registerBtn);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });

    }
}