Technology stack
* Backend has written using Grails and H2 database as a currency cache
* Frontend is based on React
* Basic tests for services and controller are located here server/src/test/groovy

How to deploy Currency Viewer
1. Perform the following command: gradlew assembleServerAndClient
2. java -jar .\build\server-0.1.jar
3. Grails application will be available at http://localhost:8080

The application could be run in Docker
1. gradlew assembleServerAndClient
2. docker build -t currency-viewer .
3. docker run currency-viewer -p 8080:8080

![](https://github.com/t0xx/CurrencyViewer/blob/master/pic_1.jpg)
![](https://github.com/t0xx/CurrencyViewer/blob/master/pic_2.jpg)
