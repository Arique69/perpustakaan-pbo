/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.util.Date;
import java.util.Random;
import model.User;

import view.RegisterNewLibrarianView;

/**
 *
 * @author ganjarggt
 */
public class RegisterLibrarianController extends MouseAdapter implements ActionListener{
    private RegisterNewLibrarianView registerNewLibrarianView;
    
    public RegisterLibrarianController(){
        this.registerNewLibrarianView = new RegisterNewLibrarianView();
        this.registerNewLibrarianView.addActionListener(this);
        this.registerNewLibrarianView.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(source.equals(this.registerNewLibrarianView.getButtonCreateAccount())){
            String name = this.registerNewLibrarianView.getjTextFieldName().getText();
            char gender = 'P';
            if(this.registerNewLibrarianView.getRadioButtonMale().isSelected()){
                gender = 'L';
            }
            Date dateOfBirth = this.registerNewLibrarianView.getjDateDateBirth().getDate();
            String email = this.registerNewLibrarianView.getjTextFieldEmail().getText();
            String password = this.registerNewLibrarianView.getjPasswordField1().getText();
            String address = this.registerNewLibrarianView.getjTextAreaAddress().getText();
            char roleId = '2';
            Random rand = new Random();
            String userId = "000002" + String.valueOf(rand.nextInt(10000));
            String phoneNumber = this.registerNewLibrarianView.getjTextPhoneNumber().getText();
            
            User user = new User(name, gender, email, password, dateOfBirth, address, 
                                 roleId, userId, phoneNumber);
            user.registerNewUser();
        } else if(source.equals(this.registerNewLibrarianView.getButtonBackToLogin())){
            new AdminController();
            this.registerNewLibrarianView.setVisible(false);
        }
    }
}
