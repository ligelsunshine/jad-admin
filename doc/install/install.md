+ 安装docker

```shell
sudo yum install -y yum-utils  device-mapper-persistent-data  lvm2
sudo yum-config-manager  --add-repo   https://download.docker.com/linux/centos/docker-ce.repo
sudo yum install docker-ce docker-ce-cli containerd.io

sudo systemctl enable docker.service
sudo systemctl start docker.service
```

+ docker安装mysql

```shell
docker pull mysql:8.0.25;

docker run -dit --name mysql8 -p 3306:3306 -e MYSQL_ROOT_PASSWORD=123456 mysql:8.0.25 --lower_case_table_names=1;

docker exec -it 容器id bash;

mysql -uroot -pcxxwl96@sina.com;

use mysql;
# 创建用户
create user 'root'@'192.168.0.%' identified by '123456';

# 给创建好的用户赋予远程登录权限
grant all on *.* to 'root'@'192.168.0.%' with grant option;

# 刷新权限
flush privileges;
```

+ docker安装redis

```shell
docker pull redis;

docker run -dit --name redis -p 6379:6379 -v /home/data/redis/conf/redis.conf:/etc/redis/redis.conf -v /home/data/redis/data:/data redis redis-server /etc/redis/redis.conf --appendonly yes;
```

+ docker安装minio

```shell
docker pull minio/minio;

docker run -dit --name minio -p 9000:9000 -p 9090:9090 \
--restart=always \
-v /home/data/minio/data:/data \
-v /home/data/minio/conf:/root/.minio \
-e "MINIO_ACCESS_KEY=admin" \
-e "MINIO_SECRET_KEY=cxxwl96@sina.com" \
minio/minio server /data \
--address 0.0.0.0:9000 \
-console-address 0.0.0.0:9090;
```

+ docker安装nginx

```shell
docker pull nginx;

docker build -t yssj-web .;

docker run -dit --name yssj-web -p 8080:80 yssj-web

```

+ Java -jar启动

```shell
nohup java -jar \
 -Dspring.config.location=conf/setting.yml \
 -Dlogging.config=conf/log4j2.xml \
 -Djasypt.encryptor.password=cxxwl96@sina.com \
 system-1.0.0.jar \
 > logs/nohup.log 2>&1 &
```