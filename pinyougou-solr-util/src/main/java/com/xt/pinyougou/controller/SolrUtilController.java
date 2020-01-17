package com.xt.pinyougou.controller;

import com.xt.pinyougou.solrutil.SolrUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SolrUtilController {

    @Autowired
    private SolrUtil solrUtil;

    @GetMapping("/importData")
    public String importData() {
        solrUtil.importItemData();
        return "success";
    }
}
