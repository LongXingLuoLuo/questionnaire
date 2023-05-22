# 使用方法
## 相关设置
设置文件为 jar 包里的 `BOOT-INF/classes/application.properties` 文件
### 应用服务 WEB 访问端口
```properties
server.port=8090
```
访问端口
### 数据库链接
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/questionnaire?useUnicode=true&characterEncoding=UTF-8&useSSL=false
```
服务器数据库链接地址
### 数据库用户名
```properties
spring.datasource.username=root
```
数据库用户名
### 数据库用户密码
```properties
spring.datasource.password=qwe123
```
数据库用户密码
上述用户名、密码、数据库链接和访问端口可以根据需要改变
## 网站链接说明
下面以主机名为`localhost`，访问端口为`8090`示例
### 登录地址
```http request
localhost:8090/login
```
后台管理用户登录地址
### 后台管理页面
```http request
localhost:8090/admin/management
```
需要登录才能进入，可以管理年级，专业，课程，教师以及问卷
在问卷管理面板可以查看问卷全部填写结果和复制问卷访问链接
### 问卷访问链接
```http request
localhost:8090/visit/{{id}}
```
问卷`id`属性为id的访问链接
该地址可在问卷管理面板点复制按钮得到
### 问卷填写结果管理
```http request
localhost:8090/questionnaire_answer/table/{{id}}
```
问卷`id`属性为id的填写结果管理页面
可以导出为excel文件（等待时间略长）