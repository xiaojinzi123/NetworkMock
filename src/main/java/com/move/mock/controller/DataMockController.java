package com.move.mock.controller;

import com.move.mock.bean.NetworkDataBean;
import com.move.mock.service.DataMockService;
import com.move.mock.util.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * org.springframework.transaction.NoTransactionException: No transaction aspect-managed TransactionStatus in scope
 */
@RequestMapping("networkMock")
@RestController()
public class DataMockController {

    @Autowired
    DataMockService dataMockService;

    @PostMapping("/data")
    public String save(NetworkDataBean dataMock) {

        String result = null;

        if (dataMock == null) {
            result = "fail";
        }

        try {
            dataMockService.insert(dataMock);
            result = "success";
        } catch (BusinessException e) {
            result = "fail:" + e.getMessage();
        }

        System.out.println("result = " + result);

        return result;

    }



}
