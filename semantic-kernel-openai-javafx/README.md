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
Chat should react to prompts asking for:
- list all persons
- insert a new person
  - person model requires values for `name` and `preferred_weather` properties
  - chat asked for inserting a new person without providing the data in the same prompt could ask about these values in return (however this does not happen always)
- find preferred weather of particular person by name
- get current weather in a given location
- find a hotel in a given city (hardcoded data)
- find hotel's email address (hardcoded data)
- send email to the hotel (hardcoded data)
