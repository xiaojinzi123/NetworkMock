package com.move.mock.controller;

import com.move.mock.bean.DataMock;
import com.move.mock.bean.DataMockRequest;
import com.move.mock.service.DataMockService;
import com.move.mock.util.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("dataMock")
@RestController()
public class DataMockController {

    @Autowired
    DataMockService dataMockService;

    @RequestMapping("/save")
    public String save(DataMockRequest dataMock) {

        if (dataMock == null) {
            return "fail";
        }

        try {
            dataMockService.insert(dataMock);
        } catch (BusinessException e) {
            return "fail:" + e.getMessage();
        }

        return "success";

    }

}
