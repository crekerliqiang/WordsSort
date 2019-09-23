package com.alibaba.easyexcel.test.demo.readwrite;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.analysis.ExcelAnalyserImpl;
import com.alibaba.excel.context.AnalysisContextImpl;
import com.alibaba.excel.exception.ExcelAnalysisException;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.alibaba.excel.read.metadata.holder.ReadSheetHolder;
import com.alibaba.excel.write.metadata.WriteSheet;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import static com.alibaba.easyexcel.test.demo.readwrite.Path.FILE_PATH_SRC;
import static com.alibaba.easyexcel.test.demo.readwrite.Path.FILE_PATH_DEST;

public class ReadWriteTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReadWriteTest.class);
    private List<String> sheetNameList;

    //一共多少个 sheet「可以 填一个很大的数」
    private static final int SHEET_NO = 2000;
    //多少个单词分一组
    private static final int GROUP_NUM =  6;

    private static final String EMPTY = " ";

    @Test
    public void TestSortWords(){

        ExcelWriter excelWriter = EasyExcel.write(FILE_PATH_DEST, WriteData.class).build();
        sheetNameList = new ArrayList<String>();
        for(int i = 0;i < SHEET_NO;i++){
            try{
            List<WriteData> writeData = sortWords(FILE_PATH_SRC,i,GROUP_NUM);
            WriteSheet writeSheet = EasyExcel.writerSheet(i,sheetNameList.get(i)).build();
            excelWriter.write(writeData, writeSheet);

            }catch (ExcelAnalysisException e){
                break;
            }
        }
        excelWriter.finish();
        sheetNameList =  null;
    }

    private synchronized List<WriteData> sortWords(String fileNameSrc, int sheetNo, int groupNo){

        //单独读取一个sheetName
        ExcelReader excelReader = EasyExcel.read(fileNameSrc, ReadData.class, new ReadDataListener()).build();
        ReadSheet readSheet = EasyExcel.readSheet(sheetNo).build();
        excelReader.read(readSheet);

        ExcelAnalyserImpl excelAnalyser = excelReader.getExcelAnalyser();
        AnalysisContextImpl analysisContext = excelAnalyser.getAnalysisContext();
        ReadSheetHolder readSheetHolder = analysisContext.getReadSheetHolder();
        String sheetName  = readSheetHolder.getSheetName();
        sheetNameList.add(sheetName);
        // 这里千万别忘记关闭，读的时候会创建临时文件，到时磁盘会崩的
        excelReader.finish();

        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 同步读取会自动finish
        List<Object> list = EasyExcel.read(fileNameSrc).head(ReadData.class).sheet(sheetNo).doReadSync();
        List<ReadData> readData = new ArrayList<ReadData>();
        for (Object obj : list){
            ReadData data = (ReadData)obj;
            readData.add(data);
        }
        List<ReadData> readDataSort = Tool.splitList(readData,groupNo);

        List<WriteData> writeData = new ArrayList<WriteData>();
        //组合 readData readDataSort
        for(int i =  0;i < readData.size();i++){
            //加 一行 空格
            if(i > 0 && i % GROUP_NUM == 0){
                writeData.add(new WriteData(EMPTY,EMPTY,EMPTY,EMPTY));
            }
            writeData.add(new WriteData(
                    readData.get(i).en,
                    readData.get(i).cn,
                    readDataSort.get(i).en,
                    readDataSort.get(i).cn));
        }
        return writeData;
    }
}
