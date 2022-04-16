echo "启动网关模块"
nohup java -Dfile.encoding=utf-8 -jar -Xmx256m -Xms256m ../fun-gateway/target/*.jar   > logs/fun-gateway.log 2>&1 &
