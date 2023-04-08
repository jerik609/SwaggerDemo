package com.jerikthedog.swaggerdemo.resource;

import com.jerikthedog.swaggerdemo.model.FoobarModel;
import com.jerikthedog.swaggerdemo.model.ToDoItem;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// https://stackoverflow.com/questions/22812365/how-to-hide-a-parameter-in-swagger

@RestController
// let's focus on describing this endpoint in swagger, it's the most interesting one :-)
public class UpdateToDoItem {

    @PutMapping(value = "/todos/{toDoItemId}")
    @Operation(
            tags = {"TodoItems", "Modifiers"}, // places the endpoint inside a tag (category)
            operationId = "updateTodo", // a unique operation ID, useful for callbacks
            summary = "this is the summary", // appearing next to the endpoint (short blabla)
            description = "this is the description", // available when expanding it, more detailed info
            // now to the passed variables
            // COMMON MISTAKE: !!! make sure to use the swagger v3 annotation !!!
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = false, // does not work?
                    description = "this is the request body description",
                    // here we can override the content which is inferred by swagger normally
                    content = @Content(
                            schema = @Schema(
                                    implementation = FoobarModel.class
                            )
                    )
            ),
            // other parameters
            parameters = {
                    @Parameter(
                            // name must match the parameter name
                            name = "toDoItemId",
                            description = "Teh item ID.",
                            example = "12314", // prefilled variable
                            examples = { // even a list of prefilled variables
                                    @ExampleObject(name = "boo", value = "123"),
                                    @ExampleObject(name = "foo", value = "99999")
                            },
                            // we can change the parameter "origin" (otherwise it's inferred)
                            in = ParameterIn.HEADER,
                            hidden = false
                    ),
                    @Parameter(
                            name = "canEdit",
                            // what does this do???
                            hidden = false
                    )
                    // same for all other parameters
            },
            hidden = false, // hides the whole endpoint
            externalDocs = @ExternalDocumentation(
                    url = "https://www.google.com",
                    description = "UTFG! :-D"
            ),
            // additionally to request details, we can define responses too
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "All went well and things were done perfectly",
                            content = {
                                    @Content(
                                            schema = @Schema(
                                                    implementation = ToDoItem.class
                                            ),
                                            mediaType = MediaType.TEXT_EVENT_STREAM_VALUE,
                                            examples = {
                                                    @ExampleObject(name = "example stuff #1", value = "Object1"),
                                                    @ExampleObject(name = "example stuff #2", value = "Object2"),
                                            }
                                    )
                            }
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Wow, this went sideways",
                            headers = {
                                    @Header(
                                            name = "error details",
                                            description = "mode detailed error details details of the details"
                                    )
                            }
                    )
            }
    )
    public ResponseEntity<Object> updateToDoItem(@PathVariable String toDoItemId,
                                                 // this tells swagger that this is the data model we're using, and it will
                                                 // be automatically displayed on the swagger web page
                                                 @RequestBody ToDoItem toDoItem,
                                                 @CookieValue(required = false) String canEdit,
                                                 @RequestHeader Boolean fromHost,
                                                 @RequestParam Boolean isClient) {

        return ResponseEntity.ok().body(toDoItem);
    }

}
