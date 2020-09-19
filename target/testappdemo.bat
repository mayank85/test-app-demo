start java -Dserver.port=8078 -jar test-app-demo-0.0.1-SNAPSHOT.jar

:isStarted

set ready=false 
if %ready%=="false" goto isStarted