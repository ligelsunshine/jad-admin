# application.yml备份

```yaml
# Debugger Configuration
debug: false

# Server Configuration
server:
  port: 8080

# Spring Configuration
spring:
  application:
    name: JADAdmin
  profiles:
    active: dev # 环境设置：dev、test、prod
  datasource:
    primary-db: jad-mysql-sys # 主库配置
    # 多源数据库
    multiple-db:
      jad-mysql-sys:
        url: jdbc:mysql://cxxwl96.cn:3306/jad_admin?useUnicode=true&characterEncoding=utf8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT%2B8&useSSL=true&allowMultiQueries=true&autoReconnect=true&useAffectRows=true
        username: root
        password: cxxwl96@sina.com
        driver-class-name: com.mysql.cj.jdbc.Driver
      jad-postgresql-sys:
        url: jdbc:postgresql://cxxwl96.cn:5432/jad_admin?currentSchema=public
        username: postgres
        password: cxxwl96@sina.com
        driver-class-name: org.postgresql.Driver
    dbcp2:
      max-idle: 10
      min-idle: 5
      max-wait-millis: 10000
      initial-size: 5
  redis:
    host: 114.55.143.66
    port: 6379
    password: cxxwl96@sina.com
    database: 1
    timeout: 3000 # 连接超时时间（毫秒）
    jedis:
      pool:
        max-active: 200 # 连接池最大连接数（使用负值表示没有限制）
        max-wait: -1 # 连接池最大阻塞等待时间（使用负值表示没有限制）
        max-idle: 10 # 连接池中的最大空闲连接
        min-idle: 0 # 连接池中的最小空闲
  jackson:
    date-format: yyyy-MM-dd HH:mm:ss # 返回json的全局时间格式
    time-zone: GMT+8 # 时区
  devtools:
    restart:
      enabled: true # 设置开启热部署
  freemarker:
    cache: false # 页面不加载缓存，修改即时生效

# MyBatis-plus Configuration
mybatis-plus:
  mapper-locations: classpath*:com/jad/**/xml/*.xml # 配置mapper xml文件的路径
  configuration:
    log-com.jad.common.service.impl: org.apache.ibatis.logging.stdout.StdOutImpl # mybatis日志
  global-config:
    db-config:
      update-strategy: ignored
      insert-strategy: ignored

# Log Configuration
logging:
  level:
    com.baomidou.samples.metainfo: debug
```