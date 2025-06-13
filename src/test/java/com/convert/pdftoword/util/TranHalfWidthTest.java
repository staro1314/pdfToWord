package com.convert.pdftoword.util;

import com.hankcs.hanlp.HanLP;
import lombok.val;
import org.junit.jupiter.api.Test;

public class TranHalfWidthTest {

    @Test
    public void toHalfWith(){
        String content ="你是谁你是誰";
        System.out.println(content);
        val simplifiedChinese = HanLP.convertToSimplifiedChinese(content);
        System.out.println(simplifiedChinese);
        String halfWidth = toHalfWidth(simplifiedChinese);
        System.out.println(halfWidth);
    }

    public static String toHalfWidth(String input) {
        char[] chars = input.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '\u3000') {         // 全角空格
                chars[i] = '\u0020';
            } else if (chars[i] >= '\uFF01'     // 全角标点/字母
                    && chars[i] <= '\uFF5E') {
                chars[i] -= 65248;              // 差值转换
            } else if (chars[i] >= '\uFF10'     // 全角数字
                    && chars[i] <= '\uFF19') {
                chars[i] -= 65248;
            }
        }
        return new String(chars);
    }
}
