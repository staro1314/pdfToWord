package com.convert.pdftoword.serive.impl;

import com.convert.pdftoword.function.FunctionHelper;
import com.convert.pdftoword.serive.WatchService;
import com.convert.pdftoword.util.WordUtils;
import com.hankcs.hanlp.HanLP;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class WatchServiceImpl implements WatchService {

    @Override
    public String watchTest(String text) {
        val simplifiedChinese =FunctionHelper.doWitch(text,HanLP::convertToSimplifiedChinese,(e)->log.error("繁体转简体",e));
        FunctionHelper.doWitch("1",1,true,this::testFunction,(e)-> log.error("",e));
        return FunctionHelper.doWitch(simplifiedChinese,WordUtils::toHalfWidth,(e)->log.error("全角转半角失败",e));
    }

    public void testFunction(String a,Integer b,boolean c){
        log.info("testFunction");
    }
}
