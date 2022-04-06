## Easycode swagger3 的默认代码生成模板

Easy code 配合Swagger3(springdoc 整合) 注解生成 

以fun-system为例

- [x] ###  Entity-pk.java

  com.fun.system.api.entity

```java
##导入宏定义
$!{define.vm}
## 定义排除字段
##保存文件（宏定义）
#save("/api/entity", ".java")

##包路径（宏定义）
#setPackageSuffix("api.entity")

## 定义排除字段
#set($excludeColumns = ["createTime","updateTime","status","delFlag"])

##自动导入包（全局变量）
$!{autoImport.vm}
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fun.common.core.domain.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

##表注释（宏定义）
##tableComment("表实体类")
/**
 * $!{tableInfo.comment}($!{tableInfo.name})表实体类
 * 
 * @author $author
 * @since $!time.currTime()
 */
@EqualsAndHashCode
@Data
@TableName("$!{tableInfo.obj.name}")
@Schema(description = "$!{tableInfo.name}实体类")
@SuppressWarnings("serial")
public class $!{tableInfo.name} extends  BaseEntity {
    
#foreach($column in $tableInfo.fullColumn)
    #if(!$excludeColumns.contains($column.name))
        /**
        * $column.comment
        */
        ## 主键(主键只能是单值)判断
        #if($column.name == $tableInfo.pkColumn.get(0).name)
        @TableId
        #end
    	@Schema(description="$column.comment")    
    	private $!{tool.getClsNameByFullName($column.type)} $!{column.name};
     #end
#end
}
```

- [x] ### Mapper.java

com.fun.system.api.mapper

```java
##导入宏定义
$!{define.vm}

##设置表后缀（宏定义）
#setTableSuffix("Mapper")

##保存文件（宏定义）
#save("/api/mapper", "Mapper.java")

##包路径（宏定义）
#setPackageSuffix("api.mapper")


import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
## 如果你的entity 不再 api.entity 中, 可以自行修改到你自己entity包中.
import $!{tableInfo.savePackageName}.api.entity.$!tableInfo.name;

##表注释（宏定义）
##tableComment("表数据库访问层")
/**
 * $!{tableInfo.comment} $!{tableInfo.name}Mapper DAO层
 * 
 * @author $author
 * @since $!time.currTime()
 */
public interface $!{tableName} extends BaseMapper<$!tableInfo.name> {

/**
* 批量新增数据（MyBatis原生foreach方法）
*
* @param entities List<$!{tableInfo.name}> 实例对象列表
* @return 影响行数
*/
int insertBatch(@Param("entities") List<$!{tableInfo.name}> entities);

/**
* 批量新增或按主键更新数据（MyBatis原生foreach方法）
*
* @param entities List<$!{tableInfo.name}> 实例对象列表
* @return 影响行数
* @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
*/
int insertOrUpdateBatch(@Param("entities") List<$!{tableInfo.name}> entities);
}

```

- [x] ### Mapper.xml

```xml
##引入mybatis支持
$!{mybatisSupport.vm}

##设置保存名称与保存位置
$!callback.setFileName($tool.append($!{tableInfo.name}, "Mapper.xml"))
$!callback.setSavePath($tool.append($modulePath, "/src/main/resources/mapper"))

##拿到主键
#if(!$tableInfo.pkColumn.isEmpty())
    #set($pk = $tableInfo.pkColumn.get(0))
#end
## mapper,entity 放在api 模块下, 
##以com.fun.demo 为例-> com.fun.demo.api.entity || com.fun.demo.api.mapper
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="$!{tableInfo.savePackageName}.api.mapper.$!{tableInfo.name}Mapper">

    <resultMap type="$!{tableInfo.savePackageName}.api.entity.$!{tableInfo.name}" id="$!{tableInfo.name}Map">
#foreach($column in $tableInfo.fullColumn)
        <result property="$!column.name" column="$!column.obj.name" jdbcType="$!column.ext.jdbcType"/>
#end
    </resultMap>

    <!-- 批量插入 -->
    <insert id="insertBatch" keyProperty="$!pk.name" useGeneratedKeys="true">
        insert into $!{tableInfo.obj.parent.name}.$!{tableInfo.obj.name}(#foreach($column in $tableInfo.otherColumn)$!column.obj.name#if($velocityHasNext), #end#end)
        values
        <foreach collection="entities" item="entity" separator=",">
        (#foreach($column in $tableInfo.otherColumn)#{entity.$!{column.name}}#if($velocityHasNext), #end#end)
        </foreach>
    </insert>
    <!-- 批量插入或按主键更新 -->
    <insert id="insertOrUpdateBatch" keyProperty="$!pk.name" useGeneratedKeys="true">
        insert into $!{tableInfo.obj.parent.name}.$!{tableInfo.obj.name}(#foreach($column in $tableInfo.otherColumn)$!column.obj.name#if($velocityHasNext), #end#end)
        values
        <foreach collection="entities" item="entity" separator=",">
            (#foreach($column in $tableInfo.otherColumn)#{entity.$!{column.name}}#if($velocityHasNext), #end#end)
        </foreach>
        on duplicate key update
         #foreach($column in $tableInfo.otherColumn)$!column.obj.name = values($!column.obj.name) #if($velocityHasNext), #end#end
    </insert>

</mapper>

```

