package com.callor.library.apdapter;

import android.graphics.Color;
import android.os.Build;
import android.text.Html;
import android.text.Layout;
import android.text.Spannable;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.text.HtmlCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.callor.library.databinding.ActivityMainBinding;
import com.callor.library.databinding.BookItemViewBinding;
import com.callor.library.model.BookDTO;

import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<BookDTO> bookList ;

    public BookAdapter(List<BookDTO> bookList) {
        this.bookList = bookList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        BookItemViewBinding bookItemViewBinding
                = BookItemViewBinding.inflate(
                        layoutInflater,
                        parent,
                        false);

        RecyclerView.ViewHolder viewHolder
                = new BookViewHolder(bookItemViewBinding);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        BookViewHolder bookHolder = (BookViewHolder) holder;
        BookDTO bookDTO = bookList.get(position);

        String strTitle = "<font color=blue>" + bookDTO.getTitle() + "</font>";

        strTitle = "<span style='color:#0000FF'>" + bookDTO.getTitle() + "<span>";

        TextView txt_title = bookHolder.bookItemView.itemTxtTitle;
        txt_title.setText(HtmlCompat.fromHtml(strTitle, HtmlCompat.FROM_HTML_MODE_LEGACY));

//        bookHolder.bookItemView.itemTxtTitle.setText(bookDTO.getTitle());


        Spannable span = (Spannable) bookHolder.bookItemView.itemTxtTitle.getText();
        span.setSpan(new ForegroundColorSpan(Color.BLUE), 0, txt_title.getText().length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


    }
    @Override
    public int getItemCount() {
        return bookList == null ? 0 : bookList.size() ;
    }

    /**
     * ??? ????????? item??? ???????????? ?????? View ?????? ????????????
     */
    public static class BookViewHolder extends RecyclerView.ViewHolder {

        /**
         * DataBinding??? true??? ?????? ?????????
         * book_item_view.xml layout ????????? ????????????
         * ???????????? ???????????? ?????????
         *
         * DataBinding??? ????????????
         * layout.xml??? ????????? Component??? ????????? ????????? ???????????? ?????????
         * binding ?????? ????????? ???????????? ???????????? ???????????? ?????? ????????????
         */
        public BookItemViewBinding bookItemView;
//        public TextView txt_name;

        public BookViewHolder(@NonNull BookItemViewBinding bookItemViewBinding) {
            super(bookItemViewBinding.getRoot());
            bookItemView = bookItemViewBinding;

        }

        public void bind(List<BookDTO> bookDTO){

        }
    }
}
