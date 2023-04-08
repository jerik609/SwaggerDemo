package com.jerikthedog.swaggerdemo.resource;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeleteToDoItem {

    @DeleteMapping(value = "/todos/{toDoItemId}")
    @Operation(
            tags = {"TodoItems"} // places the endpoint inside a tag (category)
    )
    public ResponseEntity<Object> deleteToDoItem(@PathVariable String toDoItemId) {
        return ResponseEntity.ok().body("ToDo Item Deleted");
    }

}
