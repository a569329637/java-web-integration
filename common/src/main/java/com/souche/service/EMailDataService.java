package com.souche.service;

import java.io.File;
import java.util.HashMap;
import java.util.List;

/**
 * @author guishangquan
 * @Description
 * @Package com.souche.service
 * @date 17/4/27
 **/
public interface EMailDataService {

    /**
     * 生成 html 表格
     *
     * @param columnNameList 表头数据
     * @param data 内容数据
     * @return
     */
    String generateHtml(List<String> columnNameList, List<HashMap<String, Object>> data);

    /**
     * 生成 excel 文件
     *
     * @param title
     * @param data
     * @return
     */
    File generateExcel(String title, List<List<String>> data);

}
