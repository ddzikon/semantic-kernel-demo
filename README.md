# Semantic Kernel Demo
Example implementations of [Semantic Kernel](https://learn.microsoft.com/en-us/semantic-kernel/overview) ([Semantic Kernel repository](https://github.com/microsoft/semantic-kernel/tree/main)).

## How to run
Each demo app requires OpenAI API key in order to talk with ChatGPT.  
Provide it as an environment variable either `OPENAI_API_KEY` or `SPRING_AI_OPENAI_API_KEY` (depending on the subproject).  
Check each subproject's `README.md` file for details:  
1. [Semantic Kernel + Spring Boot - simple web app](semantic-kernel-openai-springboot-simple-web/README.md)
2. [Semantic Kernel + JavaFX - simple GUI](semantic-kernel-openai-javafx/README.md)
3. [Spring AI Function Calling - simple web app](spring-function-calling-openai/README.md)
4. [Spring AI Function Calling + JavaFX - simple GUI](spring-function-calling-openai-javafx/README.md)

**Demos 1 & 3 are rather experiments, more noteworthy are demos 2 & 4.**
## Available functions

Using demos 2 & 4 one can talk with ChatGPT and ask about:  
- providing list of all persons (from the attached database)
- get preferred weather of a particular person (by name)
- store new person in the database
- get current weather in a given location

Functions mentioned above call real endpoints, while the ones below are simulated endpoints with hardcoded data:
- finding hotel in a given city 
- getting email address for a hotel
- sending emails to the hotel's address

### Hardcoded data
Demos 2 & 4 have simple data model, contained in in-memory H2 database with following data:  

`Person`

| id | name            | preferred_weather |
|----|-----------------|-------------------|
| 1  | Thomas Anderson | Sunny             |
| 2  | Max Rockatansky | Rainy             |
| 3  | Sam Hall        | Sunny             |

`CityHotel` - used to simulate accommodation finding endpoint

| id | city_name | hotel_name         | email            |
|----|-----------|--------------------|------------------|
| 1  | Warsaw    | Neat Hotel         | neat@hotel.pl    |
| 2  | Oslo      | Kings Apartments   | contact@kings.se |
| 3  | Madrid    | City Central Hotel | contact@cch.es   |
