# Semantic Kernel & Spring Function Calling: AI assistant suited to your needs is now easier than ever.
Artificial Intelligence (AI) is doing an upheaval in the technology world. From its first appearance, it has evolved from simplistic algorithms and rule-based systems, to services generating poetry, composing music, and even assisting in complex problem-solving. Some time ago we've witnessed a rise of conversational agents like ChatGPT. Such agents gained a lot of interest and were / are integrated with existing services, automating many areas of interaction with software. These breakthroughs are paving the way for even more sophisticated applications, promising a future where AI seamlessly integrates into various aspects of our lives. And we can boldly say - the future is now.

**What brings the future to the present?**

As AI technology advances, new tools are emerging to help developers integrate these capabilities into their applications. One such tool is **Semantic Kernel**, designed to facilitate seamless integration between AI models and code. Similarly, **Spring Function Calling** — offered by the **Spring AI** module — provides a comparable approach, enabling developers to build and manage AI-driven services with ease. Both Semantic Kernel and Spring Function Calling represent a new wave of tools that simplify harnessing AI's potential, each with its own unique strengths and features. As we explore these innovations, it’s worth considering how they could transform the way we develop and interact with AI-powered applications.

**We've already seen many bold claims of this kind, yet almost none of them seemed to change our lives that much. What makes you think this time it would be different?**

While it's true that many technological advancements have promised to be game-changers, Semantic Kernel and Spring Function Calling stand out by addressing critical gaps in AI integration with a focus on ease of use and minimal setup effort. These tools offer versatile frameworks that simplify the connection between AI models and existing codebases, making advanced AI functionalities more accessible. Developers can quickly set up AI agents within their systems with next to no effort, and the ability to use locally hosted models provides flexibility for those who prefer not to rely on corporate models. Both tools come with vendor assurances of being future-proof and offer vast possibilities for chaining operations, enabling more complex and sophisticated AI-driven workflows. 

// REMOVE THIS PARAGRAPH VVVVV
I've recently stumbled upon tools that allowed me to freely integrate an AI model with the code I wrote. This opens an abundance of possibilities. You can make computer do anything you want by writing a respective program and now, you can ask AI to execute those programs. This allows to remove one of the steps of interaction between human and computer. One does not need to look for a particular service , one does not need to  - one can simply ask for particular actions and an AI agent will execute it. 

**Sounds promising, could you walk us through?**

Absolutely! Let's dive into the specifics.

//TODO find a way to add captions

![Example app - listing records from DB](javafx_list_all.jpg)

![Example app - adding new record to DB](javafx_add_new.png)

Both tools work on a similar concept of AI agents (although only in case of Semantic Kernel this exact term is used). 

## What is it?
Semantic Kernel is an SDK designed to enable the development of AI-based agents. These agents leverage AI models to perform a wide range of tasks, including chatbots, copilots, and fully autonomous agents.
### What are the Different Types of AI Agents?
- **Chatbots**: Answer questions in a chat-based interface, typically grounded in specific data like company documents.
- **Copilots**: Assist users in tasks such as writing emails or creating office documents by providing recommendations.
- **Fully Autonomous Agents**: Handle repetitive tasks autonomously, without user intervention.
### How Do You Build Your First Agent?
An agent in Semantic Kernel is composed of three core building blocks:
1. **Plugins**: Provide skills to the agent.
2. **Planners**: Generate plans to complete tasks.
3. **Personas**: Define the agent's behavior and interaction style.
#### Example of Building a Copilot
To create a copilot that helps write and send emails, follow these steps:
1. Get the user’s email address and name.
2. Get the recipient’s email address.
3. Get the topic of the email.
4. Generate the subject and body of the email.
5. Review the email with the user.
6. Send the email.
## Understanding Plugins
### What is a Plugin?
Plugins encapsulate existing APIs into a collection that can be used by an AI. This allows the AI to perform actions beyond its inherent capabilities.
### What is the Anatomy of a Plugin?
A plugin is a group of functions exposed to AI apps and services. These functions can be orchestrated by an AI application to accomplish user requests. Plugins need to provide semantic descriptions of their behavior for the AI to understand and correctly call the functions.
### How Do You Define a Plugin?
A plugin is defined by creating a class and annotating its methods with the `KernelFunction` attribute. This lets Semantic Kernel know that this is a function that can be called by an AI or referenced in a prompt.
### How Do You Add a Plugin to Your Kernel?
Once you have defined your plugin, you can add it to your kernel by creating a new instance of the plugin and adding it to the kernel’s plugin collection.
### How Do You Invoke a Plugin's Functions?
The AI can invoke your plugin’s functions by using function calling. This involves creating a kernel with the necessary services, adding the plugin to the kernel, and then using the chat completion service to interact with the plugin.
## Understanding the Kernel
### What is the Kernel?
The kernel is the central component that manages all services and plugins required for your AI application. It acts as a Dependency Injection container, ensuring seamless integration and monitoring of AI agents.
### How Do You Create a Kernel?
To create a kernel, you import the necessary packages, add services and plugins, and build the kernel. This involves setting up logging, adding AI services, and integrating plugins.
### How Do You Use Dependency Injection with the Kernel?
In C#, you can use Dependency Injection to create a kernel by setting up a `ServiceCollection` and adding services and plugins to it. This allows for easy configuration and management of the kernel and its components.
## What is a Planner?
### How Do Planners Work?
Planners generate plans to complete tasks using the built-in capabilities of Large Language Models (LLMs). They allow the agent to iteratively work through steps to achieve a goal, making use of available plugins and functions.
### How Do You Integrate Planning in C#?
To integrate planning, you create a kernel with the necessary plugins, enable planning settings, and create a chat history. The planner uses this history to generate responses and complete tasks based on user input.
## What is a Persona?
### How Do Personas Define Agent Behavior?
Personas define how the agent behaves and interacts with users. They set the tone and rules for the agent’s responses, ensuring consistent and appropriate interactions.
### How Do You Create and Use Personas?
To create a persona, you define the agent’s behavior in the chat history. This involves specifying the persona’s characteristics and instructions. The agent then uses this persona to guide its interactions with users.
## Conclusion
Semantic Kernel provides a robust framework for developing AI-based agents with capabilities ranging from simple chatbots to complex autonomous systems. By leveraging plugins, planners, and personas, developers can create highly functional and interactive AI agents tailored to specific tasks and user needs.