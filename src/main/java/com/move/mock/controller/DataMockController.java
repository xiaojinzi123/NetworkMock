package com.move.mock.controller;

import com.move.mock.bean.NetworkDataAccess;
import com.move.mock.bean.NetworkDataBean;
import com.move.mock.service.DataMockService;
import com.move.mock.util.BusinessException;
import com.move.mock.util.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
    public ResultBean save(NetworkDataBean dataMock) {

        if (dataMock == null) {
            return ResultBean.error("parameter is null");
        }

        try {
            dataMockService.insert(dataMock);
            return ResultBean.success();
        } catch (BusinessException e) {
            return ResultBean.error(e.getMessage());
        }

    }

    @GetMapping("/data")
    public ResultBean<String> get(NetworkDataAccess dataAccess) throws BusinessException {

        if (dataAccess == null) {
            return ResultBean.error("parameter is null");
        }

        try {
            return ResultBean.success(dataMockService.get(dataAccess));
        } catch (BusinessException e) {
            return ResultBean.error(e.getMessage());
        }

    }

}
