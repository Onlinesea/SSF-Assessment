package vttp.ssf.assessment.cyptonews.controller;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vttp.ssf.assessment.cyptonews.model.Articles;
import vttp.ssf.assessment.cyptonews.service.NewsService;

@RestController
@RequestMapping(path= "/news", produces = MediaType.APPLICATION_JSON_VALUE)
public class NewsRESTController {
    
    private static final Logger logger = LoggerFactory.getLogger(NewsRESTController.class);

    @Autowired 
    NewsService svc;

    @GetMapping(path = "/{newsId}")
    public ResponseEntity<Articles> findById(@PathVariable String newsId){
        Articles result = svc.findById(newsId);

        return  ResponseEntity.ok(result);
 
        if( result != null){
            return ResponseEntity.ok(result);
        }else{

            JSONObject error=new JSONObject();
            String errorMsg = "Cannot find news article " + newsId;
            error.put("error",errorMsg);    
            
            return ResponseEntity.status(HttpStatus.NOT_FOUND);
        }

    }




}
