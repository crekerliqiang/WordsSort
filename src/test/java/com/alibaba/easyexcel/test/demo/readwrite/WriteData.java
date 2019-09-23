package com.alibaba.easyexcel.test.demo.readwrite;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import lombok.Data;

import javax.validation.constraints.Size;


@Data
//列宽
@ColumnWidth(20)
//行高
@ContentRowHeight(20)
//标题大小
@HeadRowHeight(20)
public class WriteData {
        public WriteData(){

        }
        public WriteData(String en0, String  cn0, String en1, String cn1){
                this.en0 = en0;
                this.cn0 = cn0;
                this.en1 = en1;
                this.cn1 = cn1;
        }
        @ExcelProperty(value = "单词", index = 0)
        public String en0;
        @ExcelProperty(value = "释义", index = 1)
        public String cn0;
        @ExcelProperty(value = "单词", index = 3)
        public String en1;
        @ExcelProperty(value = "释义", index = 4)
        public String cn1;
}

