## minio启动

+ mac 启动

> nohup minio server \ \
> --address 0.0.0.0:9000 \ \
> -console-address 0.0.0.0:9090 \ \
> /Users/chengyingkui/FileStore > /Users/chengyingkui/FileStore/minio.log 2>&1 &

修改默认账户密码
> export MINIO_ACCESS_KEY=xxx\
> export MINIO_SECRET_KEY=xxx

+ docker启动

> docker run --name minio \ \
-p 9000:9000 \ \
-p 9090:9090 \ \
-e "MINIO_ACCESS_KEY=admin" \ \
-e "MINIO_SECRET_KEY=cxxwl96@sina.com" \ \
-v /home/file-store:/data \ \
-v /home/app/minio/config:/root/.minio \ \
--restart=always -d \ \
localhost/minio server /data \ \
--address 0.0.0.0:9090 \ \
--console-address 0.0.0.0:9000 
