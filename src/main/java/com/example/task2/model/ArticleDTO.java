package com.example.task2.model;

import com.example.task2.entity.ArticleEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleDTO {

    private UUID articleId;

    private String articleName;

    private String articleText;

    public static ArticleDTO toArticleDTO(ArticleEntity article){

        ArticleDTO model = new ArticleDTO();
        model.setArticleId(article.getId());
        model.setArticleName(article.getName());
        model.setArticleText(article.getText());

        return model;
    }

    public static ArticleEntity toArticleEntity(ArticleDTO article){

        ArticleEntity model = new ArticleEntity();
        model.setId(article.getArticleId());
        model.setName(article.getArticleName());
        model.setText(article.getArticleText());

        return model;
    }

}
