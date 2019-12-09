/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.*;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JOptionPane;
import java.text.SimpleDateFormat;

import databaseUtility.Connector;

/**
 *
 * @author ganjarggt
 */
public class User extends Person{
    
    private String userId;
    private String phoneNumber, email, password;
    private Date dateOfBirth;
    private char roleId;
    
    public User(){
    }
    
    public User(String email){
        this.email = email;
    }
    
    public User(String email, String password){
        this.email = email;
        this.password = password;
    }
    
    // constructor untuk mendapatkan user
    public User(String userId, String name, char gender, Date birthDate, 
                String phoneNumber, String email, String address){
        super(name, gender, address);
        this.dateOfBirth = birthDate;
        this.userId = userId;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }
    
    // constructor untuk update user data
    public User(String name, char gender, String email, Date dateOfBirth,
                String address, String phoneNumber){
        
        super(name, gender, address);
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
    }
    
    // constructor untuk membuat user baru
    public User(String name, char gender, String email, String password, 
                Date dateOfBirth,String address, char roleId, String userId, 
                String phoneNumber){
        super(name, gender, address);
        
        this.email = email;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.userId = userId;
        this.roleId = roleId;
        this.phoneNumber = phoneNumber;
    }
     
    // untuk update admin
    public User(String name, char gender, String email, Date dateOfBirth, 
                String address, String userId, String phoneNumber){
        super(name, gender, address);
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.userId = userId;
        this.phoneNumber = phoneNumber;        
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public char getRoleId() {
        return roleId;
    }

    public void setRoleId(char roleId) {
        this.roleId = roleId;
    }
    
    public void registerNewUser(){
        String birthDate = null;
        if(this.getName().equals("")){
            JOptionPane.showMessageDialog(null, "Nama tidak boleh kosong");
        } else if(this.getEmail().equals("")){
            JOptionPane.showMessageDialog(null, "Email tidak boleh kosong");
        } else if(this.cekEmail(this.email)){
            JOptionPane.showMessageDialog(null, "Email Sudah digunakan");
        } else if(this.getPassword().equals("")){
            JOptionPane.showMessageDialog(null, "Password tidak boleh kosong");
        } else {
            if(this.getDateOfBirth() != null){
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-dd-MM");
                birthDate = dateFormat.format(this.getDateOfBirth());
                
                PreparedStatement pst;
                String query = "INSERT INTO users VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
                try{
                    pst = Connector.getConnection().prepareCall(query);
                    pst.setString(1, this.getName());
                    pst.setString(2, String.valueOf(this.getGender()));
                    pst.setString(3, this.getEmail());
                    pst.setString(4, this.getPassword());
                    pst.setString(5, birthDate);
                    pst.setString(6, this.getAddress());
                    pst.setString(7, String.valueOf(this.getRoleId()));
                    pst.setString(8, this.getUserId());
                    pst.setString(9, this.getPhoneNumber());
                    
                    if(pst.executeUpdate() > 0){
                        JOptionPane.showMessageDialog(null, "Akun Berhasil Dibuat");
                    }
                    
                } catch(SQLException ex){
                    JOptionPane.showMessageDialog(null, "Terjadi Masalah !!");
                    JOptionPane.showMessageDialog(null, ex);
                }
            }
        }
    }
    
    public void updateUser(String userId){
    // function untuk update user;
        String updateQuery = "UPDATE users SET "
                + "name = '" + super.getName()
                + "', gender = '" + super.getGender()
                + "', email = '" + this.email
                + "', tgl_lahir = '" + this.convertJavaDateToSqlDate(this.dateOfBirth)
                + "', address = '" + super.getAddress()
                + "', phone_number = '" + this.phoneNumber
                + "' WHERE user_id = '" + userId + "'";
        try{
            Statement stmt = Connector.getConnection().createStatement();
            stmt.executeUpdate(updateQuery);
            JOptionPane.showMessageDialog(null, "Update Data Berhasil   !!!");
            JOptionPane.showMessageDialog(null, "Silahkan Login Kembali !!!");
            stmt.close();
        } catch(SQLException err){
            JOptionPane.showMessageDialog(null,"Terjadi Kesalahan");
            JOptionPane.showMessageDialog(null, err);
        }
    }
    
    public void updateUserAdmin(){
    // function untuk update user;
        String updateQuery = "UPDATE users SET "
                + "name = '" + super.getName()
                + "', gender = '" + super.getGender()
                + "', email = '" + this.getEmail()
                + "', tgl_lahir = '" + this.convertJavaDateToSqlDate(this.getDateOfBirth())
                + "', address = '" + super.getAddress()
                + "', phone_number = '" + this.getPhoneNumber()
                + "', user_id = '" + this.getUserId()
                + "' WHERE user_id = '" + this.getUserId()+ "'";
        try{
            Statement stmt = Connector.getConnection().createStatement();
            stmt.executeUpdate(updateQuery);
            JOptionPane.showMessageDialog(null, "Update Data Berhasil   !!!");
            JOptionPane.showMessageDialog(null, "Silahkan Login Kembali !!!");
            stmt.close();
        } catch(SQLException err){
            JOptionPane.showMessageDialog(null,"Terjadi Kesalahan");
            JOptionPane.showMessageDialog(null, err);
        }
    }

    public void deleteUser(){
        String deleteQuery = "DELETE FROM users WHERE email = '" + this.email +"'";
        try{
            Statement stmt = Connector.getConnection().createStatement();
            stmt.execute(deleteQuery);
            JOptionPane.showMessageDialog(null, "Delete Data Berhasil   !!!");
            JOptionPane.showMessageDialog(null, "Silahkan Login Kembali !!!");            
        } catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Terjadi Kesalahan   !!!");
            JOptionPane.showMessageDialog(null, ex);
        }
    }    
    
