#Link Converter Application:
* JAVA 11
* Spring Boot 2.6.0
* H2 Database
* Maven

##Request Endpoins: 
* "localhost:8080/urlToDeepLink" : web url to deep link
* "localhost:8080/deepLinkToUrl" : deep link to web url

Note: Request and response bodies have following json structure.
{
    "link": "linkToBeConverted or convertedLink"
}

##Docker Run Commands:
In order to run the application using Docker, following docker commands can be used:
* docker build -t link-converter .
* docker run -d -p 8080:8080 link-converter