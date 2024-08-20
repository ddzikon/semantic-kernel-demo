# Spring Boot Function Calling + OpenAI
## How to run
Provide OpenAI API key:
```shell
export SPRING_AI_OPENAI_API_KEY=<your_api_key>
```

Provide Weather API key:
```shell
export WEATHER_API_KEY=<your_api_key>
```  
This temporary API key can be used: `70ffddb5a5f14f078cc91622241807`.

Navigate to project directory and run the app:
```shell
../gradlew bootRun
```

Use REST API testing tool (Postman, Insomnia, etc.) or `curl` to talk with ChatGPT
```shell
curl --request POST 'localhost:8080/ai/chat' \
--header 'Content-Type: text/plain' \
--data-raw 'Could you tell me the current weather in Warsaw?'
```

## REST endpoints
App exposes following endpoints:
- GET `/hello`   
  returns string `Hello there!`
- GET `/persons`  
  returns the list of all persons stored in the database`
- POST `/ai/chat`  
  sends a message to ChatGPT;  
  provide the message as a plain text request body
- GET `/ai/audio?audioFilePath=<audio_file_path>`
  sends audio message to ChatGPT
  request param `audioFilePath` is an absolute path to audio file containing prompt for the chat

### Available functions
- list all person present in the database
- find info for particular person by name
- add a new person
  - person model requires values for `name` and `info` properties
  - chat asked for inserting a new person without providing the data in the same prompt could ask about these values in return (however this does not happen always)
- get current weather in a given location
