package com.example.controller;

import cn.hutool.core.util.ObjectUtil;
import com.example.common.Result;
import com.example.common.config.TokenUtils;
import com.example.common.enums.ResultCodeEnum;
import com.example.entity.Account;
import com.example.entity.Messagechat;
import com.example.exception.CustomException;

import com.example.service.MessagechatService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
*  描述：聊天记录相关接口
*/
@RestController
@RequestMapping("/messagechat")
public class MessagechatController {

    @Resource
    MessagechatService messagechatService;

    /**
     * 新增
     */
    @PostMapping("/add")
    public Result add(@RequestBody Messagechat messagechat) {
		Account currentUser = TokenUtils.getCurrentUser();
		if (ObjectUtil.isEmpty(currentUser)) {
			throw new CustomException(ResultCodeEnum.USER_NOT_LOGIN);
		}
		if ("student".equals(currentUser.getRole())) {
			messagechat.setStudentId(currentUser.getId());
		}

        messagechatService.add(messagechat);
        return Result.success();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id) {
        messagechatService.deleteById(id);
        return Result.success();
    }

    /**
     * 批量删除
     */
    @DeleteMapping("/delete/batch")
    public Result delete(@RequestBody List<Integer> ids) {
        messagechatService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * 更新
     */
    @PutMapping("/update")
    public Result update(@RequestBody Messagechat messagechat) {

        messagechatService.updateById(messagechat);
        return Result.success();
    }

    /**
     * 查询单个
     */
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        Messagechat messagechat = messagechatService.selectById(id);
        return Result.success(messagechat);
    }

    /**
     * 查询所有
     */
    @GetMapping("/selectAll")
    public Result selectAll(Messagechat messagechat) {
        List<Messagechat> list = messagechatService.selectAll(messagechat);
        return Result.success(list);
    }

    /**
     * 查询所有
     */
    @GetMapping("/selectPage")
    public Result selectPage(
            Messagechat messagechat,
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<Messagechat> pageInfo = messagechatService.selectPage(messagechat, pageNum, pageSize);
        return Result.success(pageInfo);
    }




}