    public boolean validateUser(String emailFromJTextField, String passwordFromJtextField){
        String password = "salahgan";
        PreparedStatement ps;
        ResultSet rs;
        
        String query = "SELECT password FROM users WHERE email = ? ";
        
        try{
            ps = Connector.getConnection().prepareCall(query);
            ps.setString(1, email);
            rs = ps.executeQuery();
            
            if(rs.next()){
                password = rs.getString("password");
            }
            rs.close();
            ps.close();
        } catch(SQLException e){
            JOptionPane.showMessageDialog(null, "terjadi error");
            JOptionPane.showMessageDialog(null, e);
        }
        
        return password.equals(passwordFromJtextField);
    }
    
    public boolean isAdmin(String email){
        return "1".equals(this.checkRoleId(email));
    }
    
    public boolean isLibrarian(String email){
        return "2".equals(this.checkRoleId(email));
    }
    
    public boolean cekEmail(String email){
        PreparedStatement ps;
        ResultSet rs;
        boolean isAlreadySignup = false;
        
        String query = "SELECT * FROM `register_user` WHERE `new_email`=?";
        
        try {
            ps = Connector.getConnection().prepareStatement(query);
            ps.setString(1, email);
            rs = ps.executeQuery();
            
            if(rs.next()){
                isAlreadySignup = true;
            }
            rs.close();
            ps.close();
        } catch (SQLException ex) {}
        return isAlreadySignup;
    }

    public String checkRoleId(String email){
        String localRoleId = "null";
            try{
                String query = "SELECT role_id FROM users WHERE email = ?";
                Connection conn = Connector.getConnection();
                PreparedStatement pst = conn.prepareStatement(query);
                pst.setString(1, email);
                ResultSet rs = pst.executeQuery();
                while(rs.next()){
                    localRoleId = rs.getString("role_id");
                }
                rs.close();
                pst.close();
            } catch(SQLException ex){
                JOptionPane.showMessageDialog(null, ex);
            }
        return localRoleId;
        }
    
    /**
     *
     * @param email
     * @return all public user data
     */
    public List getUserData(String email){
        List<String> userData = new LinkedList<>();
        String query = "SELECT email, name, gender, tgl_lahir, address, phone_number, user_id FROM users WHERE email = ?";
        try{
            Connection conn = Connector.getConnection();
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, email);
            ResultSet st = pst.executeQuery(); 
            while(st.next()){
                userData.add(st.getString("email"));
                userData.add(st.getString("name"));
                userData.add(st.getString("gender"));
                userData.add(st.getString("tgl_lahir"));
                userData.add(st.getString("address"));
                userData.add(st.getString("phone_number"));
                userData.add(st.getString("user_id"));
            }
            st.close();
            pst.close();
        } catch(SQLException ex){}
        return userData;
    }
    
    public List getUsersData(){
        List<String> userData = new LinkedList<>();
        String query = "SELECT email, name, gender, tgl_lahir, address, phone_number, user_id FROM users";
        try{
            Connection conn = Connector.getConnection();
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, email);
            ResultSet st = pst.executeQuery(); 
            while(st.next()){
                userData.add(st.getString("email"));
                userData.add(st.getString("name"));
                userData.add(st.getString("gender"));
                userData.add(st.getString("tgl_lahir"));
                userData.add(st.getString("address"));
                userData.add(st.getString("phone_number"));
                userData.add(st.getString("user_id"));
            }
            st.close();
            pst.close();
        } catch(SQLException ex){}
        return userData;      
    }
    
    public java.sql.Date convertJavaDateToSqlDate(java.util.Date date) {
       return new java.sql.Date(date.getTime());
    }    
}
