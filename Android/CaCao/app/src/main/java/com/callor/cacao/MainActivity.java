package com.callor.cacao;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

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

    private String nickname = "익명";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences preferences
                = PreferenceManager.getDefaultSharedPreferences(this);

        nickname = preferences.getString("nickname", "익명");

        String alarm = preferences.getString("alarm", "ON");

        /**
         * custom된 toolbar를 ActionBar로 설정하기 위한 코드
         */
        Toolbar main_toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(main_toolbar);

        /**
         * 새로운 Activity가 열렸을 때
         * 이전 Activity로 돌아가기 아이콘을 표시하기
         * MainActivity에서는 의미가 없기 때문에 사용하지 않는다
         */
//        ActionBar actionBar = getSupportActionBar();
//        actionBar.setDisplayHomeAsUpEnabled(true);

        chat_list = findViewById(R.id.chat_list);
        txt_msg = findViewById(R.id.txt_msg);
        btn_send = findViewById(R.id.btn_send);

        chatList = new ArrayList<ChatVO>();

//        chat_adapter = new ChatAdapter(chatList);
        chat_adapter = new ChatAdapter(chatList, nickname);

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
                chat.setName(nickname);

                dbRef.push().setValue(chat);
                txt_msg.setText("");

            }

        });


    }

    /**
     * ActionBar에 설정된 Option Menu의 특정한 항목(item)을
     * 클릭하면 호출되는 method
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int menu_item = item.getItemId();
        if(menu_item == R.id.app_bar_settings){
            Toast.makeText(this, "설정메뉴 클릭됨", Toast.LENGTH_SHORT).show();

            Intent setting_intent
                    = new Intent(MainActivity.this, SettingsActivity.class);
            startActivity(setting_intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Custom한 Toolbar가 Activity에 적용될 때
     * setSupportActionBar() method가 실행될 때
     * event가 발생하고 자동으로 호출되는 method
     *
     * Toolbar를 사용하여 ActionBar를 Custom하는 이유중에 하나가
     * onCreateOptionsMenu() method를 Override하여
     * 더욱 세밀한 Customizing을 하기 위해서 이다
     *
     * ToolBar에 사용자 정의형 menu를 설정하여
     * 다른 기능을 수행하도록 하는 UI를 구현할 수 있다
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main_tool_menu, menu);


        return true;
    }
}