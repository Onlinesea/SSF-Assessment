package vttp.ssf.assessment.cyptonews.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.json.JsonObject;

public class Articles {
    private static final Logger logger = LoggerFactory.getLogger(Articles.class);

    public String id;
    public String published_on; 
    public String title;
    public String url;
    public String imageUrl;
    public String body;
    public String tags;
    public String categories;
    public boolean toSave = false; 
    


    public boolean isToSave() {
        return toSave;
    }


    public void setToSave(boolean toSave) {
        this.toSave = toSave;
    }


    public static Articles createArticles(JsonObject o){
        Articles article = new Articles();
        //logger.info("Article ACCESSED*******************");

        article.id = o.getString("id");
        //logger.info("Article id COMPLETED*******************"); 
        //logger.info(article.id);

        article.published_on = o.getJsonNumber("published_on").toString();
        //logger.info("Article publsihed_on COMPLETED*******************"); 
        //logger.info(article.published_on);


        article.title= o.getString("title");
        //logger.info("Article title COMPLETED*******************"); 
        //logger.info(article.title);

        article.url= o.getString("url");
        //logger.info("Article url COMPLETED*******************"); 
        //logger.info(article.url);

        article.imageUrl = o.getString("imageurl");
        //logger.info("Article imageUrl COMPLETED*******************"); 
        //logger.info(article.imageUrl);

        article.body= o.getString("body");
        //logger.info("Article body COMPLETED*******************"); 
        //logger.info(article.body);

        article.tags = o.getString("tags");
        //logger.info("Article tags COMPLETED*******************"); 
        //logger.info(article.tags);

        article.categories = o.getString("categories");
        //logger.info(article.categories);
        
        //logger.info("Article initialised COMPLETED*******************"); 

        return article; 
    }


    //Getters and setters
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getPublished_on() {
        return published_on;
    }
    public void setPublished_on(String published_on) {
        this.published_on = published_on;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getImageUrl() {
        return imageUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    public String getBody() {
        return body;
    }
    public void setBody(String body) {
        this.body = body;
    }
    public String getTags() {
        return tags;
    } 
    public void setTags(String tags) {
        this.tags = tags; 
    }
    public String getCategories() {
        return categories;  
    }
    public void setCategories(String categories) {
        this.categories = categories; 
    }
}
