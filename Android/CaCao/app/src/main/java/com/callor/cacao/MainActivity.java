package com.callor.cacao;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.EditText;

import com.callor.cacao.adapter.ChatAdapter;
import com.callor.cacao.model.ChatVO;
import com.callor.cacao.service.FireServiceImplV1;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText txt_msg;
    private AppCompatButton btn_send;

    private RecyclerView chat_list;
    private ChatAdapter chat_adapter;
    private List<ChatVO> chatList;

    private DatabaseReference dbRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chat_list = findViewById(R.id.chat_list);
        txt_msg = findViewById(R.id.txt_msg);
        btn_send = findViewById(R.id.btn_send);

        chatList = new ArrayList<ChatVO>();
        chat_adapter = new ChatAdapter(chatList);
        chat_list.setAdapter(chat_adapter);


        FirebaseDatabase dbConn = FirebaseDatabase.getInstance();
        dbRef = dbConn.getReference("chatting");

        ChildEventListener childEventListener
                = new FireServiceImplV1(chat_adapter);

        dbRef.addChildEventListener(childEventListener);

        RecyclerView.LayoutManager layoutManager
                = new LinearLayoutManager(this);
        chat_list.setLayoutManager(layoutManager);



        btn_send.setOnClickListener(view->{

            String msg = txt_msg.getText().toString();
            if(msg != null && !msg.isEmpty()){

                ChatVO chat = new ChatVO();
                chat.setMsg(msg);
                chat.setName("영진");

                dbRef.push().setValue(chat);
                txt_msg.setText("");

            }

        });


    }
}