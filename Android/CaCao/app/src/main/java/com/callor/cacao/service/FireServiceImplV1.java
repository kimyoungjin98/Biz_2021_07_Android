package com.callor.cacao.service;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.callor.cacao.adapter.ChatAdapter;
import com.callor.cacao.model.ChatVO;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

/**
 * ChildEventListener
 * Firebase의 RealtimeDataBase의 변화(CUD)가 발생하면
 * FireBase에서 Android 모든 App에 신호를 보내고
 * 그 신호를 Event로 받아서 처리하는 클래스를 작성하기 위한 인터페이스
 */
public class FireServiceImplV1 implements ChildEventListener {

    /**
     * ChatAdapter 객체가 하는일
     * RecyclerView와 연동하여 데이터를 화면에 그리기 위한 중간 연결도구
     *
     * Adapter를 MainActivity로부터 전달받아서
     * firebaseㄹ database에 데이터가 추가되면
     * 데이터를 가져와서 화면에 그리기 위한 코드를 작성한다
     */
    private ChatAdapter adapter;

    public FireServiceImplV1(ChatAdapter adapter) {
        this.adapter = adapter;
    }

    /**
     * CUD중에서 새로운 데이터가 추가되는(Insert, Create) event가
     * 발생하면 실행되는 method
     *
     * 이 method에서 데이터가 추가되었다는 신호를 받으면
     * 데이터를 가져와서 Adapter의 chatList에 데이터를 추가한다
     * 그리고 RecyclerView에게 알림을 전한다
     * @param snapshot
     * @param previousChildName
     */
    @Override
    public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
        ChatVO chat = snapshot.getValue(ChatVO.class);
        adapter.addChatList(chat);
    }

    @Override
    public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

    }

    @Override
    public void onChildRemoved(@NonNull DataSnapshot snapshot) {

    }

    @Override
    public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

    }

    @Override
    public void onCancelled(@NonNull DatabaseError error) {

    }
}
