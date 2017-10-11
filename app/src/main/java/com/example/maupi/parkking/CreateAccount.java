package com.example.maupi.parkking;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CreateAccount extends AppCompatActivity {

    DatabaseHelper helper = new DatabaseHelper(this);
    private Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        submit = (Button) findViewById(R.id.submit);

        submit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                onSignUpClick(view);
            }
        });
    }

    // Functions that needs to be called after the user submits his new account information

    public void onSignUpClick(View v){

        if(v.getId() == R.id.submit){

            EditText uname = (EditText)findViewById(R.id.uname);
            EditText pass1 = (EditText)findViewById(R.id.pass1);
            EditText pass2 = (EditText)findViewById(R.id.pass2);
            EditText email = (EditText)findViewById(R.id.email);

            String user_name = uname.getText().toString();
            String pass_str1 = pass1.getText().toString();
            String pass_str2 = pass2.getText().toString();
            String user_email = email.getText().toString();
            
            if(!pass_str1.equals(pass_str2)){

               Toast pass =  Toast.makeText(CreateAccount.this, "Passwords dont't match", Toast.LENGTH_SHORT);
               pass.show();

            }else{

                client c = new client();
                c.setEmail(user_email);
                c.setPass(pass_str1);
                c.setUname(user_name);

                helper.insertContact(c);
                Toast pass =  Toast.makeText(CreateAccount.this, "congratulations, successfully inserted the new account", Toast.LENGTH_SHORT);
                pass.show();
            }

        }
    }
}
