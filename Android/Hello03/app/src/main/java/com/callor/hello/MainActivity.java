package com.callor.hello;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.Button;

import com.callor.hello.model.UserDTO;
import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    private TextInputEditText input_email = null;
    private TextInputEditText input_password = null;
    private TextInputEditText input_tel = null;
    private TextInputEditText input_name = null;
    private TextInputEditText input_address = null;
    private Button btn = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.btn);
        input_email = findViewById(R.id.input_email);
        input_password = findViewById(R.id.input_password);
        input_tel = findViewById(R.id.input_tel);
        input_name = findViewById(R.id.input_name);
        input_address = findViewById(R.id.input_address);

        btn.setOnClickListener( view->{

            String email = input_email.getText().toString();
            String password = input_password.getText().toString();
            String tel = input_tel.getText().toString();
            String name = input_name.getText().toString();
            String address = input_address.getText().toString();

            UserDTO userDTO = new UserDTO();
            userDTO.id = email;
            userDTO.name = name;
            userDTO.password = password;
            userDTO.tel = tel;
            userDTO.addr = address;

            Intent intent = new Intent(MainActivity.this, LoginActivity.class);

            intent.putExtra("USER", (Parcelable) userDTO);


            startActivity(intent);



        });



    }
}