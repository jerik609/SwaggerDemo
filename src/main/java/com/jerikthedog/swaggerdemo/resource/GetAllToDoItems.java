package com.jerikthedog.swaggerdemo.resource;

import com.jerikthedog.swaggerdemo.model.ToDoItem;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class GetAllToDoItems {

    @GetMapping(value = "todos")
    @Operation(
            tags = {"TodoItems"} // places the endpoint inside a tag (category)
    )
    public ResponseEntity<Object> getAllToDoItems(Boolean isCompleted) {
        ArrayList<ToDoItem> toDoItems = new ArrayList<>();
        toDoItems.add(new ToDoItem("4432","Item 1","Item 1 description",false));
        toDoItems.add(new ToDoItem("4434","Item 2","Item 2 description",true));
        toDoItems.add(new ToDoItem("4435","Item 3","Item 3 description",false));

        return ResponseEntity.ok().body(toDoItems);
    }

}
