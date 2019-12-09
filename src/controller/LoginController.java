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

import javax.swing.JOptionPane;

import model.User;
import view.LoginView;

public class LoginController extends MouseAdapter implements ActionListener{
    private LoginView loginView;
    
    public LoginController(){
        this.loginView = new LoginView();
        this.loginView.addActionListener(this);
        this.loginView.setVisible(true);
    }   
    
    @Override
    public void actionPerformed(ActionEvent e){
        Object source = e.getSource();
        String email = loginView.getjTextFieldEmail().getText();
        String password = loginView.getjTextFieldPassword().getText();
        User user = new User(email, password);
        
        if(source.equals(loginView.getButtonLogin())){
            if(user.validateUser(email, password) && !user.isAdmin(email) && !user.isLibrarian(email)){
                    new UserController(email);
                    this.loginView.setVisible(false);
            } else if((user.validateUser(email, password) && user.isLibrarian(email)) && !user.isAdmin(email)){
                    // user adalah librarian
                    new LibrarianController();
                    this.loginView.setVisible(false);
            } else if((user.validateUser(email, password) && user.isAdmin(email)) && !user.isLibrarian(email)){
                    new AdminController();
                    this.loginView.setVisible(false);
            } else {
                    JOptionPane.showMessageDialog(null, "Email atau password tidak valid !!!");
            }            
        } else if(source.equals(loginView.getButtonRegister())){
            new RegistrationController();
            this.loginView.setVisible(false);
        }
    }
    
    @Override
    public void mousePressed(MouseEvent me){
        Object source = me.getSource();
    }
}
