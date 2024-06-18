# Semantic Kernel Demo
Example implementations of [Semantic Kernel](https://learn.microsoft.com/en-us/semantic-kernel/overview) ([Semantic Kernel repository](https://github.com/microsoft/semantic-kernel/tree/main)).
## How to run
Each demo app requires OpenAI API key in order to talk with ChatGPT.  
Provide it as an environment variable `OPENAI_API_KEY`.
1. [Semantic Kernel + Spring Boot](semantic-kernel-openai-springboot-simple-web/README.md)
2. [Semantic Kernel + simple JavaFX GUI](semantic-kernel-openai-javafx/README.md)
3. [Spring AI Functions]()
## Available functions
Each demo app has simple, in-memory H2 database with following data:  
Table `Person`

| id | name    | info     |
| -- | ------- | -------- |
| 1  | Areczek | Intern   |
| 2  | Miro    | Handlarz |

One can talk with ChatGPT and ask about:
- providing list of all persons
- get info for particular person (by name)
- store new person in the database