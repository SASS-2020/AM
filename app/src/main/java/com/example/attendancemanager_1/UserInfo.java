package com.example.attendancemanager_1;

public class UserInfo {
    private String userName;
    private String userID;

    public UserInfo() {
    }

    public UserInfo(String userName, String userID)
    {
        this.userName = userName;
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }


}
