package com.convert.pdftoword.util;

import com.aspose.pdf.Document;
import com.aspose.pdf.SaveFormat;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public class WordUtils {

    public static void pdfToWord(MultipartFile file, HttpServletResponse response) throws IOException {
        Document document =new Document(file.getInputStream());
        document.save(response.getOutputStream(), SaveFormat.DocX);
        document.close();
    }
}
