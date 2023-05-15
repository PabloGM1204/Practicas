package com.serbatic.facturas.service;

import com.serbatic.facturas.accessingData.Article;
import org.apache.velocity.exception.ResourceNotFoundException;

public interface ArticleServiceInterface {
    Article addNewArticle(String name, String category, int stock, double price);

    Article updateArticlePartially(Long artId, Article artDetails) throws ResourceNotFoundException;

    Article findArticle(Long artId) throws ResourceNotFoundException;

    void deleteArticle(Long id);

    Iterable<Article> getAllArticles();
}
