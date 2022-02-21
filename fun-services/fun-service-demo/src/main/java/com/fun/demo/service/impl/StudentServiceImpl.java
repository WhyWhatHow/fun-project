package com.fun.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fun.demo.mapper.StudentMapper;
import com.fun.demo.entity.Student;
import com.fun.demo.service.StudentService;
import org.springframework.stereotype.Service;

/**
 * 学生表(Student)表服务实现类
 *
 * @author  whywhathow
 * @since 2022-02-21 15:19:45
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {

}
