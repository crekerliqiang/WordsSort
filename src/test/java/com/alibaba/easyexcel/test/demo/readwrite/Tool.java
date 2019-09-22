package com.alibaba.easyexcel.test.demo.readwrite;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Tool {


    private static final Logger LOGGER = LoggerFactory.getLogger(Tool.class);

    private static void  printList(String  tag,List<ReadData> list){
        for (ReadData data : list){
            LOGGER.info(tag + "list: " +  data.toString());
        }
    }

    private static void  printList(Integer [] list){
        StringBuilder s = new StringBuilder();
        for (Integer i : list){
            s.append(" ").append(i);
        }
        LOGGER.info("list: " +  s.toString());
    }

    /**
     * number 转为 abc
     * @param i i
     * @return  abc
     * 1:A;2:B...
     */
    private static String numberToABC(int i){
        return  String.valueOf((char) (i + (int)'A'));
    }
    /**
     * 获取size的随机 index
     * @param size size
     * @return int []
     * eg:size=4;int [] {2,3,1,0}...
     */
    private static Integer [] getRandomIndex(int size){
        Integer solutionArr[] = new Integer[size];
        List list=new ArrayList<Integer>();
        for (int i = 0; i < size; i++){
            list.add(i);
        }
        Collections.shuffle(list);
        list.toArray(solutionArr);
        return solutionArr;
    }

    /**
     * 对 list 排序 「只排cn  不排en」
     * @param readDataList readDataList
     * @return list
     */
    private static List<ReadData> sortList(List<ReadData> readDataList){

        List<ReadData> listList = new ArrayList<ReadData>();
        //保存en
        List<String> enList = new ArrayList<String>();
        for (int i = 0; i < readDataList.size(); i++) {
            enList.add(readDataList.get(i).en);
        }
        //对整个list 排序
        Integer [] indexs = getRandomIndex(readDataList.size());
        for (int i = 0; i < readDataList.size(); i++) {
            listList.add(readDataList.get(indexs[i]));
        }
        //还原en
        List<ReadData> listListOnlyCn = new ArrayList<ReadData>();
        for (int i = 0; i < readDataList.size(); i++) {
            ReadData readData =  new  ReadData();
            //en为原来 的 加入编号
            readData.en = (i + 1) + "." + enList.get(i);
            //cn为排序后的
            readData.cn = numberToABC(i) + ". " + listList.get(i).cn;

            listListOnlyCn.add(readData);
        }
        //return
        return listListOnlyCn;
    }
    /**
     * 1.把一个 list分成多个list
     * 2.对分成的list重新排序
     * 3.把排好序的list重新组成新的list
     */
    public static List<ReadData> splitList(List<ReadData> readDataList,int groupNo){

        List<ReadData> listList = new ArrayList<ReadData>();
        int  i = 0;
        while (i < readDataList.size()){
            //步骤1
            List<ReadData> list = new  ArrayList<ReadData>();
            for(int j = 0;j < groupNo;j++){
                if(i >= readDataList.size())break;
                list.add(readDataList.get(i));
                i++;
            }
            //步骤2
            List<ReadData> newList = sortList(list);
            //步骤3
            listList.addAll(newList);
        }
        return listList;
    }
}
