package com.callor.cacao.adapter;

import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.callor.cacao.R;
import com.callor.cacao.model.ChatVO;

import org.w3c.dom.Text;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter {

    private List<ChatVO> chatList;
    private String name;

    public ChatAdapter(List<ChatVO> chatList) {
        this(chatList, "익명");
    }

    public ChatAdapter(List<ChatVO> chatList, String name) {

        this.chatList = chatList;
        this.name = name;
    }

    public void addChatList(ChatVO chat){

        chatList.add(chat);

        notifyItemInserted(chatList.size()-1);

    }



    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View item_layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_item, parent, false);

        ChattViewHolder ViewHolder = new ChattViewHolder(item_layout);


        return ViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        ChatVO chat = chatList.get(position);

        ChattViewHolder chattViewHolder = (ChattViewHolder) holder;

        chattViewHolder.item_name.setText(chat.getName());
        chattViewHolder.item_msg.setText(chat.getMsg());


        /**
         * 현재 App에서 보낸 메시지를 DB에서 가져왔으면(Fetch)
         *
         */
        if(this.name.equals(chat.getName())){

            chattViewHolder.item_name.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_END);
            chattViewHolder.item_msg.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_END);

            chattViewHolder.msgLinear.setGravity(Gravity.RIGHT);
            chattViewHolder.item_msg.setBackgroundColor(Color.parseColor("#FF2B3B"));

        }


    }

    @Override
    public int getItemCount() {
        return chatList == null ? 0 : chatList.size();
    }

    public static class ChattViewHolder extends RecyclerView.ViewHolder{

        public TextView item_name;
        public TextView item_msg;
        public LinearLayout msgLinear;

        public ChattViewHolder(@NonNull View itemView) {
            super(itemView);

            item_name = itemView.findViewById(R.id.item_name);
            item_msg = itemView.findViewById(R.id.item_msg);

            /**
             * item_name과 item_msg를 감싸고 있는 layout에 접근하기 위하여
             * 객체로 생성
             */
            msgLinear = itemView.findViewById(R.id.msg_linear);
        }
    }
}
