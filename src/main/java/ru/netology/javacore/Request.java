package ru.netology.javacore;

public class Request {
    protected Type type;
    protected String task;

    public Request(Type type, String task) {
        this.type = type;
        this.task = task;
    }

    public Request(Type type) {
        this.type = type;
    }

    public String getTask() {
        return task;
    }

    public Type getType() {
        return type;
    }

    @Override
    public String toString() {
        return type + " " + task;
    }
}
