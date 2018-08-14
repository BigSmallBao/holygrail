package com.avalon.holygrail;

import com.avalon.holygrail.excel.exception.ExcelException;
import com.avalon.holygrail.utils.Export;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 白超 on 2018/8/14.
 */
public class ExportTest {

    @Test
    void test01() throws ExcelException, IOException {
        List<Map<String, Object>> records = new ArrayList<>();

        Map<String, Object> r = new HashMap<>();
        r.put("id", 1);
        r.put("name", "张三");
        r.put("age", 18);
        records.add(r);

        r = new HashMap<>();
        r.put("id", 2);
        r.put("name", "李四");
        r.put("age", 24);
        records.add(r);

        r = new HashMap<>();
        r.put("id", 3);
        r.put("name", "王五");
        r.put("age", 60);
        records.add(r);


        Export.buildSXSSFExportExcelWorkBook()
                .createSheet()
                .setColumnFields("id", "name", "age")
                .importData(records, (value, record, cellHandler, field, rowCursor, index) -> {

                    return value;
                })
                .export("E://test.xlsx");
    }
}
