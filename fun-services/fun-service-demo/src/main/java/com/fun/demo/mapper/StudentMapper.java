package com.fun.demo.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.fun.demo.entity.Student;

/**
 * 学生表(Student)表数据库访问层
 *
 * @author whywhathow
 * @since 2022-02-21 15:19:43
 */
public interface StudentMapper extends BaseMapper<Student> {

/**
* 批量新增数据（MyBatis原生foreach方法）
*
* @param entities List<Student> 实例对象列表
* @return 影响行数
*/
int insertBatch(@Param("entities") List<Student> entities);

/**
* 批量新增或按主键更新数据（MyBatis原生foreach方法）
*
* @param entities List<Student> 实例对象列表
* @return 影响行数
* @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
*/
int insertOrUpdateBatch(@Param("entities") List<Student> entities);

}

