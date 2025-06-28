package com.convert.pdftoword.util;

import com.hankcs.hanlp.HanLP;
import lombok.val;
import org.junit.jupiter.api.Test;

/**
 * 全角转换为半角
 */
public class TranHalfWidthTest {

    @Test
    public void toHalfWith() {
        String content = "你是谁你是誰";
        System.out.println(content);
        val simplifiedChinese = HanLP.convertToSimplifiedChinese(content);
        System.out.println(simplifiedChinese);
        String halfWidth = WordUtils.toHalfWidth(simplifiedChinese);
        System.out.println(halfWidth);
    }


}
