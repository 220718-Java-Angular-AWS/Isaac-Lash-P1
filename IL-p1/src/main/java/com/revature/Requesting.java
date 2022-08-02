package com.revature;

public class Requesting {
    private  int uid;
    private String rcomment;
    private double amount;
    private String reason;

    public static void Requesting(int uid, String rcomment, double amount, String reason){
        //create sql statement to add request to requests table
        String Req = "insert into requests (userId, rcomment, amount, reason)\n" +
                "\tvalues ("+uid+", '"+rcomment+"',"+amount+", '"+reason+"')";

        //submit req to db

        //


    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getRcomment() {
        return rcomment;
    }

    public void setRcomment(String rcomment) {
        this.rcomment = rcomment;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
