package com.convert.pdftoword.controller;


import com.aspose.pdf.operators.EX;
import com.convert.pdftoword.config.enums.ResultEnum;
import com.convert.pdftoword.config.exception.GlobalException;
import com.convert.pdftoword.util.WordUtils;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@RestController
@RequestMapping("/pdf")
@Slf4j
public class PdfToWordController {



    @PostMapping("/pdf/word")
    public void pdfToWord(MultipartFile file, HttpServletResponse response){
        if (file == null || file.isEmpty()){
            throw new GlobalException(ResultEnum.SYS_ERROR);
        }
        String fileName = file.getOriginalFilename();
        try {
            fileName = URLEncoder.encode(fileName,"UTF-8");
            response.setContentType("application/msword;charset=utf-8");
            response.addHeader("Content-Disposition", STR."attachment;filename=\"\{new String(fileName.getBytes("gbk"), "iso8859-1")}\"");
            WordUtils.pdfToWord(file,response);
        } catch (Exception e) {
           log.error("转换失败！");
        }
    }
}
