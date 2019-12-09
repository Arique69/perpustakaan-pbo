/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.swing.JOptionPane;

import model.User;
import view.RegistrationView;

/**
 *
 * @author ganjarggt
 */
public class RegistrationController extends MouseAdapter implements ActionListener{
    RegistrationView registrationView;
    
    public RegistrationController(){
        this.registrationView = new RegistrationView();
        this.registrationView.addActionListener(this);
        this.registrationView.setVisible(true);
    }
   
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(source.equals(this.registrationView.getButtonCreateAccount())){
            String name = this.registrationView.getjTextFieldName().getText();
            char gender = 'P';
            if(this.registrationView.getRadioButtonMale().isSelected()){
                gender = 'L';
            }
            Date dateOfBirth = this.registrationView.getjDateDateBirth().getDate();
            String email = this.registrationView.getjTextFieldEmail().getText();
            String password = this.registrationView.getjPasswordField1().getText();
            String address = this.registrationView.getjTextAreaAddress().getText();
            char roleId = '3';
            Random rand = new Random();
            String userId = "000000" + String.valueOf(rand.nextInt(10000));
            String phoneNumber = this.registrationView.getjTextPhoneNumber().getText();
            
            User user = new User(name, gender, email, password, dateOfBirth, address, 
                                 roleId, userId, phoneNumber);
            user.registerNewUser();
        }
        if(source.equals(this.registrationView.getButtonBackToLogin())){
            new LoginController();
            this.registrationView.setVisible(false);
        }
    }

}
