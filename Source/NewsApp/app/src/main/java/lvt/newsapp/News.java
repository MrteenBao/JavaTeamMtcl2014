package lvt.newsapp;

import java.util.ArrayList;

public class News {
    String url;
    String title;
    String imageUrl;
    String content;
    ArrayList<String> hastTag;
    String date;

    public News(String url, String title, String imageUrl, String content, ArrayList<String> hastTag, String date) {
        this.url = url;
        this.title = title;
        this.imageUrl = imageUrl;
        this.content = content;
        this.hastTag = hastTag;
        this.date = date;
    }

    public News(String url, String title, String imageUrl, String date) {
        this.url = url;
        this.title = title;
        this.imageUrl = imageUrl;
        this.date = date;
    }

    public News(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ArrayList<String> getHastTag() {
        return hastTag;
    }

    public void setHastTag(ArrayList<String> hastTag) {
        this.hastTag = hastTag;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
