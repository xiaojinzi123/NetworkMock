package com.move.mock.controller;

import com.move.mock.bean.BaseUrlBean;
import com.move.mock.service.baseurl.BaseUrlService;
import com.move.mock.util.PageResultBean;
import com.move.mock.util.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("networkMock")
@RestController()
public class BaseUrlController {

    @Autowired
    BaseUrlService baseUrlService;

    /**
     * 保存
     *
     * @param baseUrlBean
     */
    @PostMapping("baseUrl")
    @ResponseBody
    public ResultBean save(BaseUrlBean baseUrlBean) {

        try {
            baseUrlService.insert(baseUrlBean);
            return ResultBean.success();
        } catch (Exception e) {
            return ResultBean.error(e.getMessage());
        }

    }

    /**
     * 更新一个实体对象
     *
     * @param baseUrlBean
     * @return
     */
    @PutMapping("baseUrl")
    @ResponseBody
    public ResultBean update(BaseUrlBean baseUrlBean) {

        try {
            baseUrlService.update(baseUrlBean);
            return ResultBean.success();
        } catch (Exception e) {
            return ResultBean.error(e.getMessage());
        }

    }

    /**
     * 删除一个对象
     *
     * @param id
     * @return
     */
    @DeleteMapping("baseUrl")
    @ResponseBody
    public ResultBean delete(Integer id) {

        if (id == null) {
            return ResultBean.error("parameter is null");
        }

        try {
            baseUrlService.deleteById(id);
            return ResultBean.success();
        } catch (Exception e) {
            return ResultBean.error(e.getMessage());
        }

    }

    @GetMapping("baseUrl/list")
    @ResponseBody
    public ResultBean<PageResultBean> getList(int pageIndex, int pageSize) {

        try {
            PageResultBean<BaseUrlBean> pageResultBean = new PageResultBean<>(baseUrlService.count(), pageIndex, baseUrlService.queryPage(pageIndex, pageSize));
            return ResultBean.success(pageResultBean);
        } catch (Exception e) {
            return ResultBean.error(e.getMessage());
        }

    }


}
