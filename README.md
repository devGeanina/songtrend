# Songtrend 
Collects trends in song titles from Soundcloud API.
 
## Getting Started
Import the project into Eclipse or other preferred IDE and run it as Java Application or Spring Boot App.

### Prerequisites
The application comes with all the jars included and an embedded database, so no prerequisites are required.

## Built With
* [Maven](https://maven.apache.org/) - Dependency Management
* [Spring Boot](https://spring.io/projects/spring-boot) - Used to create the stand-alone Java application based on Spring.
* [Derby](https://db.apache.org/derby/) - Open source relational database implemented in Java, that supports JDBC and SQL standards and for this application comes in handy as it is lightweight, comes embedded and it is easily used with Hibernate.
* [Hibernate](http://hibernate.org/) - The default JPA implementation for Spring.
* [Gson](https://github.com/google/gson) - Used to serialize/deserialize the data.

## Author
* **Geanina Viorela Chiricuta**

## Additional information

Provide a REST API that returns a time series for a given word and date range. For example:
http://localhost:8080/word/?word=love&from=20161231&to=20170101

should return:
{  
   "word":"love",
   "timeSeries":[  
      {  
         "date":"20161231",
         "count":20
      },
      {  
         "date":"20170101",
         "count":19
      }
   ]
}

It should fetch data daily from the SoundCloud track API and store word counts for all words found in song titles in the database. The API is at https://api.soundcloud.com/tracks?client_id=3a792e628dbaf8054e55f6e134ebe5fa