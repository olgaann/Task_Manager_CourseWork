package ru.netology.javacore;

import java.util.*;
import java.util.stream.Collectors;

public class Todos {
    static final int TASKS_LIMIT = 7;

    protected List<String> tasksList = new ArrayList<>();

    public List<String> getTaskList() {
        return tasksList;
    }

    public void addTask(String task) {
        if (tasksList.size() < TASKS_LIMIT) {
            tasksList.add(task);
        }
    }

    public void removeTask(String task) {
        tasksList.remove(task);
    }

    public String getAllTasks() {
        return tasksList.stream().sorted(String::compareToIgnoreCase).collect(Collectors.joining(" "));
    }

}
