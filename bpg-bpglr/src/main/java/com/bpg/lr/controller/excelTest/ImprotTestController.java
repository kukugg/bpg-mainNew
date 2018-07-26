package com.bpg.lr.controller.excelTest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bpg.lr.service.importTest.ImprotTestService;

@RestController
@RequestMapping("/improtTest")
public class ImprotTestController {
	
	@Autowired
	private ImprotTestService improtTestService;
	
	@RequestMapping(value="/import",method = RequestMethod.POST)
	public boolean testImport(@RequestParam("file") MultipartFile file) {
		
		
		boolean a = false;
        String fileName = file.getOriginalFilename();
        try {
            a = improtTestService.batchImport(fileName, file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  a;

	}

}
