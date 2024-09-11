
#!/bin/bash
 
# 定义BIN文件的URL和变量
BIN_URL="http://storage.zstack.io/mirror/zstack_zsv_4.2.8/latest/ZStack-ZSphere-installer-4.2.8-2407190347-115.bin"
BIN="ZStack-ZSphere-installer-4.2.8-2407190347-115.bin"
 
# 切换到ZStack安装目录并列出内容
cd /usr/local/
ll
 
# 检查zstack目录是否存在并删除
if [ -d "zstack" ]; then
    rm -rf zstack  # 删除zstack目录
fi
 
ll  # 再次列出目录内容，确认zstack目录已被删除
pwd  # 显示当前目录
 
# 切换到根目录查看系统版本
cd /root
cat /etc/zstack-release  # 打印ZStack版本信息
 
# 下载BIN文件到当前目录
wget $BIN_URL -O $BIN
 
# 检查下载是否成功
if [ $? -ne 0 ]; then
    echo "下载BIN文件失败，请检查URL是否正确。"
    exit 1
fi
 
# 检查BIN文件是否存在
if [ ! -f "$BIN" ]; then
    echo "BIN文件不存在，下载可能未完成或文件名错误。"
    exit 1
fi
 
# 运行BASH脚本执行BIN文件
bash $BIN -r /usr/local/zstack -D  # 执行BIN文件进行安装或升级