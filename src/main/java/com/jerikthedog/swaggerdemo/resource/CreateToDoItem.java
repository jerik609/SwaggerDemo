package com.jerikthedog.swaggerdemo.resource;

import com.jerikthedog.swaggerdemo.model.ToDoItem;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreateToDoItem {

    @PostMapping(value = "/todos")
    @Operation(
            tags = {"TodoItems", "Modifiers"} // places the endpoint inside a tag (category)
    )
    public ResponseEntity<Object> createToDoItem(@RequestBody ToDoItem toDoItem) {
        return ResponseEntity.ok().body(toDoItem);
    }

}