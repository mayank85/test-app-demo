# test-app-demo-quickstart-guide

This baby project has been developled as a demo test code to implement the Question answer exchange between user and service using Java 1.8, Spring Boot.
You can extend this simple project to quickly bootstrap your other services and improve it further.

It's a pre-configured Maven project containing a sample restful application and all required dependencies.

## Getting Started

### Technology Stack

- Java 1.8
- STS 4 ( Spring Tool Suite 4) IDE
- Spring Boot 2.3.2

### Clone

To get started you can simply clone this repository using git:

```
git clone https://github.com/mayank85/test-app-demo.git
cd test-app-demo
```

### Configuration

In order to get your application working you have the following default settings:

```
server.port = 8078
spring.devtools.restart.enabled = true
test.number.limit = 50
```

The configuration is located in `src/main/resources/application.yml`.
These default values can be changed if required but it would need a fresh build of executable Jar file to reflect the changes.

### Application Execution

=> You can run the application using the windows batch script file
   ```
   "testappdemo.bat" 
   
   ```
   located in 'test-app-demo\target'.
   
=> This application runs on default port 8078 but if you want to the service to run on ports other than default port, please
    refer to section "Running Application on custom ports".
=> All the services are being served at a single path "/"

### Running Application on custom ports

There are 2 ways to change the default port

1. Edit the windows batch script file
   => start java -Dserver.port=8078 -jar test-app-demo-0.0.1-SNAPSHOT.jar
   Here we can specify our custom port

2. Chanege the value of "server.port" key in application.yml configuration file.
   The configuration is located in `src/main/resources/application.yml`.

### Build an executable JAR and run from command line

1. change directory to project root folder "test-app-demo"
2. run the follwoing command on cmd for windows. If maven is already installed, just use "mvn package".

```
mvnw package

```

3.  A single executable JAR file containing all the necessary dependencies, classes, and resources would be genarted in test-app-demo/target folder
4.  Change directory to '/target' folder and Use the follwoing command on cmd to run the application

    ```
    java -Dserver.port=8078 -jar test-app-demo-0.0.1-SNAPSHOT.jar

    ```

    > Please note that -Dserver.port=8078 is optional. It can be used when you want to run the applicatiom on ports other than default port.

5.  Alternatively, you can also use the below command from project root folder "test-app-demo" to run the application on default port.

```
mvnw spring-boot:run

```

### Application Testing

> 5 unit test cases have been configured to test the application.These tests run automatically when creating the executable jar file.

## Assumptions

> This program has been developed assuming that service side provides 3 integers for summation as no exception handling has been
> introduced to maintain the program simplicity.

> Please note that spring security also has not been introduced at the service endpoint for simplicity purpose.

## License

This project is licensed under the terms of the [MIT license](LICENSE).
