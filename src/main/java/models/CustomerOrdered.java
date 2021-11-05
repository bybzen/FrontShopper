package models;

public class CustomerOrdered {

    private String orderedID;
    private String status;
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setOrderedID(String orderedID) {
        this.orderedID = orderedID;
    }

    public String getOrderedID() {
        return orderedID;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

}
