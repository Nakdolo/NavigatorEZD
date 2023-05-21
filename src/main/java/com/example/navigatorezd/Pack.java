package com.example.navigatorezd;

import java.util.List;

public class Pack implements Comparable<Pack>{
    String name;
    double val;
    List<String> path ;
    Pack(double val, String name,List<String> p ){
        this.val=val;
        this.name=name;
        path=p;
    }
    @Override
    public int compareTo(Pack p){
        if(p.val<this.val){
            return  1;
        }else if(p.val>this.val){
            return -1;
        }else{
            return 0;
        }
    }
}