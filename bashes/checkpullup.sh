#!/bin/bash
#@auther  llj
#@time  2024/09/08 
# 定义日志文件路径
LOG_FILE="service_check.log"

check() {
    local count=0
    while [ $count -lt 3 ]; do
        if curl -s http://8.137.104.90:8099/isOK | grep -q "OK"; then
            echo "$(date "+%Y-%m-%d %H:%M:%S")服务正常喵喵喵" | tee -a "$LOG_FILE"
            return 0
        else
            echo "$(date "+%Y-%m-%d %H:%M:%S")服务异常喵喵喵喵，尝试重新检查..." | tee -a "$LOG_FILE"
            count=$((count+1))
        fi
    done
    return 1
}
pullup() {
    echo "$(date "+%Y-%m-%d %H:%M:%S") - 正在重启服务..." | tee -a "$LOG_FILE"
    
    PIDS=$(lsof -i :8099 | grep -v PID | awk '{print $2}')

    # 杀死这些进程
    if [ ! -z "$PIDS" ]; then
        echo "$(date "+%Y-%m-%d %H:%M:%S") - 杀死进程: $PIDS" | tee -a "$LOG_FILE"
        kill -9 $PIDS
    else
        echo "$(date "+%Y-%m-%d %H:%M:%S") - 没有找到占用 8099 端口的进程" | tee -a "$LOG_FILE"
        nohup java -jar springboot-0.0.1-SNAPSHOT.jar > /dev/null 2>&1 &
    fi

    # 启动服务
    cd /edukg
    nohup java -jar springboot-0.0.1-SNAPSHOT.jar > /dev/null 2>&1 &
    echo "$(date "+%Y-%m-%d %H:%M:%S") - 服务已重启" | tee -a "$LOG_FILE"
}

while true; do
    if check; then
        echo "$(date "+%Y-%m-%d %H:%M:%S")服务状态良好，无需重启。哦耶~" | tee -a "$LOG_FILE"
    else
        echo "$(date "+%Y-%m-%d %H:%M:%S")服务连续三次检查异常，正在尝试重启服务...呜呜呜" | tee -a "$LOG_FILE"
        pullup
    fi
    # 等待10秒
    sleep 1
done

