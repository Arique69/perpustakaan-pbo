/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author ganjarggt
 */
public class Book {
    private String title, author, publisher, bookCode, category, status;
    
    public Book(){
    }
    
    public Book(String bookCode, String status){
        this.bookCode = bookCode;
        this.status = status;
    }
    
    public Book(String bookCode, String title, String author, String publisher, String category){
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.bookCode = bookCode;
        this.category = category;
    }
    
    public Book(String bookCode, String title, String author, String publisher, String category, String status){
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.bookCode = bookCode;
        this.category = category;
        this.status = status;
    }    
    
    public Book(String bookCode){
        this.bookCode = bookCode;
    }
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
    
    public void setPublisher(String publisher){
        this.publisher = publisher;
    }
    
    public String getPublisher(){
        return publisher;
    }
    
    public void setBookCode(String bookCode){
        this.bookCode = bookCode;
    }
    
    public String getBookCode(){
        return this.bookCode;
    }
    
    public void setBookCategory(String category){
        this.category = category;
    }
    
    public String getBookCategory(){
        return this.category;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }  
}
