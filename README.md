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
## Available functions
First three demo apps have simple data model, contained in-memory H2 database with following data:  
Table `Person`

| id | name    | info     |
| -- | ------- | -------- |
| 1  | Areczek | Intern   |
| 2  | Miro    | Handlarz |

One can talk with ChatGPT and ask about:
- providing list of all persons
- get info for particular person (by name)
- store new person in the database
- current weather in a given location
