#!/bin/bash
echo $@
mvn clean install -DskipTests
java -D$@ -jar target/spring-boot-complete-0.0.1-SNAPSHOT.jar 
