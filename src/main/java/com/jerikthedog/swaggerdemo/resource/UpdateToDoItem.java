package com.jerikthedog.swaggerdemo.resource;

import com.jerikthedog.swaggerdemo.model.ToDoItem;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
// let's focus on describing this endpoint in swagger, it's the most interesting one :-)
public class UpdateToDoItem {

    @PutMapping(value = "/todos/{toDoItemId}")
    @Operation(
            tags = {"TodoItems"} // places the endpoint inside a tag (category)
    )
    public ResponseEntity<Object> updateToDoItem(@PathVariable String toDoItemId,
                                                 @RequestBody ToDoItem toDoItem,
                                                 @CookieValue(required = false) String canEdit,
                                                 @RequestHeader Boolean fromHost,
                                                 @RequestParam Boolean isClient) {

        return ResponseEntity.ok().body(toDoItem);
    }

}
