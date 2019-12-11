/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.*;

/**
 *
 * @author ganjarggt
 */
public class UserRecord {
    String recordId, userId, bookCode, title;
    Date borrowedDate, returnedDate;
    
    User user = new User();
    Book book = new Book();

    public UserRecord(String recordId, String userId, String bookCode, String title,
                      Date borrowedDate, Date returnedDate){
        this.user.setUserId(userId);
        this.book.setBookCode(bookCode);
        this.book.setTitle(title);
        this.userId = userId;
        this.recordId = recordId;
        this.bookCode = bookCode;
        this.title = title;
        this.borrowedDate = borrowedDate;
        this.returnedDate = returnedDate;
    }
    
    public UserRecord(String recordId, String userId, String bookCode,
                      Date borrowDate, Date returnedDate){
        this.recordId = recordId;
        this.userId = userId;
        this.bookCode = bookCode;
        this.borrowedDate = borrowDate;
        this.returnedDate = returnedDate;
    }
    
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getBookCode() {
        return bookCode;
    }

    public void setBookCode(String bookCode) {
        this.bookCode = bookCode;
    }

    public Date getBorrowedDate() {
        return borrowedDate;
    }

    public void setBorrowedDate(Date borrowedDate) {
        this.borrowedDate = borrowedDate;
    }

    public Date getReturnedDate() {
        return returnedDate;
    }

    public void setReturnedDate(Date returnedDate) {
        this.returnedDate = returnedDate;
    }

    public String getRecordId() {
        return recordId;
    }

    public String getTitle() {
        return title;
    }
}
