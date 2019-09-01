# Getting Started

When user send the request to the greeting service greets the user with a welcome message, tracks the User Visits and return stats in visits service

### 

Run the Greeting Application

* Run the Maven install from the IDE or maven install from command line. 
* To Run the greeting micri service as jar use the below command

  `java -jar greeting-0.0.1-SNAPSHOT.jar`

To configure the port  
* Update the server.port property in the application.properties or the application.yml. application.properties will take the precedence
* From the command line pass the port number by adding the --server.port= <<Port Number>>
  
   java -jar greeting-0.0.1-SNAPSHOT.jar --server.port=8081

Access the Application

* For API documentation used the Swagger. When request made to the server root redirected to the swagger-ui.html page.

There are two APIs
* /greeting - Accept the POST request with UserRequestDTO as parameter. Persists the User if not exists and add UserVisit Entry.
* /visits - return the all the statistics of the User Visits.

Database

* Data will be persisted in the H2 in memory database/
* To Access the H2 console http://localhost:8080/h2. Change the port to application runnning port. 
* Two tables are created USER and USER_VISIT, one to many relaionship on user_id column. 

Persisting 

* Spring Data with JPA used to persist the data. 

* [JPQL Group By] (https://www.objectdb.com/java/jpa/query/jpql/group#GROUP_BY_as_DISTINCT_no_Aggregates)



Changing the Base URI

* [Refer the MvcConfig to customize/redirect the base url to application specific URL](https://spring.io/guides/gs/securing-web/)



### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/{bootVersion}/reference/htmlsingle/#using-boot-devtools)
* [Spring Boot Actuator](https://docs.spring.io/spring-boot/docs/{bootVersion}/reference/htmlsingle/#production-ready)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/{bootVersion}/reference/htmlsingle/#boot-features-jpa-and-spring-data)
* [Spring Web Starter](https://docs.spring.io/spring-boot/docs/{bootVersion}/reference/htmlsingle/#boot-features-developing-web-applications)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service with Spring Boot Actuator](https://spring.io/guides/gs/actuator-service/)
* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)

