package com.fun.demo.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fun.demo.entity.Student;
import com.fun.demo.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * 学生表(Student)表控制层
 *
 * @author whywhathow
 * @since 2022-02-21 15:37:12
 */
@RestController
@RequestMapping("student")
@Slf4j
public class StudentController extends ApiController {
    /**
     * 服务对象
     */
    @Resource

    private StudentService studentService;

    /**
     * 分页查询所有数据
     *
     * @param page    分页对象
     * @param student 查询实体
     * @return 所有数据
     */
    @GetMapping
    public R selectAll(Page<Student> page, Student student) {
        return success(this.studentService.page(page, new QueryWrapper<>(student)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public R selectOne(@PathVariable Serializable id) {
        log.info("当前线程,->{}", Thread.currentThread().getName());
        return success(this.studentService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param student 实体对象
     * @return 新增结果
     */
    @PostMapping
    public R insert(@RequestBody Student student) {
        return success(this.studentService.save(student));
    }

    /**
     * 修改数据
     *
     * @param student 实体对象
     * @return 修改结果
     */
    @PutMapping
    public R update(@RequestBody Student student) {
        return success(this.studentService.updateById(student));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public R delete(@RequestParam("idList") List<Long> idList) {
        return success(this.studentService.removeByIds(idList));
    }
}

