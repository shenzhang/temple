## Instructions

### 1. Make sure you have installed JDK before building this project. The version should be 1.6 or 1.7

### 2. Comment the 'maven' line in 'build.gradle' if you don't have private nexus repo, which will make gradle go to central maven repository.

### 3. execute 'gradlew deploy' in the root path of this project, you'll find temple.x.x.x.war in 'build/deploy' directory. You could only copy and replace the temple.war file with the latest one. (keep the existing temple.properties file as it is)

### 4. execute 'gradlew jettyRun' to run the project locally, it will connect the local temple database with credential 'twer/123'. If the credential you use is different, please change it in 'deploy/temple.properties'

