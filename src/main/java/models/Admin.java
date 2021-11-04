package models;


public class Admin {
    private String username;
    private String password;
    private String adminId;

    public Admin() {
    }

    public Admin(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAdminId() {
        return adminId;
    }

    public String getUsername() {

        return username;
    }

    public String getPassword() {

        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


//    public boolean checkAdminLogin(String username, String password){
//        if(this.username.equals(username) && this.password.equals(password)) {
//            System.out.println(username);
//            System.out.println(password);
//            return true;
//        }
//        return false;
//    }
//
//    public boolean checkUsername(String username){
//        if(this.username.equals(username)) {
//            return true;
//        }
//        return false;
//    }

}
