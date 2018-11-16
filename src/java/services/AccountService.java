/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import database.NotesDBException;
import database.UserDB;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.User;
import sun.util.logging.PlatformLogger;

/**
 *
 * @author awarsyle
 */
public class AccountService {

    public User login(String username, String password, String path) {
        try {
            UserDB userDB = new UserDB();
            User user = userDB.getUser(username);

            if (user.getPassword().equals(password)) {
                // successful login
                Logger.getLogger(AccountService.class.getName())
                        .log(Level.INFO, "User {0} logged in.", user.getUsername());

                String email = user.getEmail();
                String subject = "Notes App Login";
                String template = path + "/emailtemplates/login.html";

                HashMap<String, String> tags = new HashMap<>();
                tags.put("firstname", user.getFirstname());
                tags.put("date", ((new java.util.Date())).toString());

                GmailService.sendMail(email, subject, template, tags);

                return user;
            }
        } catch (Exception e) {

        }

        return null;
    }

    public boolean forgotPassword(String email, String path) {
        try {
            

            UserService userservice = new UserService();
            User user = userservice.getUserByEmail(email);
            
            if(user != null){
                String firstname = user.getFirstname();
                String lastname = user.getLastname();
                String username = user.getUsername();
                String password = user.getPassword();
                
                String subject = "Notes Keepr Login";
                String template = path + "/emailtemplates/newlogin.html";
                
                HashMap<String, String> tags = new HashMap<>();
                tags.put("firstname", firstname);
                tags.put("lastname", lastname);
                tags.put("username", username);
                tags.put("password", password);
                
                GmailService.sendMail(email, subject, template, tags);
            }
            
            return true;
        } catch (NotesDBException ex) {
            Logger.getLogger(AccountService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }

}
