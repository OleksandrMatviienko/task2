package com.example.task2.dto;


import com.example.task2.entity.ArticleEntity;

public class Article {
    private String name;
    private String text;

    public static Article toDTO(ArticleEntity articleEntity){
        Article article = new Article();
        article.setName(articleEntity.getName());
        article.setText(articleEntity.getText());
        return article;
    }

    public Article() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
