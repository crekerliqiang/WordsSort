package com.alibaba.easyexcel.test.demo.readwrite;

import java.io.File;


public class Path {

    private static final String  BASE =  new File(System.getProperty("user.dir")).getAbsolutePath() +  File.separator;
    public static final String FILE_PATH_SRC = BASE + "雅思核心词汇精讲精练.xlsx";
    public static final String FILE_PATH_SRC_BACK = BASE + "雅思核心词汇精讲精练备份.xlsx";
    public static final String FILE_PATH_DEST = BASE + "雅思词汇";
}
