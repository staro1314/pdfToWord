package com.convert.pdftoword;

import cn.afterturn.easypoi.word.entity.MyXWPFDocument;
import com.aspose.pdf.Document;
import com.aspose.pdf.SaveFormat;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;

import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.junit.jupiter.api.Test;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.test.context.SpringBootTest;
import technology.tabula.CommandLineApp;

import java.io.*;
import java.util.List;

//@SpringBootTest
class PdfToWordApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    public void pdfToWordTest() throws IOException {
        String pdfPath = "D:\\Personal\\Desktop\\test\\test.pdf";
        String wordPath = "D:\\Personal\\Desktop\\test\\test.docx";
        FileOutputStream outputStream = new FileOutputStream(wordPath);

        Document document = new Document(pdfPath);
        new Document(new FileInputStream(""));
        document.save(outputStream, SaveFormat.DocX);
        outputStream.close();
    }

    @Test
    public void pdfToWordByPTest() throws Exception {
        InputStream inputStream = new FileInputStream("D:\\Personal\\Desktop\\test\\test.docx");
        MyXWPFDocument document=new MyXWPFDocument(OPCPackage.open(inputStream));
        List<XWPFTable> tables=document.getTables();
        MyXWPFDocument targetDocument=new MyXWPFDocument();

        if (tables!=null&& !tables.isEmpty()){
            XWPFTable targetXwpfTable = targetDocument.createTable();
            XWPFTable xwpfTable = tables.get(0);
            targetXwpfTable.removeRow(0);
            for (XWPFTableRow row : xwpfTable.getRows()) {
                targetXwpfTable.addRow(row);
            }
        }

        OutputStream outputStream = new FileOutputStream("D:\\Personal\\Desktop\\test\\table.docx");
        targetDocument.write(outputStream);
    }


    @Test
    public void pdfToWordByTabulaTest() throws Exception {
        // -f 导出格式，默认CSV
        //-p 指导出那一页
        //path D:\\1xx.pdf
        //-l 轻质使用点阵模式提取PDF
        String[] argsa = new String[]{"-f=TSV","-p=all","D:\\Personal\\Desktop\\test\\test.pdf","-l"};
        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = parser.parse(CommandLineApp.buildOptions(),argsa);
        StringBuilder sb = new StringBuilder();
        FileWriter fw = new FileWriter("D:\\Personal\\Desktop\\test\\OUT.tsv");
        new CommandLineApp(fw,cmd).extractTables(cmd);
//        System.out.println(sb.toString());
    }
}
