package com.alibaba.easyexcel.test.demo.readwrite;

import java.io.File;

/**
 * Created by liqiang on 2019/9/22.
 */
public class Path {

    private static final String  BASE =  new File(System.getProperty("user.dir")).getParent() +  File.separator;
    public static final String FILE_PATH_SRC = BASE + "雅思核心词汇精讲精练.xlsx";
    public static final String FILE_PATH_DEST = BASE + "雅思核心词汇精讲精练2.xlsx";
}
