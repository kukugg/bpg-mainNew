package com.bpg.lr.serviceImpl.importTest;


import java.io.InputStream;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.bpg.common.utils.RRException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bpg.lr.service.importTest.ImprotTestService;

/**
 * 测试上传类实现类
 * @author hanjp
 *
 */
@Service
public class ImprotTestServiceImpl implements ImprotTestService {

	@Override
	public boolean batchImport(String fileName, MultipartFile file) throws Exception {
	    boolean notNull = false;
        if (!fileName.matches("^.+\\.(?i)(xls)$") && !fileName.matches("^.+\\.(?i)(xlsx)$")) {
            throw new RRException("上传文件格式不正确");
        }
        boolean isExcel2003 = true;
        if (fileName.matches("^.+\\.(?i)(xlsx)$")) {
            isExcel2003 = false;
        }
        InputStream is = file.getInputStream();
        
        Workbook wb = null;
        if (isExcel2003) {
            wb = new HSSFWorkbook(is);
        } else {
            wb = new XSSFWorkbook(is);
        }
        Sheet sheet = wb.getSheetAt(0);
        if(sheet!=null){
            notNull = true;
        }
        
        for (int r = 1; r <= sheet.getLastRowNum(); r++) {
            Row row = sheet.getRow(r);
            if (row == null){
                continue;
            }
 
           
            
            if( row.getCell(0).getCellType() !=1){
            }
            String name = row.getCell(0).getStringCellValue();
 
           
        }


        
        return notNull;
	}

}
