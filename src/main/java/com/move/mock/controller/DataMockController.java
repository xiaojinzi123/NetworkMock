package com.move.mock.controller;

import com.move.mock.bean.NetworkDataAccess;
import com.move.mock.bean.NetworkDataBean;
import com.move.mock.service.mock.DataMockService;
import com.move.mock.util.BusinessException;
import com.move.mock.util.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 这个获取mock数据的接口是不需要用统一的输出对象包一下的!
     *
     * @param dataAccess
     * @return
     * @throws BusinessException
     */
    @GetMapping("/data")
    @ResponseBody
    public String get(NetworkDataAccess dataAccess) throws BusinessException {

        if (dataAccess == null) {
            throw new BusinessException("parameter is null");
        }

        dataAccess.setEnv(null);

        try {
            return dataMockService.get(dataAccess);
        } catch (BusinessException e) {
            throw new BusinessException(e.getMessage());
        }

    }

}
