package info.rixlabs.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by riccardo.causo on 04.05.2016.
 */

@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String username;
    private String password;

    protected Account() {}

    public Account(String username, String password){
        this.username = username;
        this.password = password;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    /*
    *
    * !!!!!!!!!!!!!!!!!!!PASSWORD TO THE WORLD !!!!!!!!!!!!!!!!!!!
    * !!!!!!!!!!!!!!!!!!! NOT FOR PRODUCTION !!!!!!!!!!!!!!!!!!!
    *
    * */
    @Override
    public String toString(){
        return String.format(
                "Account[id=%d, username='%s', password='%s']",
                id, username, password);
    }

}
