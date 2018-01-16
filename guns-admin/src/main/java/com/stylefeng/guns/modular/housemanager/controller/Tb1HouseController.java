package com.stylefeng.guns.modular.housemanager.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.util.ToolUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import com.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import com.stylefeng.guns.common.persistence.model.Tb1House;
import com.stylefeng.guns.modular.housemanager.service.ITb1HouseService;

/**
 * 房屋管理控制器
 *
 * @author fengshuonan
 * @Date 2018-01-16 15:57:19
 */
@Controller
@RequestMapping("/tb1House")
public class Tb1HouseController extends BaseController {

    private String PREFIX = "/housemanager/tb1House/";

    @Autowired
    private ITb1HouseService tb1HouseService;

    /**
     * 跳转到房屋管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "tb1House.html";
    }

    /**
     * 跳转到添加房屋管理
     */
    @RequestMapping("/tb1House_add")
    public String tb1HouseAdd() {
        return PREFIX + "tb1House_add.html";
    }

    /**
     * 跳转到修改房屋管理
     */
    @RequestMapping("/tb1House_update/{tb1HouseId}")
    public String tb1HouseUpdate(@PathVariable Integer tb1HouseId, Model model) {
        Tb1House tb1House = tb1HouseService.selectById(tb1HouseId);
        model.addAttribute("item",tb1House);
        LogObjectHolder.me().set(tb1House);
        return PREFIX + "tb1House_edit.html";
    }

    /**
     * 获取房屋管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        //判断condition是否有值
        if(ToolUtil.isEmpty(condition)){
            return tb1HouseService.selectList(null);
        }else{
            //如果有值，则认为是按业务名称进行模糊查询
            EntityWrapper<Tb1House> entityWrapper = new EntityWrapper<>();
            Wrapper<Tb1House> wrapper = entityWrapper.like("house_user",condition);
            return tb1HouseService.selectList(wrapper);
        }
    }

    /**
     * 新增房屋管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Tb1House tb1House) {
        tb1HouseService.insert(tb1House);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除房屋管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer tb1HouseId) {
        tb1HouseService.deleteById(tb1HouseId);
        return SUCCESS_TIP;
    }

    /**
     * 修改房屋管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Tb1House tb1House) {
        tb1HouseService.updateById(tb1House);
        return super.SUCCESS_TIP;
    }

    /**
     * 房屋管理详情
     */
    @RequestMapping(value = "/detail/{tb1HouseId}")
    @ResponseBody
    public Object detail(@PathVariable("tb1HouseId") Integer tb1HouseId) {
        return tb1HouseService.selectById(tb1HouseId);
    }
}
