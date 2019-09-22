package com.alibaba.easyexcel.test.demo.readwrite;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liqiang on 2019/9/22.
 */
public class ReadDataListener extends AnalysisEventListener<ReadData> {
    private List<ReadData> list = new ArrayList<ReadData>();
    @Override
    public void invoke(ReadData data, AnalysisContext context) {
        list.add(data);
    }
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
    }
}