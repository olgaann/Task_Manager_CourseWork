package ru.netology.javacore;

import org.junit.jupiter.api.*;

import java.util.*;

public class TodosTests {

    static Todos testTodos;
    static String testWord = "Землетрясение";

    @BeforeEach
    void setUp() {
        testTodos = new Todos();
        for (int i = 0; i < testWord.length(); i++) {
            testTodos.addTask(String.valueOf(testWord.charAt(i)));
        }
    }


    @DisplayName("Тестирование метода addTask()")
    @Test
    void addTaskTest() {
        List<String> expectedList = Arrays.asList("З", "е", "м", "л", "е", "т", "р");
        Assertions.assertEquals(expectedList, testTodos.getTaskList());
    }


    @DisplayName("Тестирование метода removeTask()")
    @Test
    void removeTaskTest() {
        testTodos.removeTask("е");
        testTodos.removeTask("е");
        List<String> expectedList = Arrays.asList("З", "м", "л", "т", "р");
        Assertions.assertEquals(expectedList, testTodos.getTaskList());
    }


    @DisplayName("Тестирование метода getAllTasks()")
    @Test
    void getAllTaskTest() {
        String expected = "е е З л м р т";
        Assertions.assertEquals(expected, testTodos.getAllTasks());
    }


    @DisplayName("Тестирование метода restoreLastAction()")
    @Test
    void restoreLastActionTest() {
        testTodos.restoreLastAction();
        testTodos.restoreLastAction();
        testTodos.restoreLastAction();
        testTodos.restoreLastAction();
        String expected = "е З м";

        Assertions.assertEquals(expected, testTodos.getAllTasks());
    }
}
