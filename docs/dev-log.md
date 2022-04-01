### 2022.2.16

- 在构建项目的过程中,出现 classpath问题, 大致原因:
  - 项目问题--> rebuild项目
  - maven resources 资源配置问题(导致resources文件夹下的配置文件被排除在外了), 重新修改一下.
  
- **在实现多环境配置开发中, 发现由于springboot 版本不同导致application.yml 不能成功应用其他application-dev.yml**
  
  - **解决方案: spring.config.use-legacy-processing=true**
  - 官方链接
  
  ### 2022.3.22
  
  MySQL查询结果与Mybatis 查询结果不一致:
  
  Reason:
  
  ​	Druid select 查询语句 **结果集返回行数限制,** 
  
  ​	 **stupid ! ! !**
  
  
  
  
  
  
  
  
  
  
  
  
