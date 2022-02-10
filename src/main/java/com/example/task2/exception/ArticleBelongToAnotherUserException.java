package com.example.task2.exception;

public class ArticleBelongToAnotherUserException extends Exception {
    public ArticleBelongToAnotherUserException(String message) {
        super(message);
    }
}
