package com.metacoders.home;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class WpPost {


        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("link")
        @Expose
        private String link;
        @SerializedName("title")
        @Expose
        private Title title;
        @SerializedName("excerpt")
        @Expose
        private Excerpt excerpt;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public Title getTitle() {
            return title;
        }

        public void setTitle(Title title) {
            this.title = title;
        }

        public Excerpt getExcerpt() {
            return excerpt;
        }

        public void setExcerpt(Excerpt excerpt) {
            this.excerpt = excerpt;
        }




    public class Excerpt {

        @SerializedName("rendered")
        @Expose
        private String rendered;

        public String getRendered() {
            return rendered;
        }

        public void setRendered(String rendered) {
            this.rendered = rendered;
        }

    }


    public class Title {

        @SerializedName("rendered")
        @Expose
        private String rendered;

        public String getRendered() {
            return rendered;
        }

        public void setRendered(String rendered) {
            this.rendered = rendered;
        }

    }

}








