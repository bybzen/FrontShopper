package models;


public class Admin {
    private String username;
    private String password;

    public Admin() {
    }

    public Admin(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }


    public boolean checkAdminLogin(String username,String password){
        if(this.username.equals(username) && this.password.equals(password)) {
            return true;
//            if(this.password.equals(password)) {
//                return true;
//            }
//            else {
//                throw new IllegalArgumentException("The username/password you entered did not match.");
//            }
        }
        return false;
    }

}
