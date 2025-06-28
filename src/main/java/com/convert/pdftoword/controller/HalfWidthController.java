package com.convert.pdftoword.controller;

import com.convert.pdftoword.model.GlobalResult;
import com.convert.pdftoword.util.WordUtils;
import com.hankcs.hanlp.HanLP;
import lombok.val;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("half")
public class HalfWidthController {

    /**
     * 全角转半角，繁体转简体
     * @param text
     * @return
     */
    @GetMapping("/trans")
    public GlobalResult<String> halfWidth(String text) {
        val simplifiedChinese = HanLP.convertToSimplifiedChinese(text);
        String halfWidth = WordUtils.toHalfWidth(simplifiedChinese);
        return new GlobalResult<String>().setData(halfWidth);
    }

}
