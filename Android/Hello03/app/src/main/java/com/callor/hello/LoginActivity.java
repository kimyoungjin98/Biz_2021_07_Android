package com.callor.hello;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.callor.hello.model.UserDTO;

public class LoginActivity extends AppCompatActivity {

    private TextView txt_msg = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txt_msg = findViewById(R.id.txt_msg);

        Intent intent = getIntent();

        UserDTO userDTO = intent.getParcelableExtra("USER");



        String id = intent.getExtras().getString("id");
        String password = intent.getExtras().getString("password");
        String name = intent.getExtras().getString("name");
        String tel = intent.getExtras().getString("tel");
        String address = intent.getExtras().getString("address");

        String msg = String.format("id : %s \npassword : %s \nname : %s \n tel : %s\n address : %s",
                userDTO.id, userDTO.password, userDTO.name, userDTO.tel, userDTO.addr);

        txt_msg.setText(msg);
    }
}