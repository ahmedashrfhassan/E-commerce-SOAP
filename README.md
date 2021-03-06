# JAX-WS SOAP E-Commerce Web Service
### An-E-Commerce-SOAP-built-using-JAX-WS.

## ๐ Documentation 
๐งผ[SOAP-UI requests](https://github.com/ahmedashrfhassan/E-commerce-SOAP/blob/master/E-Commerce-soap-project-requests.xml)

## ๐ฆ Features
* Content negotiation (support for both XML payloads and responses)
* SOAP 1.1

## โ Technologies used
* JAX-WS (Metro)
* JAX-B
* Maven
* Tomcat
* SoapUi

 ## ๐  Run with Maven
    **Maven**
* Change the configuration of Tomcat in `pom.xml`. 
* Deploy the application using the following maven command:
 ```
mvn clean compile tomcat7:redeploy
```
* SOAP: import the project into SOAP UI

**๐ฌMySQL**
* Create a database schema and provide the username and password in the persistence.xml
* Hibernate will automatically create the tables for you
