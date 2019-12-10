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
import java.text.SimpleDateFormat;
import javax.swing.table.DefaultTableModel;
import java.util.*;
import javax.swing.JOptionPane;

import model.User;
import model.Database;

import view.AdminView;
import view.UsersAdminView;

/**
 *
 * @author ganjarggt
 */
public class AdminController extends MouseAdapter implements ActionListener {
    private AdminView adminView;
    private UsersAdminView usersAdminView;
    
    public AdminController(){
        this.adminView = new AdminView();
        this.usersAdminView = new UsersAdminView();
        
        this.adminView.addActionListener(this);
        this.usersAdminView.addActionListener(this);
        this.usersAdminView.addMouseListener(this);
        
        this.adminView.setVisible(true);
    }
    
    private void loadUsersData(String is_load_librarian){
        Database databaseTool = new Database();
        DefaultTableModel model = new DefaultTableModel(new String[]{"Id User", 
                                                                     "Nama", 
                                                                     "Jenis Kelamin",
                                                                     "Email",
                                                                     "Tanggal Lahir",
                                                                     "Alamat",
                                                                     "Nomor Hp"}, 0);
        if(is_load_librarian.equals("is_not_load_librarian")){
            databaseTool.getUsersData();
        } else if(is_load_librarian.equals("is_load_librarian")){
            databaseTool.getLibrariansData();
        }
        ArrayList<User> users = databaseTool.getUsers();
        
        for(User user : users){
            String gender = String.valueOf(user.getGender());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-dd-MM");
            String birthDate = sdf.format(user.getDateOfBirth());
            model.addRow(new Object[]{user.getUserId(), user.getName(), gender, 
                                      user.getEmail(), birthDate, 
                                      user.getAddress(), user.getPhoneNumber()});
        }
        try{
            this.usersAdminView.setUsersProfileTableModel(model);
        } catch(Exception err){
            JOptionPane.showMessageDialog(null, err);
        }
    }
    
    private void setUsersDataToTextField(){
        int i = this.usersAdminView.getjTableUserProfile().getSelectedRow();
        String userId = String.valueOf(usersAdminView.getjTableUserProfile().getValueAt(i, 0));
        String name = String.valueOf(usersAdminView.getjTableUserProfile().getValueAt(i, 1));
        String email = String.valueOf(usersAdminView.getjTableUserProfile().getValueAt(i, 3));
        SimpleDateFormat sdf  = new SimpleDateFormat("yyyy-MM-dd");
        try{
            Date dateOfBirth = sdf.parse( String.valueOf(usersAdminView.getjTableUserProfile().getValueAt(i, 4)));
            this.usersAdminView.setjDateChooserUser(dateOfBirth);
        } catch(Exception ex){}
        String address = String.valueOf(usersAdminView.getjTableUserProfile().getValueAt(i, 5));
        String phoneNumber = String.valueOf(usersAdminView.getjTableUserProfile().getValueAt(i, 6));
        
        this.usersAdminView.setjTextFieldUserId(userId);
        this.usersAdminView.setjTextFieldName(name);
        if(String.valueOf(String.valueOf(usersAdminView.getjTableUserProfile().getValueAt(i, 2))).equals("L")){
            this.usersAdminView.setjRadioButtonMale();
        }
        if(String.valueOf(String.valueOf(usersAdminView.getjTableUserProfile().getValueAt(i, 2))).equals("P")){
            this.usersAdminView.setjRadioButtonFemale();
        }
        this.usersAdminView.setjTextFieldPhoneNumber(phoneNumber);
        this.usersAdminView.setjTextFieldEmail(email);
        this.usersAdminView.setjTextAreaAddress(address);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(source.equals(this.adminView.getButtonViewAllUsers())){    
            this.adminView.setVisible(false);
            this.loadUsersData("is_not_load_librarian");
            this.usersAdminView.setVisible(true);
        } else if(source.equals(this.usersAdminView.getjButtonBackUsersAdminView())){
            this.usersAdminView.setVisible(false);
            this.adminView.setVisible(true);
        } else if(source.equals(this.adminView.getButtonViewLibrarians())){
            this.adminView.setVisible(false);
            this.loadUsersData("is_load_librarian");
            this.usersAdminView.setVisible(true);
        } else if(source.equals(this.usersAdminView.getjButtonUpdate())){
            String name = usersAdminView.getjTextFieldName().getText();
            char gender = 'P';
            if(this.usersAdminView.getjRadioButtonMale().isSelected()){
                gender = 'L';
            }
            Date dateOfBirth = this.usersAdminView.getjDateChooserUser().getDate();
            String email = this.usersAdminView.getjTextFieldEmail().getText();
            String address = this.usersAdminView.getjTextAreaAddress().getText();
            String userId = this.usersAdminView.getjTextFieldUserId().getText();
            String phoneNumber = this.usersAdminView.getjTextFieldPhoneNumber().getText();
            
            User user = new User(name, gender, email, dateOfBirth, address, userId, phoneNumber);
            
            user.updateUserAdmin();
        } else if(source.equals(this.usersAdminView.getjButtonDelete())){
            User user = new User(this.usersAdminView.getjTextFieldEmail().getText());
            int dialogResult = JOptionPane.showConfirmDialog(null, "Apakah Yakin ??");
            if(dialogResult == JOptionPane.YES_OPTION){
                this.usersAdminView.getGenderButtonGroup().clearSelection();
                this.usersAdminView.setjTextFieldUserId("");
                this.usersAdminView.setjTextFieldName("");
                this.usersAdminView.getjDateChooserUser().cleanup();
                this.usersAdminView.setjTextFieldPhoneNumber("");
                this.usersAdminView.setjTextFieldEmail("");
                this.usersAdminView.setjTextAreaAddress("");
                user.deleteUser();
            }
        } else if(source.equals(this.adminView.getButtonAddLibrarian())){
            new RegisterLibrarianController();
            this.adminView.setVisible(false);
        } else if(source.equals(this.adminView.getButtonLogout())){
            this.adminView.setVisible(false);
            new LoginController();
        }
    } 
    
    @Override
    public void mousePressed(MouseEvent me){
        Object source = me.getSource();
        if(source.equals(this.usersAdminView.getjTableUserProfile())){
            this.setUsersDataToTextField();
        }
    }
    
}
