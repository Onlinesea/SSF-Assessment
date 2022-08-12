package vttp.ssf.assessment.cyptonews.model;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

public class ArticleList {
    private static final Logger logger = LoggerFactory.getLogger(ArticleList.class);

    private String message;
    public List<Articles> articleList= new LinkedList<>();

    public static ArticleList createArticleList(String json) throws IOException{
        ArticleList articleList = new ArticleList();
        //logger.info("ArticleList ACCESSED*******************");

        try(InputStream is = new ByteArrayInputStream(json.getBytes())){
            JsonReader r = Json.createReader(is);
            JsonObject o = r.readObject();
            articleList.message =o.getString("Message");
            //logger.info("Message received************** >>> " + articleList.message );

            articleList.articleList = o.getJsonArray("Data").stream().map(v -> (JsonObject)(v))
                            .map(v -> Articles.createArticles(v))
                            .toList();

        //logger.info("List Created*******************");
        //logger.info("List2***************>>>>>> " + articleList.articleList.get(2).getId()); 
        }catch(IOException e){
            e.printStackTrace();
        }

        return articleList;
    }

}