- [x] ### Service.java

```java
##导入宏定义
$!{define.vm}

##设置表后缀（宏定义）
#setTableSuffix("Service")

##保存文件（宏定义）
#save("/service", "Service.java")

##包路径（宏定义）
#setPackageSuffix("service")

import com.baomidou.mybatisplus.extension.service.IService;
import $!{tableInfo.savePackageName}.api.entity.$!tableInfo.name;

##表注释（宏定义）
##tableComment("")
/**
 *
 * @author $author
 * @since $!time.currTime()
 */
public interface $!{tableName} extends IService<$!tableInfo.name> {
}

```



- [x] ### ServiceImpl.java

```java
##导入宏定义
$!{define.vm}

##设置表后缀（宏定义）
#setTableSuffix("ServiceImpl")

##保存文件（宏定义）
#save("/service/impl", "ServiceImpl.java")

##包路径（宏定义）
#setPackageSuffix("service.impl")

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import $!{tableInfo.savePackageName}.api.mapper.$!{tableInfo.name}Mapper;
import $!{tableInfo.savePackageName}.api.entity.$!{tableInfo.name};
import $!{tableInfo.savePackageName}.service.$!{tableInfo.name}Service;
import org.springframework.stereotype.Service;

##表注释（宏定义）
#tableComment("")
@Service("$!tool.firstLowerCase($tableInfo.name)Service")
public class $!{tableName} extends ServiceImpl<$!{tableInfo.name}Mapper, $!{tableInfo.name}> implements $!{tableInfo.name}Service {

}

```

- [ ] ### Controller.java

  无权限校验

```java
##定义初始变量
#set($tableName = $tool.append($tableInfo.name, "Controller"))
##设置回调
$!callback.setFileName($tool.append($tableName, ".java"))
$!callback.setSavePath($tool.append($tableInfo.savePath, "/controller"))
##拿到主键
#if(!$tableInfo.pkColumn.isEmpty())
    #set($pk = $tableInfo.pkColumn.get(0))
#end

#if($tableInfo.savePackageName)package $!{tableInfo.savePackageName}.#{end}controller;

import $!{tableInfo.savePackageName}.service.$!{tableInfo.name}Service;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;
import javax.annotation.Resource;
import io.swagger.v3.oas.annotations.tags.Tag;


/**
 * $!{tableInfo.comment} 
 *
 * @author  $author
 * @since $!time.currTime()
 */
@Tag(name = "$!{tableInfo.comment}$!{tableInfo.name}") 
@Validated
@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("$!tool.firstLowerCase($tableInfo.name)")
public class $!{tableName} {
    @Resource
    private final $!{tableInfo.name}Service $!tool.firstLowerCase($tableInfo.name)Service;
    
     /**
     * 分页查询
     * @param page 分页对象
     * @param ${classname} ${comments}
     * @return
     */
    @Operation(summary = "分页查询", description = "分页查询")
    @GetMapping("/page" )
    public R Page(Page page, ${className} ${classname}) {
        return R.ok(${classname}Service.page(page, Wrappers.query(${classname})));
    }


    /**
     * 通过id查询${comments}
     * @param ${pk.lowerAttrName} id
     * @return R
     */
    @Operation(summary = "通过id查询", description= "通过id查询")
    @GetMapping("/{${pk.lowerAttrName}}" )
    public R getById(@PathVariable("${pk.lowerAttrName}" ) ${pk.attrType} ${pk.lowerAttrName}) {
        return R.ok(${classname}Service.getById(${pk.lowerAttrName}));
    }

    /**
     * 新增${comments}
     * @param ${classname} ${comments}
     * @return R
     */
   @Operation(summary = "新增${comments}", description= "新增${comments}")
    @PostMapping
    @PreAuthorize("@pms.hasPermission('${moduleName}_${pathName}_add')" )
    public R save(@RequestBody ${className} ${classname}) {
        return R.ok(${classname}Service.save(${classname}));
    }

    /**
     * 修改${comments}
     * @param ${classname} ${comments}
     * @return R
     */
  	@Operation(summary = "修改${comments}", description= "修改${comments}")
    @PutMapping
    @PreAuthorize("@pms.hasPermission('${moduleName}_${pathName}_edit')" )
    public R updateById(@RequestBody ${className} ${classname}) {
        return R.ok(${classname}Service.updateById(${classname}));
    }

    /**
     * 通过id删除${comments}
     * @param ${pk.lowerAttrName} id
     * @return R
     */
    @Operation(summary = "通过id删除${comments}", description= "通过id删除${comments}")
    @SysLog("通过id删除${comments}" )
    @DeleteMapping("/{${pk.lowerAttrName}}" )
    @PreAuthorize("@pms.hasPermission('${moduleName}_${pathName}_del')" )
    public R removeById(@PathVariable ${pk.attrType} ${pk.lowerAttrName}) {
        return R.ok(${classname}Service.removeById(${pk.lowerAttrName}));
    }

}

```





