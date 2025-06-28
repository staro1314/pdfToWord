package com.convert.pdftoword.util;

import com.aspose.pdf.Document;
import com.aspose.pdf.SaveFormat;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.apache.commons.text.similarity.CosineSimilarity;
import org.apache.commons.text.similarity.JaccardSimilarity;
import org.apache.commons.text.similarity.LevenshteinDistance;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordUtils {

    public static void pdfToWord(MultipartFile file, HttpServletResponse response) throws IOException {
        Document document = new Document(file.getInputStream());
        document.save(response.getOutputStream(), SaveFormat.DocX);
        document.close();
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

    /**
     * 计算编辑距离
     *
     * @param a
     * @param b
     * @return
     */
    public static Double levenshteinDistance(String a, String b) {
        int apply = LevenshteinDistance.getDefaultInstance().apply(a, b);
        return 1 - apply * 1.0 / Math.max(a.length(), b.length());
    }


    /**
     * 交并集计算相似度
     *
     * @param a
     * @param b
     * @return
     */
    public static Double jaccardDistance(String a, String b) {
        JaccardSimilarity jaccardSimilarity = new JaccardSimilarity();
        return jaccardSimilarity.apply(a, b);
    }


    public static Double cosineSimilarity(String a, String b) {
        CosineSimilarity cosineSimilarity = new CosineSimilarity();

        Map<CharSequence, Integer> leftVector = of(tokenize(a));
        Map<CharSequence, Integer> rightVector = of(tokenize(b));
        return cosineSimilarity.cosineSimilarity(leftVector, rightVector);
    }

    private static CharSequence[] tokenize(CharSequence text) {
        Validate.isTrue(StringUtils.isNotBlank(text), "Invalid text", new Object[0]);
        List<String> tokens = new ArrayList<>();
        for (int i = 0; i < text.length(); i++) {
            tokens.add(String.valueOf(text.charAt(i)));
        }
        return tokens.toArray(new String[0]);
    }

    private static Map<CharSequence, Integer> of(CharSequence[] tokens) {
        Map<CharSequence, Integer> innerCounter = new HashMap<>();

        for (CharSequence token : tokens) {
            if (innerCounter.containsKey(token)) {
                int value = innerCounter.get(token);
                ++value;
                innerCounter.put(token, value);
            } else {
                innerCounter.put(token, 1);
            }
        }

        return innerCounter;
    }
}
