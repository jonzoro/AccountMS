package com.jonz.accountms.model;

/**
 * Created by jon-z on 2016/7/8.
 */
public class Tb_txt {
    private String txt;
    public Tb_txt(){
        super();
    }
    public Tb_txt(String txt){
        super();
        this.txt = txt;
    }

    public String getTxt(){
        return txt;
    }
    public void setTxt(String txt){
        this.txt = txt;
    }
}
