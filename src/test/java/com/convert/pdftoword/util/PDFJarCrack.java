package com.convert.pdftoword.util;

import javassist.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.JarOutputStream;

/**
 * 破解 aspose pdf转换工具包（去除水印，去除默认状四页限制）
 * pdf转word破解工具
 */
public class PDFJarCrack {

    public static void main(String[] args) throws NotFoundException, CannotCompileException, IOException {
        String jarPath = "D:\\Personal\\Desktop\\test\\aspose-pdf-21.6.jar";
        crack(jarPath);
    }

    public static void crack(String jarName) throws NotFoundException, CannotCompileException, IOException {
        ClassPool.getDefault().insertClassPath(jarName);
        CtClass ctClass = ClassPool.getDefault().getCtClass("com.aspose.pdf.ADocument");
        CtMethod[] declaredMethods = ctClass.getDeclaredMethods();
        int num = 0;
        for (int i = 0; i < declaredMethods.length; i++) {
            if (num==2){
                break;
            }
            CtMethod method = declaredMethods[i];
            CtClass[] ps = method.getParameterTypes();
            if (ps.length ==2
                    && method.getName().equals("lI")
                    && ps[0].getName().equals("com.aspose.pdf.ADocument")
                    && ps[1].getName().equals("int")) {
                // 最多只能转换4页 处理
                System.out.println(method.getReturnType());
                System.out.println(ps[1].getName());
                method.setBody("{return false;}");
                num =1;
            }
            if (ps.length ==0 && method.getName().equals("lt")) {
                // 水印处理
                method.setBody("{return true;}");
                num =2;
            }
        }
        File file =new File(jarName);
        ctClass.writeFile(file.getParent());
        disposeJar(jarName, file.getParent() +"/com/aspose/pdf/ADocument.class");

    }
    private static void disposeJar(String jarName, String replaceFile) throws IOException {
        List<String> deletes =new ArrayList<>();
        deletes.add("META-INF/37E3C32D.SF");
        deletes.add("META-INF/37E3C32D.RSA");
        File oriFile =new File(jarName);
        if (!oriFile.exists()) {
            System.out.println("######Not Find File:" + jarName);
            return;
        }
        //将文件名命名成备份文件
        String bakJarName = jarName.substring(0, jarName.length() -3) +"cracked.jar";
        //创建文件(根据备份文件并删除部分)
        JarFile jarFile =new JarFile(jarName);
        JarOutputStream jos =new JarOutputStream(new FileOutputStream(bakJarName));
        Enumeration entries = jarFile.entries();
        while (entries.hasMoreElements()) {
            JarEntry entry = (JarEntry) entries.nextElement();
            if (!deletes.contains(entry.getName())) {
                if (entry.getName().equals("com/aspose/pdf/ADocument.class")) {
                    System.out.println("Replace:-------" + entry.getName());
                    JarEntry jarEntry = new JarEntry(entry.getName());
                    jos.putNextEntry(jarEntry);
                    FileInputStream fin =new FileInputStream(replaceFile);
                    byte[] bytes = readStream(fin);
                    jos.write(bytes,0, bytes.length);


                }else{
                    jos.putNextEntry(entry);
                    byte[] bytes = readStream(jarFile.getInputStream(entry));
                    jos.write(bytes,0, bytes.length);
                }
            }else {
                System.out.println("Delete:-------" + entry.getName());
            }
        }
        jos.flush();
        jos.close();
        jarFile.close();

    }

    private static byte[]  readStream(InputStream in) throws IOException {
        ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = -1;
        while ((len = in.read(buffer)) != -1) {
            outSteam.write(buffer, 0, len);
        }
        outSteam.close();
        in.close();
        return outSteam.toByteArray();
    }
}
