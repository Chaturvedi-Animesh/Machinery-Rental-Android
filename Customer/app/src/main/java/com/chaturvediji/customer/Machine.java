package com.chaturvediji.customer;

public class Machine {

    String id;
    String type;
    String manuf;
    String model;
    String dop;
    String rent;
    String condi;
    String cid;
    String sid;

    public Machine(){

    }

    public Machine(String id, String type, String manuf, String model, String dop, String rent, String condi, String sid) {
        this.id = id;
        this.type = type;
        this.manuf = manuf;
        this.model = model;
        this.dop = dop;
        this.rent = rent;
        this.condi = condi;
        this.cid ="";
        this.sid = sid;
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getManuf() {
        return manuf;
    }

    public String getModel() {
        return model;
    }

    public String getDop() {
        return dop;
    }

    public String getRent() {
        return rent;
    }

    public String getCondi() {
        return condi;
    }

    public String getCid() {
        return cid;
    }

    public String getSid() {
        return sid;
    }
}


