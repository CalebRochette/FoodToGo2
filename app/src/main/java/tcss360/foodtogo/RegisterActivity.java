package tcss360.foodtogo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //Objects to store the components
        final EditText etName = (EditText) findViewById(R.id.etName);
        final EditText etEmail = (EditText) findViewById(R.id.etEmail);
        final EditText etPassword = (EditText) findViewById(R.id.etPassword);
        final Button btRegister = (Button) findViewById(R.id.btRegister);
        final TextView loginLink = (TextView) findViewById(R.id.tvLogin);

        //gets the object storing the user data
        final Users theUsers = Users.getInstance();

        //handles when the user clicks the login link
        loginLink.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent loginIntent = new Intent(RegisterActivity.this, LoginActivity.class);
                RegisterActivity.this.startActivity(loginIntent);
            }
        });

        //handles when the user clicks the register button
        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString();
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();
                boolean isEmailCorrect = false;
                boolean isPasswordCorrect = false;
                if (email.contains("@")) isEmailCorrect = true;
                if (password.length() > 4) isPasswordCorrect = true;

                //returns the user to the login screen and adds the new user to the database
                if(isEmailCorrect && isPasswordCorrect){
                    theUsers.addCredential(email, password, name);
                    Intent loginIntent = new Intent(RegisterActivity.this, LoginActivity.class);
                    RegisterActivity.this.startActivity(loginIntent);
                } else {
                    if(!isEmailCorrect){
                        etEmail.requestFocus();
                    } else {
                        etPassword.requestFocus();
                    }
                }

            }
        });

    }
}
