# crud-springboot

## Configuração do Banco de Dados
Crie um banco de dados MySQL com o nome `springboot` e adicione as credenciais em `/resources/application.properties`.  
Exemplo:

```
spring.datasource.url=jdbc:mysql://localhost:3306/springboot
spring.datasource.username=root
spring.datasource.password=

spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL5InnoDBDialect
```
