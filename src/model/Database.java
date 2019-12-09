/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.*;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.Random;
import java.util.Date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;

import databaseUtility.Connector;

public class Database {
    private Connection conn;
    private Statement stmt;
    private ResultSet rs;
    
    private ArrayList<User> users = new ArrayList<>();
    private ArrayList<Book> books = new ArrayList<>();
    private ArrayList<UserRecord> usersRecords = new ArrayList<>();
    
    public Database(){}
    
    public ArrayList<User> getUsers(){
        return users;
    }
    
    public ArrayList<Book> getBooks(){
        return books;
    }
    
    public ArrayList<UserRecord> getUsersRecords(){
        return usersRecords;
    }
    
    public void getUsersData(){
        connect();
        try{
            String query = "SELECT * FROM users WHERE role_id = 3";
            this.rs = this.stmt.executeQuery(query);
            while(rs.next()){
                this.users.add(new User(rs.getString("user_id"), 
                                        rs.getString("name"), 
                                        rs.getString("gender").charAt(0),
                                        rs.getDate("tgl_lahir"), 
                                        rs.getString("phone_number"), 
                                        rs.getString("email"), 
                                        rs.getString("address")));
            }
        } catch(SQLException ex){
            JOptionPane.showMessageDialog(null,ex);
        }
        disconnect();
    }
    
    public void getLibrariansData(){
        this.connect();
        try{
            String query = "SELECT * FROM users WHERE role_id = 2";
            rs = stmt.executeQuery(query);
            while(rs.next()){
                users.add(new User(rs.getString("user_id"), 
                                        rs.getString("name"), 
                                        rs.getString("gender").charAt(0),
                                        rs.getDate("tgl_lahir"), 
                                        rs.getString("phone_number"), 
                                        rs.getString("email"), 
                                        rs.getString("address")));
            }
        } catch(SQLException ex){
            JOptionPane.showMessageDialog(null,ex);
        }
        disconnect();
    }
    
