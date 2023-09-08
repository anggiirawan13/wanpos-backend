# SETUP APPLICATION.PROPERTIES
- spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
- spring.datasource.url=jdbc:mysql://localhost:3306/wanpos
- spring.datasource.username=
- spring.datasource.password=

- spring.jpa.show-sql=true
- spring.jpa.hibernate.ddl-auto=update
- spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL5Dialect
- server.port=8888

- server.port.staging=8889
- host.staging=https://staging.com
- host.client.allowed=http://127.0.0.1:5173
- spring.api-docs.enabled=true
