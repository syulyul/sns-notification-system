# application.properties

#

# log

logging.level.root=info
logging.level.bitcamp.myapp=debug
logging.level.bitcamp.myapp.dao=debug

#

# server

server.port=80
server.servlet.context-path=/

#

# database

#spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql:{db-public-url}
spring.datasource.username={username}
spring.datasource.password={password}

#

# mybatis

mybatis.type-aliases-package=bitcamp.myapp.vo
#mybatis.mapper-locations=/bitcamp/myapp/mapper/*Mapper.xml

#

# web

spring.servlet.multipart.max-file-size=20MB
spring.servlet.multipart.max-request-size=200MB

#

# thymeleaf # 개발하는 동안만 사용할 것

spring.thymeleaf.prefix=file:src/main/resources/templates/
spring.web.resources.static-locations=file:src/main/resources/static/
spring.web.resources.cache.period=0
spring.profiles.active=dev