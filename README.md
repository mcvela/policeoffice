# Police Office - Bandit Spring Boot - Docker - AngularJs

## Technologies

* [Spring Boot](http://projects.spring.io/spring-boot/)
* [Maven](http://maven.apache.org/)
* [Spring Security](http://projects.spring.io/spring-security/)
* [Spring Security OAuth](http://projects.spring.io/spring-security-oauth/)
* [Spring MVC REST](http://spring.io/guides/gs/rest-service/)
* [Spring Data JPA](http://projects.spring.io/spring-data-jpa/)
* [PostgreSQL](http://www.postgresql.org/) (Production, Development)
* [H2 Database Engine](http://www.h2database.com/) (Test)
* [Flyway Database Migration](http://flywaydb.org/)
* [Docker](https://www.docker.com/)
* [Docker Compose](https://docs.docker.com/compose/)
* [Gulp](http://gulpjs.com/)
* [AngularJS](https://angularjs.org/)
* [GoogleChart](https://developers.google.com/chart/interactive/docs/gallery/orgchart)

**** Dockers ***
policeoffice_bandit-nginx_1   --> Front-end (AngularJs)
policeoffice_bandit-rest_1    --> Back-end (Sprint Boot, Sprint Security, API Rest)
policeoffice_bandit-db_1      --> DB PostgreSQL

...

## Deploy

```
# Clean the project
mvn clean

# Clean all dockers
./scripts/docker_clean_all.sh

# Run all
./scripts/docker_start.sh

-------------- OR 
./start.sh

```
## Test

```
http://localhost/

User-> user
Password -> user

***** MENUS WEBSITE
Edit All Bandits --> CRUD Bandit
Jail --> Bandits in Jail
Boss --> Only Active Bosses
Band --> Treeview

```
## Example

```
* Bandit SuperCapo  level 0-> id:1 
***** Normal Bandit level 1-> id:11,12, ..
* Bandit Capo level 1-> id:10
***** Normal Bandit level 2-> id:21,22, ..
* Bandit Capo level 2-> id:20
***** Normal Bandit level 3-> id:31,32, ..




```

