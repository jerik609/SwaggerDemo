# Swagger, OpenAPI and Spring Boot (3)

## it's a bit different in spring boot 3
- https://stackoverflow.com/questions/74701738/spring-boot-3-springdoc-openapi-ui-doesnt-work
- https://springdoc.org/v2/#features
- https://mvnrepository.com/artifact/org.springdoc/springdoc-openapi-starter-webmvc-ui

- `implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.1.0")`

## Endpoints

- http://localhost:8080/v3/api-docs
- http://localhost:8080/swagger-ui/index.html

# THERE'S A REACTIVE STARTER TOO !!

## Swagger
Just look in the code for actual annotation usage.

The `OpenAPIDefinition` is the main annotation, defines global properties, is set on the "main" entrypoint, i.e.:
```
@SpringBootApplication
@OpenAPIDefinition(
    ...
)
public class SwaggerDemoApplication {
    ...
}
```

In default settings, endpoints are each in its own category, despite using the same model, that's not ideal.

We can describe the individual settings, using the `@Operation` annotation, e.g.:
```
@RestController
public class UpdateToDoItem {
    
    @PutMapping(value = "/todos/{toDoItemId}")
    @Operation(
        ...
    )
    
    ...
        
}
```

And finally, security description - both global:
```
@SecurityScheme(
		name = "BearerJWT",
		type = SecuritySchemeType.HTTP,
		scheme = "bearer",
		in = SecuritySchemeIn.COOKIE,
		bearerFormat = "JWT",
		description = "Bearer token"
)
public class SwaggerDemoApplication {
    ...
}
```
and per-endpoint:
```
@RestController
public class GetAllToDoItems {

    @GetMapping(value = "todos")
    @Operation(
            tags = {"TodoItems"}, // places the endpoint inside a tag (category)
            security = {
                    // if we want to use the globally defined authentication mechanism,
                    // otherwise we must spefcify the details
                    @SecurityRequirement(name = "BearerJWT")
            }
    )
```

## How is all this useful?

On the http://localhost:8080/v3/api-docs endpoint, we can save the provided API definition JSON file, e.g.: 
```
{"openapi":"3.0.1","info":{"title":"TodoItems API","version":"0.0.1.CAKE"},"servers":[{"url":"http://localhost:8080"},{"url":"http://jerik-the-dog.wuf:1234"}],"tags":[{"name":"TodoItems","description":"All the operations for TODOs"},{"name":"Modifiers","description":"These endpoints are used to modify the TODOs"}],"paths":{"/todos/{toDoItemId}":{"get":{"tags":["TodoItems"],"operationId":"getToDoItemById","parameters":[{"name":"toDoItemId","in":"path","required":true,"schema":{"type":"string"}}],"responses":{"200":{"description":"OK","content":{"*/*":{"schema":{"type":"object"}}}}}},"put":{"tags":["TodoItems","Modifiers"],"summary":"this is the summary","description":"this is the description","externalDocs":{"description":"UTFG! :-D","url":"https://www.google.com"},"operationId":"updateTodo","parameters":[{"name":"toDoItemId","in":"header","description":"Teh item ID.","examples":{"boo":{"description":"boo","value":123},"foo":{"description":"foo","value":99999}},"example":12314},{"name":"canEdit","in":"cookie","required":false,"schema":{"type":"string"}},{"name":"toDoItemId","in":"path","required":true,"schema":{"type":"string"}},{"name":"fromHost","in":"header","required":true,"schema":{"type":"boolean"}},{"name":"isClient","in":"query","required":true,"schema":{"type":"boolean"}}],"requestBody":{"description":"this is the request body description","content":{"application/json":{"schema":{"$ref":"#/components/schemas/FoobarModel"}}},"required":true},"responses":{"200":{"description":"All went well and things were done perfectly","content":{"text/event-stream":{"schema":{"$ref":"#/components/schemas/ToDoItem"},"examples":{"example stuff #1":{"description":"example stuff #1","value":"Object1"},"example stuff #2":{"description":"example stuff #2","value":"Object2"}}}}},"404":{"description":"Wow, this went sideways","headers":{"error details":{"description":"mode detailed error details details of the details","style":"simple","schema":{"type":"string"}}},"content":{"*/*":{"schema":{"type":"object"}}}}}},"delete":{"tags":["TodoItems","Modifiers"],"operationId":"deleteToDoItem","parameters":[{"name":"toDoItemId","in":"path","required":true,"schema":{"type":"string"}}],"responses":{"200":{"description":"OK","content":{"*/*":{"schema":{"type":"object"}}}}}}},"/todos":{"get":{"tags":["TodoItems"],"operationId":"getAllToDoItems","parameters":[{"name":"isCompleted","in":"query","required":true,"schema":{"type":"boolean"}}],"responses":{"200":{"description":"OK","content":{"*/*":{"schema":{"type":"object"}}}}},"security":[{"BearerJWT":[]}]},"post":{"tags":["TodoItems","Modifiers"],"operationId":"createToDoItem","requestBody":{"content":{"application/json":{"schema":{"$ref":"#/components/schemas/ToDoItem"}}},"required":true},"responses":{"200":{"description":"OK","content":{"*/*":{"schema":{"type":"object"}}}}}}}},"components":{"schemas":{"FoobarModel":{"type":"object","properties":{"foo":{"type":"integer","format":"int32"},"bar":{"type":"string"}}},"ToDoItem":{"type":"object","properties":{"itemId":{"type":"string"},"title":{"type":"string"},"description":{"type":"string"},"completed":{"type":"boolean"}}}},"securitySchemes":{"BearerJWT":{"type":"http","description":"Bearer token","in":"cookie","scheme":"bearer","bearerFormat":"JWT"}}}}
```
This file is agnostic of any programming language.

1. We can use this with the swagger code generation: https://swagger.io/tools/swagger-codegen/ - Using this file, we can generate code for server and/or client in various programming languages.
2. We can use this with OpenAPI compatible tools, like postman to "load" API definitions so that we can test against the API, generate queries
