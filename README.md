# Vanquish-P2
Team Vanquish Project 2 Implementation

### Minimum Requirements
- [ ] Proper use of OOP principles and a well layered application structure
- [ ] Users are interfacing with an advanced UI built with Angular running in a browser
- [ ] CRUD operations are supported for one or more domain objects via the web application's API endpoints
- [ ] Client/Server communication is done with JSON in HTTP request and response bodies.
- [ ] Client/Server communication is done with JSON in HTTP request and response bodies.
- [ ] All low-level persistence logic is abstracted away with Hibernate/Spring Data
- [ ] Documentation (all classes and methods have adequate Javadoc comments)
- [ ] All Exceptions are caught and logged to a file/database
- [ ] Data useful to the application is retrieved from a third party API
- [ ] Adequate test coverage for the service-layer
- [ ] DevOps CI/CD pipeline is used to build and deploy project to a publicly available remote location


## -- 4TheMusic --

Our Music and concerts app, "4TheMusic" brings users together with their favorite artists,
while allowing concert organizers to gain insights into user trends and analytics.

Users will be able to create and share playlists of their favorite songs and artists,
along with viewing songs by similar artists and genres.

Premium users (concert organizers, artists, tour managers, etc.) will be able to browse artists and see high-level, real-time analytics on
the users that like particular artists.


### Minimum Viable Product
* As a user, I can create a playlist.
* As a user, I can add or remove songs from a playlist.
* As a user, I can rate a song with a like or dislike.
* As a user, I can look up an album and see what songs are on it.
* As a user, I can look up an artist and see a [limited] list of their songs.
* As a premium user, I can schedule a concert.
* As a premium user, I can cancel a concert.
* As a premium user, I can see a list of liked songs for a user.
* As a premium user, I can see a like/dislike ratio for a particular song.

### Bonus Stories
* As a user, I can view a list of users who like the same songs.
* As a premium user, I can see a like/dislike ratio for an entire artist.

## Tech Stack
You should be employing the following technologies in your project.
 - Java 8
 - JavaScript/TypeScript
 - HTML & CSS
 - Angular 4
 - Apache Maven for dependencies and project management
 - Git & Github for version control
 - MariaDB deployed on AWS RDS for data persistence
 - Hibernate/Spring Data to abstract away JDBC code
 - AWS EC2, ElasticBeanstalk, S3, CodeBuild, CodePipeline for CI/CD pipeline
 - Spring Boot
