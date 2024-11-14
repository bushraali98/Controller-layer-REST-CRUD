package com.example.tasktracker.Controller;

import com.example.tasktracker.ApiResponse.ApiResponse;
import org.springframework.web.bind.annotation.*;
import com.example.tasktracker.Model.Task;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/task")
public class TaskController {

    ArrayList<Task> tasks = new ArrayList<>();

    @GetMapping("/get")
    public ArrayList<Task> getTodos(){
        return tasks;
    }

    @PostMapping("/add")
    public ApiResponse addTodo(@RequestBody Task task) {
        tasks.add(task);
        return new ApiResponse("Task added", "200");
    }

    @PutMapping("/update/{index}")
    public ApiResponse updateTask(@PathVariable int index, @RequestBody Task task) {
        tasks.set(index, task);
        return new ApiResponse("Task updated", "200");
    }

    @DeleteMapping("/delete/{index}")
    public ApiResponse deleteTask(@PathVariable int index) {
        tasks.remove(index);
        return new ApiResponse("Task deleted", "200");
    }

    @PutMapping("change-status/{index}")
    public ApiResponse changeStatus(@PathVariable int index) {
        for (int i = 0; i < tasks.size(); i++) {
            if (i == index) {
                Task task = tasks.get(i);
                if("not done".equals(task.getStatus())) {
                    task.setStatus("done");
                    return new ApiResponse("Task updated", "200");
                } else return new ApiResponse("Task already done", "400");
            }
        } return new ApiResponse("Task not found!", "404");
    }

    @GetMapping("/search/{keyword}")
    public ApiResponse search(@PathVariable String keyword) {
        for (Task task : tasks) {
            if (task.getTitle().contains(keyword)) {
                return new ApiResponse("Task found", "200");
            }
        }
        return new ApiResponse("Task not found", "404");
    }

}
