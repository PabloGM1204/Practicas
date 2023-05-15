package com.serbatic.facturas.controllers;

import com.serbatic.facturas.service.ArticleService;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.serbatic.facturas.accessingData.Article;
import com.serbatic.facturas.accessingData.ArticleRepository;

@Controller
@RequestMapping(path = "/article") // This means URL's start with /demo (after Application path)
public class ArticleController {

  @GetMapping("/")
  public String index() {
    return "index";
  }

  @Autowired
  private ArticleService articleService;

  @PostMapping(path = "/add") // Map ONLY POST Requests
  public @ResponseBody String addNewArticle(@RequestParam String name,
      @RequestParam String category, @RequestParam int stock, @RequestParam double price) {
    // @ResponseBody means the returned String is the response, not a view name
    // @RequestParam means it is a parameter from the GET or POST request
    Article savedArticle= articleService.addNewArticle(name,category,stock,price);
    return "Article saved with id "+savedArticle.getIdArt();
  }

  @PatchMapping(path = "/{idArt}") // Map ONLY PATCH Requests
  public ResponseEntity<Article> updateArticlePartially(
      @PathVariable(value = "idArt") Long artcleId, @RequestBody Article articleDetails)
      throws ResourceNotFoundException {
    Article updatedArticle = articleService.updateArticlePartially(artcleId,articleDetails);
    return ResponseEntity.ok(updatedArticle);
  }

  // This returns a json with the article information
  @GetMapping(path = "/{idArt}")
  public ResponseEntity<Article> findArticle(@PathVariable(value = "idArt") Long articleId)
      throws ResourceNotFoundException {
   Article article=articleService.findArticle(articleId);
      return ResponseEntity.ok().body(article);
  }

  // Delete

  @DeleteMapping(path = "/{idArt}")
  public @ResponseBody String deleteArticle(@PathVariable("idArt") Long id) {
    articleService.deleteArticle(id);
    return String.format("Article %d deleted", id);

    // You can add the option of returning the deleted article just in case if you want to create
    // some
    // sort of backup
  }

  @GetMapping(path = "/all")
  public @ResponseBody Iterable<Article> getAllArticles() {
    // This returns a JSON or XML with the articles
    return articleService.getAllArticles();
  }


}
