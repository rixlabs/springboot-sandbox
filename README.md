# springboot-sandbox #

a little sandbox for an easy start playing with spring boot features

## Build and startup ##

From command line you can do 

    mvn clean package

this will create the standard spring boot jar inside the **target** direcotry

You can start up the sand box in different ways

As a standalone jar

    java -jar springboot-sandbox-0.0.1-SNAPSHOT

With a maven (thanks to the **spring-boot-maven-plugin**)

    mvn spring-boot:run


Running the main of the SpringbootSandboxApplication class (with an IDE)

This project include the **spring-boot-devtools** and is ready for the auto reload even with maven.

    <build>
    	<plugins>
    		<plugin>
    			<groupId>org.springframework.boot</groupId>
    			<artifactId>spring-boot-maven-plugin</artifactId>
    			<configuration>
    				<fork>true</fork>
    			</configuration>
    		</plugin>
    	</plugins>
    </build>

You need the fork in the plugin

The autoreload is triggered when the files in the classpath change(needs a build -> CTRL+F9 in intellij)

## Testing the app ##

Handles and behaviour

### Endpoints ###

/greeting

POST Some data to the H2 db
```
    curl -v -H "Accept: application/json" -H "Content-type: application/json" -X POST -d '{  "firstName" : "Frodo",  "lastName" : "Baggins" }'  http://localhost:8080/people
```

### Security ###

For test purpose there is some security implemented as basic http 

you can access with

**username**: poldo

**password**: poldone

You can change it in the SpringbootSandboxApplication class

### Health check ###
You can check the status of services at /health
Actually is just the basic check of Actuator


## Usefull resources ##

a list of link that I used to better understand spring boot and some other little things

- http://www.programcreek.com/java-api-examples/index.php?api=org.springframework.boot.CommandLineRunner
- https://github.com/spring-guides/tut-bookmarks/issues/10
- https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html#approach6
- https://www.youtube.com/watch?v=CBwlgvfllNk
- https://github.com/joshlong
- https://spring.io/understanding/HATEOAS 
- http://presos.dsyer.com/decks/microservice-security.html




