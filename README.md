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
