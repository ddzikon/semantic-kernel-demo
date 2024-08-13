# Semantic Kernel + simple JavaFX GUI
## How to run
Provide OpenAI API key as environment variable:
```shell
export OPENAI_API_KEY=<your_api_key>
```  
Provide [WeatherAPI](https://www.weatherapi.com/) key as environment variable:
```shell
export WEATHER_API_KEY=<your_api_key>
```  
This temporary API key can be used: `70ffddb5a5f14f078cc91622241807`.

Navigate to project directory and run the app:
```shell
../gradlew run
```
## How to use GUI
- right side panel corresponds to the state of `Person` table in the database
- left side panel contains chat history
- bottom text field allows to enter a command for ChatGPT
- type the command and click **Send** button

### Available functions
Chat should react to instructions telling to:
- list all persons
- insert a new person
- find info for particular person by name
- get current weather in a given location
