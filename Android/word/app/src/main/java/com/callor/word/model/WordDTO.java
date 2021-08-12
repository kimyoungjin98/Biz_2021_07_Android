package com.callor.word.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity(tableName = "tbl_word")
public class WordDTO {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name="word")
    private String word; // 영어단어

    @ColumnInfo(name="korea")
    private String korea; // 한글 뜻

    @NonNull
    public String getWord() {
        return word;
    }

    public String getKorea() {
        return korea;
    }

    public void setWord(@NonNull String word) {
        this.word = word;
    }

    public void setKorea(String korea) {
        this.korea = korea;
    }

    @Override
    public String toString() {
        return "WordDTO{" +
                "word='" + word + '\'' +
                ", korea='" + korea + '\'' +
                '}';
    }
}
