package services;

import database.NotesDBException;
import database.UserDB;
import models.User;
import java.util.List;

public class UserService {

    private UserDB userDB;

    public UserService() {
        userDB = new UserDB();
    }

    public User get(String username) throws Exception {
        return userDB.getUser(username);
    }
    
    public User getUserByEmail(String email) throws NotesDBException{
        return userDB.getUserByEmail(email);
    }

    public List<User> getAll() throws Exception {
        return userDB.getAll();
    }

    public int update(String username, String password, String firstname, String lastname, String email) throws Exception {
        User user = get(username);
        user.setPassword(password);
        user.setFirstname(firstname);
        user.setLastname(lastname);
        user.setEmail(email);
        return userDB.update(user);
    }

    public int delete(String username) throws Exception {
        User deletedUser = userDB.getUser(username);
        // do not allow the admin to be deleted
        if (deletedUser.getUsername().equals("admin")) {
            return 0;
        }
        return userDB.delete(deletedUser);
    }

    public int insert(String username, String password, String firstname, String lastname, String email) throws Exception {
        User user = new User(username, password);
        user.setFirstname(firstname);
        user.setLastname(lastname);
        user.setEmail(email);
        return userDB.insert(user);
    }
    
}
