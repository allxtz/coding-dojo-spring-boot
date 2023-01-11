Spring Boot Coding Dojo
---

Welcome to the Spring Boot Coding Dojo!

### Introduction

This is a simple application that requests its data from [OpenWeather](https://openweathermap.org/) and stores the result in a database. The current implementation has quite a few problems making it a non-production ready product.

### The task

As the new engineer leading this project, your first task is to make it production-grade, feel free to refactor any piece
necessary to achieve the goal.

### The Solution

|     | Changelog                                           |
|-----|-----------------------------------------------------|
| 1   | AOP                                                 |
| 2   | profiles for dev and prod                           |
| 3   | javadocs                                            |
| 4   | spring security - basic auth inMem                  |
| 5   | exceptionHandler                                    |
| 6   | validation - onCreate/onUpdate/pattern              |
| 7   | swagger for dev profile                             |
| 8   | IT test using profile dev                           |
| 9   | UT for service                                      |
| 10  | dockerfile config to deploy DB                      |
| 11  | dockerfile config to deploy app - prod              |
| 12  | jacoco for coverage - can be used by Sonar          |
| 13  | docker-compose file production ready deploy project |
| 14  | save log in file: app.log                           |
| 15  | update pom                                          |
| 16  | new column in table - weatherAppId                  |

### How to deploy

**build project**:
mvn clean install -Pprod

**deploy using docker**:
docker compose -f docker-compose.yml

### How to deliver the code

Please send an email containing your solution with a link to a public repository.

>**DO NOT create a Pull Request with your solution** 

### Footnote
It's possible to generate the API key going to the [OpenWeather Sign up](https://openweathermap.org/appid) page.
