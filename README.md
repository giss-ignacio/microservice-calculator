# Calculator service with authentication

author: Giss, Ignacio

### Docker
To run with docker run the following commands:
- ` mvn clean install `
- ` docker build --tag=app:latest . `
- ` docker-compose up `

### Swagger
To access the API UI go to the following url:
- http://localhost:8080/swagger-ui/

### Steps to try the application
1. Build and run the application
2. Go to swagger ui http://localhost:8080/swagger-ui/
3. Sign up a new user, use the example
4. Login with the same user example
5. Copy the generated Token
6. Go to the Authorize button at the top of the swagger ui screen and write `Bearer ` and paste the token
for example:  
  ```
  Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTY1ODQ5OTcyMSwiZXhwIjoxNjU4NTAzMzIxfQ.icjBu1uZKp-vAWs8UkiIf4VkCMeRKcOS1Gw2lBE8F7s
  ```
7. Then you can go to the calculator-controller API and try it out.
