/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.time.format.DateTimeFormatter;

import view.LibrarianView;
import view.BorrowBookView;
import view.AddBookView;
import view.BooksView;
import view.ReturnBookView;

import model.Book;
import model.Database;
import model.UserRecord;

/**
 *
 * @author ganjarggt
 */
public class LibrarianController extends MouseAdapter implements ActionListener {
    private LibrarianView librarianView;
    private BorrowBookView borrowBookView;
    private AddBookView addBookView;
    private ReturnBookView returnBookView;
    private BooksView booksView;
    private DefaultTableModel model = new DefaultTableModel(
                                        new String[]{"Kode Buku", 
                                                     "Judul", 
                                                     "Penulis",
                                                     "Penerbit",
                                                     "Kategori"}, 0);    
    
    public LibrarianController(){
        this.librarianView = new LibrarianView();
        this.borrowBookView = new BorrowBookView();
        this.addBookView = new AddBookView();
        this.returnBookView = new ReturnBookView();
        this.booksView = new BooksView();
        
        this.librarianView.addActionListener(this);
        this.borrowBookView.addActionListener(this);
        this.addBookView.addActionListener(this);
        this.returnBookView.addActionListener(this);
        this.booksView.addActionListener(this);
        this.booksView.addMouseListener(this);
        
        this.borrowBookView.addMouseListener(this);
        this.returnBookView.addMouseListener(this);
        
        this.librarianView.setVisible(true);
        
    }
    
    private void loadBooksData(String isLoadedFor){
        Database databaseTool = new Database();
        DefaultTableModel model = new DefaultTableModel(
                                        new String[]{"Kode Buku", 
                                                     "Judul", 
                                                     "Penulis",
                                                     "Penerbit",
                                                     "Kategori",
                                                     "Status"}, 0);
        databaseTool.getBooksData();
        ArrayList<Book> books = databaseTool.getBooks();
        for(Book book : books){
            model.addRow(new Object[]{
                book.getBookCode(), book.getTitle(), book.getAuthor(),
                book.getPublisher(), book.getBookCategory(), book.getStatus()});
        }
        if(isLoadedFor.equals("add_books_view")){
            this.addBookView.setJTableShowBooks(model);
        } else if(isLoadedFor.equals("view_books_view")){
            this.booksView.setjTableBooksView(model);
        } else if(isLoadedFor.equals("borrow_book_view")){
            this.borrowBookView.setJtableShowBooks(model);
        }
    }
    
    private void loadUsersRecordsData(){
        Database databaseTool = new Database();
        DefaultTableModel model = new DefaultTableModel(
                                        new String[]{
                                            "Kode Peminjaman",
                                            "Kode Buku",
                                            "Judul",
                                            "Tanggal Dipinjam",
                                            "Tanggal Pengembalian"},0);
        databaseTool.getUsersRecordsData();
        ArrayList<UserRecord> usersRecords = databaseTool.getUsersRecords();
        for(UserRecord userRecord : usersRecords){
            Object returnDate = userRecord.getReturnedDate();
            if(returnDate == null){
                returnDate = "belum dikembalikan";
            }
            model.addRow(new Object[]{
                userRecord.getRecordId(),
                userRecord.getBookCode(), userRecord.getTitle(), 
                userRecord.getBorrowedDate(),returnDate
            });
        }
        this.returnBookView.setJtableShowbuku(model);
    }
    
    private void setBookDataToTextField(){
        int i = this.booksView.getjTableBooksView().getSelectedRow();
        String code = String.valueOf(booksView.getjTableBooksView().getValueAt(i, 0));
        String title = String.valueOf(booksView.getjTableBooksView().getValueAt(i, 1));
        String author = String.valueOf(booksView.getjTableBooksView().getValueAt(i, 2));
        String publisher = String.valueOf(booksView.getjTableBooksView().getValueAt(i, 3));
        String category = String.valueOf(booksView.getjTableBooksView().getValueAt(i, 4));
        this.booksView.setjTextFieldTitle(title);
        this.booksView.setjTextFieldBooksCode(code);
        this.booksView.setjTextFieldPublisher(publisher);
        this.booksView.setjTextFieldAuthor(author);
        this.booksView.setjTextFieldCategory(category);
    }
    
    private void setBorrowBookDataToTextField(){
        int i = this.borrowBookView.getJtableShowBooks().getSelectedRow();
        String code = String.valueOf(borrowBookView.getJtableShowBooks().getValueAt(i, 0));
        String title = String.valueOf(borrowBookView.getJtableShowBooks().getValueAt(i, 1));
        String author = String.valueOf(borrowBookView.getJtableShowBooks().getValueAt(i, 2));
        String publisher = String.valueOf(borrowBookView.getJtableShowBooks().getValueAt(i, 3));
        String category = String.valueOf(borrowBookView.getJtableShowBooks().getValueAt(i, 4));
        this.borrowBookView.setJtextTitle(title);
        this.borrowBookView.setJtextFieldBookCode(code);
        this.borrowBookView.setJtextPublisher(publisher);
        this.borrowBookView.setJtextAuthor(author);
        this.borrowBookView.setJtextCategory(category);    
    }
    
