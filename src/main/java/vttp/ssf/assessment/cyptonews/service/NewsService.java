package vttp.ssf.assessment.cyptonews.service;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;

import org.apache.tomcat.util.json.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import vttp.ssf.assessment.cyptonews.model.ArticleList;
import vttp.ssf.assessment.cyptonews.model.Articles;

@Service
public class NewsService {

    private static final Logger logger = LoggerFactory.getLogger(NewsService.class);

    @Autowired
    @Qualifier("news")
    RedisTemplate<String, Articles> redisTemplate;

    private static String URL="https://min-api.cryptocompare.com/data/v2/news/";



    public Optional<ArticleList> getArticle(){
        String apikey = System.getenv("CYPTO_API_KEY");
        String NewsUrl= UriComponentsBuilder.fromUriString(URL)
                        .queryParam("lang","EN")
                        .queryParam("api_key", apikey)
                        .toUriString();
        
        //the link is corrected, api called 
        logger.info(NewsUrl);
        RestTemplate template = new RestTemplate();
        ResponseEntity<String> resp = null;

        //logger.info("Correct api******************************");
        try{
            resp = template.getForEntity(NewsUrl, String.class);
            //logger.info("Response received****************");
            ArticleList articleList = ArticleList.createArticleList(resp.getBody());
            //logger.info("ArticleList created*****************");

            return Optional.of(articleList); 

        }catch(IOException e){
            e.printStackTrace();

            return Optional.empty();
        }

    }
    public ArticleList getArticleList()throws IOException{
        String file = "D:/VisaWorkshop/cyptonews/repsonse.json";
        String json = new String(Files.readAllBytes(Paths.get(file)));
        ArticleList articleList = ArticleList.createArticleList(json);
        
        return articleList;
    }

    public Articles findById(final String articleId){
        Articles result = (Articles) redisTemplate.opsForValue().get(articleId);

        return result;
    }

    public void save(Articles art){
        redisTemplate.opsForValue().set(art.getId(), art);
        Articles result = (Articles) redisTemplate.opsForValue().get(art.getId());
    }

}
