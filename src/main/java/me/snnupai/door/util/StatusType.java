package me.snnupai.door.util;


public enum StatusType {
    delete(1),create(0), update(2);

    private int val;

    StatusType(int val){
        this.val = val;
    }

    int getVal(){
        return val;
    }


}