    private void setReturnBookDataToTextField(){
        int i = this.returnBookView.getJtableShowbuku().getSelectedRow();
        String bookCode = String.valueOf(returnBookView.getJtableShowbuku().getValueAt(i, 1));
        String recordId = String.valueOf(returnBookView.getJtableShowbuku().getValueAt(i, 0));
        this.returnBookView.setJtextCodebook(bookCode);
        this.returnBookView.setJtextRecordNum(recordId);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(source.equals(this.librarianView.getJbuttonBorrowBook())){
            this.librarianView.setVisible(false);
            this.loadBooksData("borrow_book_view");
            this.borrowBookView.setVisible(true);

        } else if(source.equals(this.borrowBookView.getJbuttonBorrow())){
            Database db = new Database();
            String bookCode = this.borrowBookView.getJtextFieldBookCode().getText();
            String email = this.borrowBookView.getJtextFieldEmail().getText();
            Date borrowDate = this.borrowBookView.getjDateChooserBorrowDate().getDate();
            try {
                db.insertNewBorrowBook(bookCode, email, borrowDate);
            } catch (ParseException ex) {
                Logger.getLogger(LibrarianController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } else if(source.equals(this.returnBookView.getJbuttonReturnBook())){
            Database db = new Database();
            String email = this.returnBookView.getJtextEmail().getText();
            String bookCode = this.returnBookView.getJtextBookCode().getText();
            String recordId = this.returnBookView.getJtextRecordNum().getText();
            Date returnDate = this.returnBookView.getjDateChooserReturnDate().getDate();
            
            db.setReturnBook(email, bookCode, recordId, returnDate);
        }
        
        else if(source.equals(this.librarianView.getJbuttonAddBook())){
            this.librarianView.setVisible(false);
            this.loadBooksData("add_books_view");
            this.addBookView.setVisible(true);
            
        } else if(source.equals(this.booksView.getjButtonUpdate())){
            String title = this.booksView.getjTextFieldTitle().getText();
            String bookCode = this.booksView.getjTextFieldBooksCode().getText();
            String author = this.booksView.getjTextFieldAuthor().getText();
            String publisher = this.booksView.getjTextFieldPublisher().getText();
            String category = this.booksView.getjTextFieldCategory().getText();
            
            Book book = new Book(bookCode, title, author, publisher, category);
            
            Database db = new Database();
            db.updateBook(book);
            
        } else if(source.equals(this.booksView.getjButtonDelete())){
            
            Book book = new Book(this.booksView.getjTextFieldBooksCode().getText());
            Database db = new Database();
            int dialogResult = JOptionPane.showConfirmDialog(null, "Apakah Yakin ??");
            if(dialogResult == JOptionPane.YES_OPTION){
                this.booksView.setjTextFieldTitle("");
                this.booksView.setjTextFieldBooksCode("");
                this.booksView.setjTextFieldPublisher("");
                this.booksView.setjTextFieldAuthor("");
                this.booksView.setjTextFieldCategory("");
                db.deleteBook(book);
            }
        } else if(source.equals(this.addBookView.getJbuttonSubmit())){
            
            String title = this.addBookView.getJtextTitle().getText();
            String bookCode = this.addBookView.getJtextCodebook().getText();
            String author = this.addBookView.getJtextAuthor().getText();
            String publisher = this.addBookView.getJtextPublisher().getText();
            String category = this.addBookView.getJtextCategory().getText();
            
            Book book = new Book(bookCode, title, author, publisher, category);
            
            Database db = new Database();
            db.insertNewBook(book);
            
        } else if(source.equals(this.librarianView.getjButtonViewBooks())){
            
            this.librarianView.setVisible(false);
            this.loadBooksData("view_books_view");
            this.booksView.setVisible(true);
            
        } else if(source.equals(this.librarianView.getJbuttonReturnBook())){
            
            this.librarianView.setVisible(false);
            this.loadUsersRecordsData();
            this.returnBookView.setVisible(true);
        
        } else if(source.equals(this.booksView.getjButtonBackUsersAdminView())){
            this.booksView.setVisible(false);
            this.librarianView.setVisible(true);
            
        } else if(source.equals(this.borrowBookView.getJbuttonBack())){
            
            this.borrowBookView.setVisible(false);
            this.loadUsersRecordsData();
            this.librarianView.setVisible(true);
            
        } else if(source.equals(this.addBookView.getJbuttonBack())){
            
            this.addBookView.setVisible(false);
            this.librarianView.setVisible(true);
            
        } else if(source.equals(this.returnBookView.getJbuttonBack())){
            
            this.returnBookView.setVisible(false);
            this.librarianView.setVisible(true);
            
        } else if(source.equals(this.librarianView.getjButtonLogout())){
            this.librarianView.setVisible(false);
            new LoginController();
        }
    }
    
    @Override
    public void mousePressed(MouseEvent me){
        Object source = me.getSource();
        if(source.equals(this.booksView.getjTableBooksView())){
            this.setBookDataToTextField();
        } else if(source.equals(this.borrowBookView.getJtableShowBooks())){
            this.setBorrowBookDataToTextField();
        } else if(source.equals(this.returnBookView.getJtableShowbuku())){
            this.setReturnBookDataToTextField();
        }
    }
    
}
