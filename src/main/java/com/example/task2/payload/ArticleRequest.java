package com.example.task2.payload;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
public class ArticleRequest {
    @NotEmpty
    @Size(min=3, max=30, message = "articleName should contain 3-30 characters")
    private String articleName;

    @NotEmpty
    @Size(min=10, max=200, message = "articleText should contain 10-200 characters")
    private String articleText;
}