*  含权限校验 

```java
##定义初始变量
#set($tableName = $tool.append($tableInfo.name, "Controller"))
##设置回调
$!callback.setFileName($tool.append($tableName, ".java"))
$!callback.setSavePath($tool.append($tableInfo.savePath, "/controller"))
##拿到主键
#if(!$tableInfo.pkColumn.isEmpty())
    #set($pk = $tableInfo.pkColumn.get(0))
#end

#if($tableInfo.savePackageName)package $!{tableInfo.savePackageName}.#{end}controller;

import $!{tableInfo.savePackageName}.service.$!{tableInfo.name}Service;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;
import javax.annotation.Resource;
import io.swagger.v3.oas.annotations.tags.Tag;


/**
 * $!{tableInfo.comment} 
 *
 * @author  $author
 * @since $!time.currTime()
 */
@Tag(name = "$!{tableInfo.comment}$!{tableInfo.name}") 
@Validated
@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("$!tool.firstLowerCase($tableInfo.name)")
public class $!{tableName} {
    @Resource
    private final $!{tableInfo.name}Service $!tool.firstLowerCase($tableInfo.name)Service;
    
     /**
     * 分页查询
     * @param page 分页对象
     * @param ${classname} ${comments}
     * @return
     */
    @ApiOperation(summary = "分页查询", description = "分页查询")
    @GetMapping("/page" )
    @PreAuthorize("@pms.hasPermission('${moduleName}_${pathName}_get')" )
    public R get${className}Page(Page page, ${className} ${classname}) {
        return R.ok(${classname}Service.page(page, Wrappers.query(${classname})));
    }


    /**
     * 通过id查询${comments}
     * @param ${pk.lowerAttrName} id
     * @return R
     */
    @Operation(summary = "通过id查询", description= "通过id查询")
    @GetMapping("/{${pk.lowerAttrName}}" )
    @PreAuthorize("@pms.hasPermission('${moduleName}_${pathName}_get')" )
    public R getById(@PathVariable("${pk.lowerAttrName}" ) ${pk.attrType} ${pk.lowerAttrName}) {
        return R.ok(${classname}Service.getById(${pk.lowerAttrName}));
    }

    /**
     * 新增${comments}
     * @param ${classname} ${comments}
     * @return R
     */
   @Operation(summary = "新增${comments}", description= "新增${comments}")
    @PostMapping
    @PreAuthorize("@pms.hasPermission('${moduleName}_${pathName}_add')" )
    public R save(@RequestBody ${className} ${classname}) {
        return R.ok(${classname}Service.save(${classname}));
    }

    /**
     * 修改${comments}
     * @param ${classname} ${comments}
     * @return R
     */
   @Operation(summary = "修改${comments}", description= "修改${comments}")
    @PutMapping
    @PreAuthorize("@pms.hasPermission('${moduleName}_${pathName}_edit')" )
    public R updateById(@RequestBody ${className} ${classname}) {
        return R.ok(${classname}Service.updateById(${classname}));
    }

    /**
     * 通过id删除${comments}
     * @param ${pk.lowerAttrName} id
     * @return R
     */
   @Operation(summary = "通过id删除${comments}", description= "通过id删除${comments}")
    @DeleteMapping("/{${pk.lowerAttrName}}" )
    @PreAuthorize("@pms.hasPermission('${moduleName}_${pathName}_del')" )
    public R removeById(@PathVariable ${pk.attrType} ${pk.lowerAttrName}) {
        return R.ok(${classname}Service.removeById(${pk.lowerAttrName}));
    }

}
```

