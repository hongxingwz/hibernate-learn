# 项目说明

## 需要你做的事
把/src/main/resources/hibernate.cfg.xml中  
connection.url 换成您相应的数据库url 并指定链接的库(默认为hibernate4，如果不存在需要手动创建一个)  
把connection.username connection.password换成您数据库的用户名和密码


## hibernate配制文件
/src/main/resources/hibernate.cfg.xml配制了hiberante

## Event实体
/src/main/java/com/jianglei/entity/Event.java


## Event实体的映射文件
/src/main/java/com/jianglei/entity/Event.hbm.xml

## 测试类
/src/test/java/com/jianglei/entity/EventTest.java  
可以依次执行testSave(), testGet(), testUpdate(), testQuery(), testDelete()方法


