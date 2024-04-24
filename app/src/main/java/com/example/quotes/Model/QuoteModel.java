package com.example.quotes.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class QuoteModel {

        @SerializedName("text")
        @Expose
        private String text;
        @SerializedName("author")
        @Expose
        private String author;

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

    }

