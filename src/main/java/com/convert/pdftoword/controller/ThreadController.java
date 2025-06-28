package com.convert.pdftoword.controller;

import com.convert.pdftoword.model.GlobalResult;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.StructuredTaskScope;

@RestController
@RequestMapping("test")
public class ThreadController {

    @PostMapping("/virtual")
    public GlobalResult<String> tranHalfWidth(@RequestParam String text) throws InterruptedException {
        if (StringUtils.isEmpty(text)){
            return GlobalResult.of(5001,"缺少必要参数");
        }
//        try (var scope = new StructuredTaskScope.ShutdownOnFailure()) {
//            Future<String> user = scope.fork(() -> fetchUser(userId));
//            Future<String> order = scope.fork(() -> fetchOrder(orderId));
//
//            scope.join();          // 等待所有任务完成
//            scope.throwIfFailed(); // 任一失败则抛出异常
//
//
//        } catch (ExecutionException e) {
//            throw new RuntimeException(e);
//        }
        return null;
    }


}
