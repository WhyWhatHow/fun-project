echo "启动网关模块"
#nohup java -Dfile.encoding=utf-8 -jar -Xmx256m -Xms256m ../fun-gateway/target/*.jar   > logs/fun-gateway.log 2>&1 &
# hint : 需要先给zero-skywalking地址,
nohup java \
  -javaagent:agent/skywalking-agent.jar\
  -Dskywalking.agent.service_name=fun-gateway\
  -Dskywalking.collector.backend_service=zero-skywalking:11800 \
  -Dfile.encoding=utf-8 -jar -Xmx400m -Xms400m pig-gateway/target/*.jar   > logs/fun-gateway.log 2>&1 &
# 日志打印, 不会关闭后台程序
tail -f logs/fun-gateway.log