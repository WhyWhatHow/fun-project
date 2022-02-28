package com.fun.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fun.demo.entity.Student;
import com.fun.demo.mapper.StudentMapper;
import com.fun.demo.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * 学生表(Student)表服务实现类
 *
 * @author whywhathow
 * @since 2022-02-21 15:19:45
 */
@Service
@Slf4j
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {
    @Autowired
    StudentMapper mapper;

    @Override
    @Async
    public Student getById(Serializable id) {
        Student student = mapper.selectById(id);
        log.info("[test-mapper]-- {} " ,student);
        log.info("当前线程,->{}", Thread.currentThread().getName());
        return student;
    }
}
