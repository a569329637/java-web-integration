package com.souche.service.impl;

import com.souche.service.EMailDataService;
import com.souche.utils.ExcelUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author guishangquan
 * @Description
 * @Package com.souche.service.impl
 * @date 17/4/27
 **/
public class EMailDataServiceImpl implements EMailDataService {

    @Override
    public String generateHtml(List<String> columnNameList, List<HashMap<String, Object>> data) {
        StringBuffer table = new StringBuffer("<table>");
        StringBuffer sbTh = new StringBuffer("<thead><tr>");
        StringBuffer tbody = new StringBuffer("<tbody>");

        String th = "<th>";
        String tr = "<tr>";
        String td = "<td>";

        String tableEnd = "</table>";
        String theadEnd = "</tr></thead>";
        String thEnd = "</th>";
        String trEnd = "</tr>";
        String tdEnd = "</td>";
        String tbodyEnd = "</tbody>";

        for (int i = 0; i < columnNameList.size(); i++) {
            sbTh.append(th);
            sbTh.append(columnNameList.get(i));
            sbTh.append(thEnd);
        }
        sbTh.append(theadEnd);

        for (int i = 0; i < data.size(); i++) {
            tbody.append(tr);
            HashMap<String, Object> hashMap = data.get(i);
            for (int j = 0; j < columnNameList.size(); j++) {
                tbody.append(td);
                tbody.append(hashMap.get(columnNameList.get(j)));
                tbody.append(tdEnd);
            }
            tbody.append(trEnd);
        }
        tbody.append(tbodyEnd);
        table.append(sbTh).append(tbody).append(tableEnd);

        return table.toString();
    }

    @Override
    public File generateExcel(String title, List<List<String>> data) {
        String fileName = "/tmp/" + title + ".xls";
        String sheetName = "sheet1";

        List<String> titleRow = new ArrayList<>();
        int num = data.get(0).size();
        for (int i = 0; i < num; ++ i) {
            titleRow.add(data.get(0).get(i));
        }
        data.remove(0);

        return ExcelUtil.createExcel(fileName, sheetName, titleRow, data);
    }
}
