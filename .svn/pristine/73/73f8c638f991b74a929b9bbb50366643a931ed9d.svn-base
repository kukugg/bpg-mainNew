package com.bpg.lr.controller.excelTest;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.bpg.common.model.ExcelData;
import org.bpg.common.utils.ExcelUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/excel")
public class ExcelController {
	
	 @RequestMapping(value = "/export", method = RequestMethod.GET)
	 public void excel(HttpServletResponse response) throws Exception {
	        ExcelData data = new ExcelData();
	        data.setName("hello");
	        List<String> titles = new ArrayList();
	        titles.add("a1");
	        titles.add("a2");
	        titles.add("a3");
	        data.setTitles(titles);

	        List<List<Object>> rows = new ArrayList();
	        List<Object> row = new ArrayList();
	        row.add("11111111111");
	        row.add("22222222222");
	        row.add("3333333333");
	        rows.add(row);

	        data.setRows(rows);


	        //生成本地
	        /*File f = new File("c:/test.xlsx");
	        FileOutputStream out = new FileOutputStream(f);
	        ExportExcelUtils.exportExcel(data, out);
	        out.close();*/
	        SimpleDateFormat fdate=new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
	        String fileName=fdate.format(new Date())+".xls";
	        ExcelUtils.exportExcel(response,fileName,data);
	    }


}
