package com.callor.yj.adpter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.callor.yj.R;
import com.callor.yj.model.Chatt;

import java.util.List;

/*
    RecyclerView.Adapter 구현한 클래스
    RecyclerView에 데이터를 표현하고자 할 때 사용하는 Helper class(도와주는 도구 클래스 )
 */
public class ChattAdapter extends RecyclerView.Adapter {

    private List<Chatt> chattList;

    /*
     * 외부에서 chatt 데이터 아이템을 List에 추가하고 추가된 List는 RecyclerView를 통해서 화면에 다시 그려지게 될 것이다
     * @param chatt
     */
    public void addChattList(Chatt chatt) {

        // 메시지를 List에 추가하기
        chattList.add(chatt);

        // 'RecyclerView에게 chattList가 변화되었으니 다시 화면에 그려라'라고 지시하기
        // chattList의 끝(size() - 1 의 위치)에 값이 추가되었으니 다시 그려라
        notifyItemInserted(chattList.size() - 1);

    }

    /*
        RecyclerView가 화면에 그릴 데이터들을 전달받을 통로
        @Param chattList
     */
    public ChattAdapter(List<Chatt> chattList) {
        this.chattList = chattList;
    }

    /*
     * chatt_item.xml 파일을 읽어서 한 개의 아이템을 화면에 그리ㅁ
     * @param parent
     * @param viewType
     * @return
     */

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        /*
         * LayoutInflater.from().inflate(chatt_item)
         *
         * chatt_item.xml 파일은 한 개의 파일이 화면 전체를 구성하지 않고 여기서는 RecyclerView 내부에 각각의 데이터 item을 그린 도구로 사용이 된다.
         * 이러한 layout은 setContentView를 사용하지 않고 layoutInflater.inflate() 함수를 사용하여 만든다.
         */
        View item_layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.chatt_item, parent, false);

        ChattViewHolder viewHolder = new ChattViewHolder(item_layout);

        return viewHolder;
    }

    // chattList에서 1개의 데이터를 getter하여 chatt_item.xml 파일과 함께 Rendering 수행할 method()
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        // 전체 chattList에서 현재 화면에 그릴 item 추출하기
        Chatt chatt = chattList.get(position);

        // Cast to ( 형변환 )
        ChattViewHolder chattViewHolder = (ChattViewHolder) holder;

        chattViewHolder.item_name.setText(chatt.getName());
        chattViewHolder.item_msg.setText(chatt.getMsg());

    }

    /*
        RecyclerView가 데이터들을 화면에 표시할 때 참조하는 함수
        @Return
     */
    @Override
    public int getItemCount() {
        return chattList == null ? 0 : chattList.size();
    }

    // class 내부에 in class 선언
    public static  class ChattViewHolder extends RecyclerView.ViewHolder {

        public TextView item_name;
        public TextView item_msg;

        // 각 데이터를 표현하기 위한 chatt_item.xml의 View 객체(2개의 TextView)를 초기화하는 method
        public ChattViewHolder(@NonNull View itemView) {
            super(itemView);

            item_name = itemView.findViewById(R.id.item_name);
            item_msg = itemView.findViewById(R.id.item_msg);
        }

    }

}