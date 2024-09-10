package com.example.service;

import com.example.common.config.TokenUtils;
import com.example.entity.Account;
import com.example.entity.Messagechat;
import com.example.mapper.MessagechatMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MessagechatService {

    @Resource
    private MessagechatMapper messagechatMapper;

    /**
     * 新增
     */
    public void add(Messagechat messagechat) {
        messagechatMapper.insert(messagechat);
    }

    /**
     * 删除
     */
    public void deleteById(Integer id) {
        messagechatMapper.deleteById(id);
    }

    /**
     * 批量删除
     */
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            messagechatMapper.deleteById(id);
        }
    }

    /**
     * 修改
     */
    public void updateById(Messagechat messagechat) {
        messagechatMapper.updateById(messagechat);
    }

    /**
     * 根据ID查询
     */
    public Messagechat selectById(Integer id) {
        return messagechatMapper.selectById(id);
    }

    /**
     * 查询所有
     */
    public List<Messagechat> selectAll(Messagechat messagechat) {
        return messagechatMapper.selectAll(messagechat);
    }

    /**
     * 分页查询
     */
    public PageInfo<Messagechat> selectPage(Messagechat messagechat, Integer pageNum, Integer pageSize) {
		Account currentUser = TokenUtils.getCurrentUser();
		if ("student".equals(currentUser.getRole())) {
			messagechat.setStudentId(currentUser.getId());
		}

        PageHelper.startPage(pageNum, pageSize);
        List<Messagechat> list = this.selectAll(messagechat);

        return PageInfo.of(list);
    }

}