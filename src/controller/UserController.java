/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.table.DefaultTableModel;

import java.util.*;
import model.Book;

import model.User;
import model.Database;
import model.UserRecord;
import view.UserView;
import view.UserRecordsView;

/**
 *
 * @author ganjarggt
 */
public class UserController extends MouseAdapter implements ActionListener {
    private String email;
    
    private List<String> userData = new LinkedList<>();
    private List<Book> books = new LinkedList<>();
    private List<UserRecord> userRecords = new LinkedList<>();
    
    private UserView userView;
    private UserRecordsView userRecordsView;
    
    public UserController(String email){
        this.email = email;
        User user = new User(this.email);
        userData = user.getUserData(this.email);
        
        this.userView = new UserView();
        this.userRecordsView = new UserRecordsView();
        this.userView.setEmailTextField(userData.get(0));
        this.userView.setUserName(userData.get(1));
        this.userView.setNameTextField(userData.get(1));
        this.userView.setGenderUser(userData.get(2).toCharArray()[0]);
        
        String userDateOfBirth = userData.get(3);
        Date userDoB = new Date();
        try{
            userDoB = new SimpleDateFormat("yyyy-MM-dd").parse(userDateOfBirth);    
        } catch(ParseException e){}
        this.userView.setDateOfBirthTextField(userDoB);
        
        this.userView.setAddressTextField(userData.get(4));
        this.userView.setPhoneNumberTextField(userData.get(5));
        
        userView.addActionListener(this);
        userView.addMouseAdapter(this);
        userRecordsView.addActionListener(this);
        this.loadBooksData();
              
        userView.setVisible(true);
    }
    
    private void loadBooksData(){
        Database db = new Database();
        DefaultTableModel model = new DefaultTableModel(new String[]{"Kode Buku", 
                                                                     "Judul", 
                                                                     "Pengarang", 
                                                                     "Penerbit",
                                                                     "Kategori",
                                                                     "Status"}, 0);        
        db.getBooksData();
        books = db.getBooks();
        for(Book book : books){
            model.addRow(new Object[] { book.getBookCode(), book.getTitle(), 
                                        book.getAuthor(), book.getPublisher(), 
                                        book.getBookCategory(), book.getStatus()});                                            
        }
        userView.setjTableListBooks(model);         
    }
    
    private void loadUserRecordsData(){
        Database db = new Database();
        DefaultTableModel userRecordsModel = new DefaultTableModel(new String[]{
                                                                "Nomor Peminjaman",
                                                                "Kode Buku",
                                                                "Tanggal Peminjaman",
                                                                "Tanggal Pengembalian"}, 0);   
        
        db.getUserRecordsByUserdEmail(this.userView.getEmailTextField().getText());
        userRecords = db.getUsersRecords();
        for(UserRecord userRecord : userRecords){
            Object returnDate = userRecord.getReturnedDate();
            if(returnDate == null){
                returnDate = "Belum dikembalikan";
            }
            userRecordsModel.addRow(new Object[] { userRecord.getRecordId(), 
                                                   userRecord.getBookCode(),
                                                   userRecord.getBorrowedDate(), 
                                                   returnDate});              
        }
        this.userRecordsView.getjTableUserRecords().setModel(userRecordsModel);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        String email = this.email;
        if(source.equals(userView.getButtonUserRecord())){
            this.userView.setVisible(false);
            this.loadUserRecordsData();
            this.userRecordsView.setVisible(true);
        } else if(source.equals(userView.getButtonUpdateProfile())){
           String name = userView.getNameTextField().getText();
           char gender = 'L';
           if(userView.getRadioButtonFemale().isSelected()){
               gender = 'P';
           }
           String updatedEmail = userView.getEmailTextField().getText();
           Date dateOfBirth = userView.getDateOfBirthTextField().getDate();
           String address = userView.getAddressTextField().getText();
           String phoneNumber = userView.getPhoneNumberTextField().getText();
           User user = new User(name, gender, updatedEmail, dateOfBirth, address, 
                                phoneNumber);
           user.updateUser(this.userData.get(6));
        } else if(source.equals(this.userView.getButtonLogout())){
            new LoginController();
            this.userView.setVisible(false);
        } else if(source.equals(this.userRecordsView.getjButtonBack())){
            this.userRecordsView.setVisible(false);
            this.userView.setVisible(true);
        }
    }
}
