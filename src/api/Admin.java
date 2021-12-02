package api;

import api.date.Date;

public class Admin extends User {
    // This is useful so that the client may know if an admin account is still valid
    private boolean active;


    public Admin(String email, String password, Date dob) {
        super(email, password, dob);
        setActive();
    }


    public void setActive() {
        this.active = true;
    }
    public void setInactive(){
        this.active = false;
    }

    public boolean isActive(){
        return this.active;
    }
    
}
