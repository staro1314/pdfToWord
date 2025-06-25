package com.convert.pdftoword.util;

import lombok.ConfigurationKeys;
import lombok.val;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.apache.commons.text.similarity.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;

/**
 * 文本相似度计算
 */
public class SimilarityTest {

    @Test
    public void testSimilarity() {
        System.out.println(cosineSimilarity("张三","张三三"));
    }

    /**
     * 计算编辑距离
     * @param a
     * @param b
     * @return
     */
    private static Double levenshteinDistance(String a, String b) {
        int apply = LevenshteinDistance.getDefaultInstance().apply(a, b);
        return 1 - apply * 1.0 / Math.max(a.length(), b.length());
    }

    /**
     * 交并集计算相似度
     * @param a
     * @param b
     * @return
     */
    private static Double jaccardDistance(String a, String b) {
        JaccardSimilarity jaccardSimilarity = new JaccardSimilarity();
        return jaccardSimilarity.apply(a, b);
    }

    private static Double cosineSimilarity(String a, String b) {
        CosineSimilarity cosineSimilarity = new CosineSimilarity();

        Map<CharSequence, Integer> leftVector = of(tokenize(a));
        Map<CharSequence, Integer> rightVector = of(tokenize(b));
        return cosineSimilarity.cosineSimilarity(leftVector,rightVector);
    }

    public static CharSequence[] tokenize(CharSequence text) {
        Validate.isTrue(StringUtils.isNotBlank(text), "Invalid text", new Object[0]);
        List<String> tokens = new ArrayList();
        for (int i = 0; i < text.length(); i++) {
            tokens.add(String.valueOf(text.charAt(i)));
        }
        return (CharSequence[])tokens.toArray(new String[0]);
    }

    private static Map<CharSequence, Integer> of(CharSequence[] tokens) {
        Map<CharSequence, Integer> innerCounter = new HashMap();
        CharSequence[] var2 = tokens;
        int var3 = tokens.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            CharSequence token = var2[var4];
            if (innerCounter.containsKey(token)) {
                int value = (Integer)innerCounter.get(token);
                ++value;
                innerCounter.put(token, value);
            } else {
                innerCounter.put(token, 1);
            }
        }

        return innerCounter;
    }
}
