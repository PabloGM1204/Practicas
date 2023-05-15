package com.serbatic.facturas.service;

import com.serbatic.facturas.accessingData.Article;
import com.serbatic.facturas.accessingData.ArticleRepository;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleService implements ArticleServiceInterface{

    @Autowired
    private ArticleRepository articleRepository;
    @Override
    public Article addNewArticle(String name, String category, int stock, double price) {
        Article art=new Article();
        art.setName(name);
        art.setCategory(category);
        art.setStock(stock);
        art.setPrice(price);
        return articleRepository.save(art);
    }

    @Override
    public Article updateArticlePartially(Long artId, Article artDetails) throws ResourceNotFoundException {
        Article art=articleRepository.findById(artId).orElseThrow(() -> new ResourceNotFoundException("Article not found on :: "+artId));

        if(artDetails.getName()!=null){
            art.setName(artDetails.getName());
        }
        if(artDetails.getCategory()!=null){
            art.setCategory(artDetails.getCategory());
        }
        art.setStock(artDetails.getStock());//Because null is 0 and 0 is allowed in stock

        if(artDetails.getPrice()>0){ //price minor or equal to 0 is not allowed
            art.setPrice(artDetails.getPrice());
        }
        
        return articleRepository.save(art);
    }

    @Override
    public Article findArticle(Long artId) throws ResourceNotFoundException {
        return articleRepository.findById(artId)
                .orElseThrow(() -> new ResourceNotFoundException("Article not found on : " + artId));
    }

    @Override
    public void deleteArticle(Long id) {
     articleRepository.deleteById(id);
    }

    @Override
    public Iterable<Article> getAllArticles() {
        return articleRepository.findAll();
    }
}