    public void getUsersRecordsData(){
        connect();
        String query = "SELECT * FROM user_record JOIN books USING(book_code)";
        try{
            rs = stmt.executeQuery(query);
            while(rs.next()){
                usersRecords.add(new UserRecord(
                        rs.getString("record_id"), rs.getString("user_id"), 
                        rs.getString("book_code"), rs.getString("title"),
                        rs.getDate("borrowed_date"),rs.getDate("returned_date")
                ));
            }
            disconnect();
        } catch(SQLException ex){
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    
    public void getUserRecordsByUserdEmail(String email){
        connect();
        String userId = this.getUserIdByEmail(email);
        String query = "SELECT * FROM user_record WHERE user_id = '" + userId + "'";
        
        try{
            stmt.execute(query);
            rs = stmt.getResultSet();
            
            while(rs.next()){
                usersRecords.add(new UserRecord(
                        rs.getString("record_id"), rs.getString("user_id"), 
                        rs.getString("book_code"),
                        rs.getDate("borrowed_date"),rs.getDate("returned_date")
                ));
            }
        } catch(SQLException err){
            JOptionPane.showMessageDialog(null, err);
        }
    }
    
    public void getBooksData(){
        connect();
        try{
            String query = "SELECT * FROM books";
            rs = stmt.executeQuery(query);
            // public Book(String bookCode, String title, String author, String publisher, String category)
            while(rs.next()){
                books.add(new Book(rs.getString("book_code"),
                                   rs.getString("title"),
                                   rs.getString("author"),
                                   rs.getString("publisher"),
                                   rs.getString("category"),
                                   rs.getString("status")));
            }
            rs.close();
        } catch(SQLException ex){
            JOptionPane.showMessageDialog(null, ex);
        }
        disconnect();
    }
    
    public void insertNewBook(Book book){
        connect();
        try{
            String query = "INSERT INTO books VALUES("
                           + "'" + book.getBookCode() + "'"
                           + ",'" + book.getTitle() + "'"
                           + ",'" + book.getAuthor() + "'"
                           + ",'" + book.getPublisher() + "'"
                           + ",'" + book.getBookCategory() + "'"
                           + ",'tersedia')";
            stmt.executeUpdate(query);
            JOptionPane.showMessageDialog(null, "Buku Berhasil disimpan !!");
            disconnect();
        } catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Terjadi Kesalahan");
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    
    public void updateBook(Book book){
        connect();
        try{
            String query = "UPDATE books SET "
                           + "book_code = '" + book.getBookCode() + "'"
                           + ",title = '" + book.getTitle() + "'"
                           + ",author = '" + book.getAuthor() + "'"
                           + ",publisher = '" + book.getPublisher() + "'"
                           + ",category = '" + book.getBookCategory() + "'"
                           + " WHERE book_code = '" + book.getBookCode() + "'";
            stmt.executeUpdate(query);
            JOptionPane.showMessageDialog(null, "Buku Berhasil di update !!");
            disconnect();
        } catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Terjadi Kesalahan");
            JOptionPane.showMessageDialog(null, ex);
        }        
    }
    
    public void deleteBook(Book book){
        String deleteQuery = "DELETE FROM books WHERE book_code = '" + book.getBookCode() +"'";
        try{
            Statement stmt = Connector.getConnection().createStatement();
            stmt.execute(deleteQuery);
            JOptionPane.showMessageDialog(null, "Delete Buku Berhasil   !!!");
            disconnect();
        } catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Terjadi Kesalahan   !!!");
            JOptionPane.showMessageDialog(null, ex);
        }        
    }
    
    public void insertNewBorrowBook(String bookCode, String email, Date borrowedDate) throws ParseException{
        connect();
        Random rand = new Random();
        String recordId = "0019" + String.valueOf(rand.nextInt(1000)) + String.valueOf(rand.nextInt(1000));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-dd-MM");
        String borrDate = formatter.format(borrowedDate);        
        if(isNotBorrowedBook(bookCode)){
            String userId = this.getUserIdByEmail(email);
            String query = "INSERT INTO user_record(`record_id`, `user_id`, `book_code`, `borrowed_date`) VALUES("
                           + "'" + recordId + "'"
                           + ",'" + userId + "'"
                           + ",'" + bookCode + "'"
                           + ",'" + borrDate + "');";
            JOptionPane.showMessageDialog(null, query);
            try{
                stmt.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Peminjaman Berhasil !!");
                setBookToBorrowed(bookCode);
                disconnect();
            } catch(SQLException err){
                JOptionPane.showMessageDialog(null, "terjadi kesalahan");
                JOptionPane.showMessageDialog(null, err);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Peminjaman Gagal !!");
            JOptionPane.showMessageDialog(null, "Buku telah dipinjam sebelumnya!!");
        }
    }
    
    public void setBookToBorrowed(String bookCode){
        if(isNotBorrowedBook(bookCode)){
            connect();
            String query = "UPDATE books SET status = 'dipinjam' WHERE book_code = '" + bookCode + "'";
            try{
                stmt.executeUpdate(query);
            } catch(SQLException err){
                JOptionPane.showMessageDialog(null, err);
            }
        }
    }
    
    private boolean isNotBorrowedBook(String bookCode){
        String checkQuery = "SELECT status FROM books WHERE book_code = '" + bookCode + "'";
        String statusBuku = "notvalid";
        connect();
        try{
            rs = stmt.executeQuery(checkQuery);
            while(rs.next()){
               statusBuku = rs.getString("status");
            }
            return statusBuku.equals("tersedia");
            } catch(SQLException err){
            JOptionPane.showMessageDialog(null, err);
        }
        return false;
    }
    
    public void setReturnBook(String email, String bookCode, String recordId, Date returnDate){
        String userId = getUserIdByEmail(email);
        Date borrowedDate = getBorrowedBookDateByCode(recordId);

        String query = "UPDATE user_record SET returned_date = '" + this.convertJavaDateToSqlDate(returnDate) + "' WHERE `record_id` = '" + recordId + "'";
        JOptionPane.showMessageDialog(null, query);
        connect();
        
        if(returnDate.after(borrowedDate)){
            try{
                stmt.executeUpdate(query);
                this.setBookToAvaible(bookCode);
                JOptionPane.showMessageDialog(null, "PENGEMBALIAN BUKU BERHASIL !!!");
            } catch(SQLException err){
                JOptionPane.showMessageDialog(null, "TERJADI ERROR SAAT PENGEMBALIAN BUKU !!!");
                JOptionPane.showMessageDialog(null, err);
            }                        
        } else {
            JOptionPane.showMessageDialog(null, "Tanggal pengembalian tidak valid");
        }
    }
    
    public void setBookToAvaible(String bookCode){        
        String query = "UPDATE books SET status = 'tersedia' WHERE book_code = '" + bookCode + "'";
        try{
            stmt.executeUpdate(query);
        } catch(SQLException err){
            JOptionPane.showMessageDialog(null, "terjadi kesalahan pada setBookAvaible");
            JOptionPane.showMessageDialog(null, err);
        }
    }
    
    private Date getBorrowedBookDateByCode(String recordId){
        String query = "SELECT borrowed_date FROM user_record WHERE record_id = '" + recordId + "'";
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-dd-MM");
        Date borrDate = new Date();
        String borrowDate = "invalid";
        connect();
        try{
            rs = stmt.executeQuery(query);
            while(rs.next()){
                borrowDate = rs.getString("borrowed_date");
            }
        } catch(SQLException err){
            JOptionPane.showMessageDialog(null, "kesalahan saat mengambil data peminjaman");
            JOptionPane.showMessageDialog(null, err);
        }
        
        try {
            borrDate = formatter.parse(borrowDate);
        } catch (ParseException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return borrDate;
    }
    
    private String getUserIdByEmail(String email){
        connect();
        String query = "SELECT user_id FROM users WHERE email = '" + email + "'";
        String userId = "notvalid";
        try{
            rs = stmt.executeQuery(query);
            while(rs.next()){
                userId =  rs.getString("user_id");
            }
        } catch(SQLException err){
            JOptionPane.showMessageDialog(null, err);
        }
        return userId;
    }
    
    private void connect(){
        try{
            this.conn = Connector.getConnection();
            this.stmt = conn.createStatement();
        } catch(SQLException ex){
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void disconnect(){
        try {
            conn.close();
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    public java.sql.Date convertJavaDateToSqlDate(java.util.Date date) {
       return new java.sql.Date(date.getTime());
    }
}
