package com.revature;


public class LogOn {

    private String passw;
    private int uid;

    public static boolean LogOn(int uid, String passw){
        //will ask database if user_table holds uid and passw on the same line
        String ucheck = "Select * From user_table where userId = "+ uid+ " and upassword = '"+passw+"';";
        //if true, return logged on (true).
        //if(){
        return true;
        //else{
        //if not in table, return false (login failed)
        //return false;}
    }

    public String getPassw() {
        return passw;
    }

    public void setPassw(String passw) {
        this.passw = passw;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }
}
