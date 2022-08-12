package vttp.ssf.assessment.cyptonews.controller;

import java.io.IOException;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import vttp.ssf.assessment.cyptonews.model.ArticleList;
import vttp.ssf.assessment.cyptonews.service.NewsService;

@Controller
public class NewsController { 
    
    private static final Logger logger = LoggerFactory.getLogger(NewsController.class);

    @Autowired
    private NewsService newsSvc;


    @GetMapping("/api")
    public String getNewsArticles(Model model){ 

        Optional<ArticleList> opt = newsSvc.getArticle();

        if(opt.isEmpty()){
            return "error";

        }else{
            logger.info("Id of the first article >>>>>>>>>>>>>>>>>>>>> " + opt.get().articleList.get(0).getId());

            model.addAttribute("ArticleList", opt.get().articleList.toArray());
            return "text"; 
        }
    }

    @GetMapping("/")
    public String getNewArticle(Model model) throws IOException{
        
        
        ArticleList artList = newsSvc.getArticleList();
        //logger.info(">>>>>>>>>>>>>>>>" + artList.articleList.get(2).getId());
        model.addAttribute("artList", artList.articleList.toArray());
        return "text";    
    } 
/* 
    @PostMapping("/articles")
    public String saveArticles(Model model, Articles[] artArr) throws IOException{

        //logger.info("POSTMAPPING success**********" + artArr[0].getId());

        for(int i =0; i<artArr.length;i++){
            if(artArr[i].toSave){
                newsSvc.save(artArr[i]);
            }
        }
        ArticleList artList = newsSvc.getArticleList();
        model.addAttribute("artList", artList.articleList.toArray());
         
        return "text"; 
        
    }
*/

}
