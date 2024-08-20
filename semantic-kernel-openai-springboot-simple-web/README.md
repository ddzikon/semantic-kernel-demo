# Semantic Kernel + Spring Boot
## How to run
Provide OpenAI API key:
```shell
export OPENAI_API_KEY=<your_api_key>
```
Navigate to project directory and run the app
```shell
../gradlew bootRun
```
Use REST API testing tool (Postman, Insomnia, etc.) or `curl` to talk with ChatGPT
```shell
curl --request POST 'localhost:8080/askGpt' \
--header 'Content-Type: text/plain' \
--data-raw 'List all persons.'
```
## REST endpoints
App exposes following endpoints:  

- GET `/hello`   
  returns string `Hello there!`
- GET `/person?name=<person_name>`  
  returns person's `info`
- POST `/askGpt`  
  sends a message to ChatGPT;  
  provide the message as a plain text request body
