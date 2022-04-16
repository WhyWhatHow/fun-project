建议: 服务器内存小于16G时, 放弃docker ,选择 jar包运行,节省内存空间 
PS: 也可以配置GC版本
以jar包的形式运行服务
按照步骤运行
1. maven clean install
2. 执行 bash start-fun-**.sh 启动 **模块
    example:
   ```bash
   nohup java -Dfile.encoding=utf-8 -jar -Xmx256m -Xms256m ../fun-gateway/target/*.jar   > logs/fun-gateway.log 2>&1 &
   ```
