package com.alibaba.easyexcel.test.demo.readwrite;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;


@Data
public class WriteDataNew {
        @ExcelProperty(value = "单词", index = 0)
        public String en0;
        @ExcelProperty(value = "释义", index = 1)
        public String cn0;
        @ExcelProperty(value = "单词", index = 3)
        public String en1;
        @ExcelProperty(value = "释义", index = 4)
        public String cn1;
}

