# 开发笔记
***
## 一、系统配置
### jvm参数
```text
-Djasypt.encryptor.password=cxxwl96@sina.com
```
### 启动命令
+ minio启动
```shell
# mac 启动
nohup minio server \
--address 0.0.0.0:9000 \
-console-address 0.0.0.0:9090 \
/Users/chengyingkui/FileStore > /Users/chengyingkui/FileStore/minio.log 2>&1 &
# 修改默认账户密码
export MINIO_ACCESS_KEY=xxx
export MINIO_SECRET_KEY=xxx
```
``` shell
# docker启动
docker run --name minio \
-p 9000:9000 \
-p 9090:9090 \
-e "MINIO_ACCESS_KEY=admin" \
-e "MINIO_SECRET_KEY=cxxwl96@sina.com" \
-v /home/file-store:/data \
-v /home/app/minio/config:k/root/.minio \
--restart=always -d \
localhost/minio server /data \
--address 0.0.0.0:9090 \
--console-address 0.0.0.0:9000
```

## 二、HuTool使用笔记
### IdUtil
+ ID生成样例
```text
IdUtil.fastUUID()                                                      // d1f92c23-2eeb-4300-97df-4b3f72436263
IdUtil.randomUUID()                                                    // 2129fce1-3abf-4228-a375-0882fe42b9ea
IdUtil.fastSimpleUUID()                                                // c0387b658332417dbe8a3dcf2460347f
IdUtil.simpleUUID()                                                    // eb410c3acfd5434c8ac748fa9f9502fd
IdUtil.objectId()                                                      // 61a389a66f506e51fb8aa683
IdUtil.nanoId()                                                        // 5ruqQQ8-E6g7W06JLpPJ-
IdUtil.nanoId(6)                                                       // yEmOlW
IdUtil.getSnowflake().nextId()                                         // 1464955398176665600
IdUtil.getSnowflake().nextIdStr()                                      // 1464955398176665601
IdUtil.getSnowflake().getWorkerId(System.currentTimeMillis())          // 27
IdUtil.getSnowflake().getDataCenterId(System.currentTimeMillis())      // 8
IdUtil.getSnowflake().getGenerateDateTime(System.currentTimeMillis())  // 1288835365212
```

## 三、springboot使用笔记
### springboot starter validation
+ 约束性注解(简单)说明

| 注解 | 功能 |
| ------- | ------- |
| @AssertFalse | 可以为null,如果不为null的话必须为false | 
| @AssertTrue |  可以为null,如果不为null的话必须为true | 
| @DecimalMax | 设置不能超过最大值 | 
| @DecimalMin | 设置不能超过最小值 | 
| @Digits|设置必须是数字且数字整数的位数和小数的位数必须在指定范围内 | 
| @Future | 日期必须在当前日期的未来 | 
| @Past| 日期必须在当前日期的过去 | 
| @Max| 最大不得超过此最大值 | 
| @Min | 最大不得小于此最小值 | 
| @NotNull | 不能为null，可以是空 | 
| @Null | 必须为null | 
| @Pattern | 必须满足指定的正则表达式 | 
| @Size | 集合、数组、map等的size()值必须在指定范围内 | 
| @Email | 必须是email格式 | 
| @Length | 长度必须在指定范围内 | 
| @NotBlank | 字符串不能为null,字符串trim()后也不能等于“” | 
| @NotEmpty | 不能为null，集合、数组、map等size()不能为0；字符串trim()后可以等于“” | 
| @Range | 值必须在指定范围内 | 
| @URL | 必须是一个URL | 