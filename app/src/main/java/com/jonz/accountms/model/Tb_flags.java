package com.jonz.accountms.model;

/**
 * Created by jon-z on 2016/5/18.
 */
public class Tb_flags {
    private int _id;
    private String flag;

    public Tb_flags(){
        super();
    }

    public Tb_flags(int id, String flag){
        super();
        this._id = id;
        this.flag = flag;
    }

    public int getid(){
        return _id;
    }
    public void setid(int id){
        this._id = id;
    }

    public String getFlag(){
        return flag;
    }
    public void setFlag(String flag){
        this.flag = flag;
    }
}
