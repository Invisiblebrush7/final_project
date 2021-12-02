package api;

import api.date.Date;
import api.encryption.RSA;


public class User {
    
    protected String email;
    protected String password;
    protected Date dateOfBirth;

    private static RSA myRSA = RSA.getInstance();

    public User(){
        this("unknown@test.com", "password", new Date(1, 1, 2000));
    }

    public User(String email, String password, Date dob){
        setEmail(email);
        setPassword(password);
        setDateOfBirth(dob);
    }

    public void setEmail(String email){
        if (!email.equals("")) {
            this.email = email;
        } else {
            this.email = "test@test.com";
        }
    }
    public void setPassword(String password){
        if (!password.equals("")) {
            password = User.myRSA.encryptMessage(password);
            this.password = password;
        } else {
            this.password = "password";
        }        
    }
    public void setDateOfBirth(Date dob){
        this.dateOfBirth = dob;
    }
    public String getEmail(){
        return this.email;
    }
    public String getPassword(){
        return User.myRSA.decryptMessage(this.password);
    }
    public Date getDateOfBirth(){
        return this.dateOfBirth;
    }
}
