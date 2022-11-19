package ru.netology.javacore;

import java.util.*;
import java.util.stream.Collectors;

public class Todos {
    static final int TASKS_LIMIT = 7;

    protected List<String> tasksList = new ArrayList<>();
    protected Deque<Request> actionsStack = new LinkedList<>();

    public List<String> getTaskList() {
        return tasksList;
    }


    public boolean isAbleToAdd(String task) {

        return (tasksList.size() < TASKS_LIMIT && task != null);
    }

    public void addTask(String task) {
        if (isAbleToAdd(task)) {                                  //если можно добавить задачу
            tasksList.add(task);                                  //добавляем задачу
            actionsStack.push(new Request(Type.ADD, task));       //кладем операцию в стэк операций
        }
    }

    public boolean isAbleToRemove(String task) {
        return tasksList.contains(task);
    }

    public void removeTask(String task) {
        if (isAbleToRemove(task)) {                                 //если есть, что удалить
            tasksList.remove(task);                                 //удаляем задачу
            actionsStack.push(new Request(Type.REMOVE, task));      //кладем операцию в стэк операций
        }
    }

    public String getAllTasks() {
        return tasksList.stream().sorted(String::compareToIgnoreCase).collect(Collectors.joining(" "));
    }

    public void restoreLastAction() {

        if (!actionsStack.isEmpty()) {
            Request last = actionsStack.pop();
            if ((last.getType() == Type.ADD) && this.isAbleToRemove(last.getTask())) {
                tasksList.remove(last.getTask());
            } else if ((last.getType() == Type.REMOVE) && this.isAbleToAdd(last.getTask())) {
                tasksList.add(last.getTask());
            }
        }

    }

}
