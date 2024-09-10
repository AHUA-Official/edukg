package com.example.service;

import cn.hutool.core.util.ObjectUtil;
import com.example.common.Constants;
import com.example.common.config.TokenUtils;
import com.example.common.enums.ResultCodeEnum;
import com.example.entity.Account;
import com.example.entity.Teacher;
import com.example.exception.CustomException;
import com.example.mapper.TeacherMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TeacherService {

    @Resource
    private TeacherMapper teacherMapper;

    /**
     * 新增
     */
    public void add(Teacher teacher) {
        // 唯一校验
        Teacher dbTeacher = teacherMapper.selectByUsername(teacher.getUsername());
        if (ObjectUtil.isNotNull(dbTeacher)) {
            throw new CustomException(ResultCodeEnum.USER_EXIST_ERROR);
        }
        if (ObjectUtil.isEmpty(teacher.getPassword())) {
            teacher.setPassword(Constants.USER_DEFAULT_PASSWORD);
        }
        if (ObjectUtil.isEmpty(teacher.getName())) {
            teacher.setName(teacher.getUsername());
        }
        teacher.setRole("teacher");
        teacherMapper.insert(teacher);
    }

    /**
     * 删除
     */
    public void deleteById(Integer id) {
        teacherMapper.deleteById(id);
    }

    /**
     * 批量删除
     */
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            teacherMapper.deleteById(id);
        }
    }

    /**
     * 修改
     */
    public void updateById(Teacher teacher) {
        teacherMapper.updateById(teacher);
    }

    /**
     * 根据ID查询
     */
    public Teacher selectById(Integer id) {
        return teacherMapper.selectById(id);
    }

    /**
     * 查询所有
     */
    public List<Teacher> selectAll(Teacher teacher) {
        return teacherMapper.selectAll(teacher);
    }

    /**
     * 分页查询
     */
    public PageInfo<Teacher> selectPage(Teacher teacher, Integer pageNum, Integer pageSize) {

        PageHelper.startPage(pageNum, pageSize);
        List<Teacher> list = teacherMapper.selectAll(teacher);

        return PageInfo.of(list);
    }

    /**
     * 注册
     */
    public void register(Account account) {
        Teacher teacher = new Teacher();
        teacher.setUsername(account.getUsername());
        teacher.setPassword(account.getPassword());
        this.add(teacher);
    }

    public Teacher login(Account account) {
        Teacher dbTeacher = teacherMapper.selectByUsername(account.getUsername());
        if (ObjectUtil.isNull(dbTeacher)) {
            throw new CustomException(ResultCodeEnum.USER_NOT_EXIST_ERROR);
        }
        if (!account.getPassword().equals(dbTeacher.getPassword())) {
            throw new CustomException(ResultCodeEnum.USER_ACCOUNT_ERROR);
        }
        // 生成token
        String tokenData = dbTeacher.getId() + "-teacher";
        String token = TokenUtils.genToken(tokenData, dbTeacher.getPassword());
        dbTeacher.setToken(token);


        return dbTeacher;
    }
    public boolean  login2(Account account) {
        Teacher dbTeacher = teacherMapper.selectByUsername(account.getUsername());
        if (ObjectUtil.isNull(dbTeacher)) {
          return  true;
        }
        if (!account.getPassword().toString().equals(dbTeacher.getPassword().toString())) {
          return  true;
        }
        // 生成token
        String tokenData = dbTeacher.getId() + "-teacher";
        String token = TokenUtils.genToken(tokenData, dbTeacher.getPassword());
        dbTeacher.setToken(token);


        return true;
    }
    /**
     * 修改密码
     */
    public void updatePassword(Account account) {
        Teacher dbTeacher = teacherMapper.selectByUsername(account.getUsername());
        if (ObjectUtil.isNull(dbTeacher)) {
            throw new CustomException(ResultCodeEnum.USER_NOT_EXIST_ERROR);
        }
        if (!account.getPassword().equals(dbTeacher.getPassword())) {
            throw new CustomException(ResultCodeEnum.PARAM_PASSWORD_ERROR);
        }
        dbTeacher.setPassword(account.getNewPassword());
        teacherMapper.updateById(dbTeacher);
    }

    public Teacher selectByJobNum(Account account) {
        Teacher dbTeacher = teacherMapper.selectByUsername(account.getUsername());
        return  dbTeacher;
    }

    public Teacher selectByUsername(String jobNum) {
        Teacher dbTeacher = teacherMapper.selectByUsername(jobNum);
        return  dbTeacher;
    }
}