package main.java.com.sowatec.pg.stack;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class UserDBO extends AbstractDBO {
    private String username;
    private String email;
    private String password;

    public UserDBO(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public UserDBO(int id, String username, String email, String password) {
        super(id);
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public UserDBO() {

    }

    public static void register(UserDBO userDBO) {
        DatabaseExecutor.registerUser(userDBO);
    }

    public void register() {
        register(this);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

}
