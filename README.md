# Spring项目模板

* 项目启动需要 MySQL、Redis

* 需要在 Application.yml 中配置 redis & MySQL 相关的`服务器 host` 以及密码

* 如果需要修改项目名称，需要修改包括以下包中相应地方的路径：

  * `JwtUtils`： `@ConfigurationProperties(prefix = "agriculture.jwt")`
  * `SwaggerConfig`： `apiInfo`
  * `RedisAspect`： `@Around`路径

* 友情提示：如果在服务器上配置 MySQL ，需要修改下 `sudo vi /etc/mysql/mysql.conf.d/mysqld.cnf`，把`bind-address=127.0.0.1`注释掉，然后 `service mysql restart`重启 MySQL 服务
