package info.rixlabs.core.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

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
    private Date lastPasswordReset;

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
    public Date getLastPasswordReset(){ return lastPasswordReset; };
    public void setLastPasswordReset(Date lastPasswordReset){ this.lastPasswordReset = lastPasswordReset; };

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
