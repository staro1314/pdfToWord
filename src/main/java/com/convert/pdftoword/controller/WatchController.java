package com.convert.pdftoword.controller;

import com.convert.pdftoword.model.GlobalResult;
import com.convert.pdftoword.serive.WatchService;
import com.convert.pdftoword.util.WordUtils;
import com.hankcs.hanlp.HanLP;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("watch")
public class WatchController {

    @Autowired
    private WatchService watchService;

    @GetMapping("/test")
    public GlobalResult<String> watchTest(String text) {
        return new GlobalResult<String>().setData(watchService.watchTest(text));
    }
}
