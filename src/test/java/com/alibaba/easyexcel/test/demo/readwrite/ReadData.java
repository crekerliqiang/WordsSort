package com.alibaba.easyexcel.test.demo.readwrite;

import lombok.Data;


@Data
public class ReadData {
    public String en;
    public String cn;
    public String  toString(){
        return "en:" + en + " cn: " +  cn;
    }
}
