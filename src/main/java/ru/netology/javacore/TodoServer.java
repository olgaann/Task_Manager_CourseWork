package ru.netology.javacore;

import com.google.gson.Gson;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class TodoServer {
    static Gson gson = new Gson();
    static Request request;
    private int port = 8989;
    private Todos todos;


    public TodoServer(int port, Todos todos) {
        this.port = port;
        this.todos = todos;
    }

    public void start() throws IOException {

        try (ServerSocket serverSocket = new ServerSocket(port);) {
            System.out.println("Starting server at " + port + "...");
            while (true) {
                try (
                        Socket socket = serverSocket.accept();
                        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        PrintWriter out = new PrintWriter(socket.getOutputStream());
                ) {
                    // обработка одного подключения
                    String jsonString = in.readLine();
                    request = gson.fromJson(jsonString, Request.class);

                    switch (request.getType()) {
                        case ADD:
                            todos.addTask(request.getTask());
                            break;
                        case REMOVE:
                            todos.removeTask(request.getTask());
                            break;
                    }

                    out.println(todos.getAllTasks());

                }
            }
        } catch (IOException e) {
            System.out.println("Не могу стартовать сервер");
            e.printStackTrace();
        }
    }
}
