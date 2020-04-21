package com.chaturvediji.dealer;



public class DealerAddition {
    String id;
    String loc;
    String name;
    String bank;
    String ifsc;


    public DealerAddition(){

    }

    public DealerAddition(String id, String loc, String name, String bank, String ifsc) {
        this.id = id;
        this.loc = loc;
        this.name = name;
        this.bank = bank;
        this.ifsc = ifsc;
    }

    public String getId() {
        return id;
    }

    public String getLoc() {
        return loc;
    }

    public String getName() {
        return name;
    }

    public String getBank() {
        return bank;
    }

    public String getIfsc() {
        return ifsc;
    }
}
