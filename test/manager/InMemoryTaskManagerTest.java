package managers;

import manager.InMemoryTaskManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tasks.Epic;
import tasks.Subtask;
import tasks.Task;

import static org.junit.jupiter.api.Assertions.*;

public class InMemoryTaskManagerTest {

    private InMemoryTaskManager taskManager;

    @BeforeEach
    public void setup() {
        taskManager = new InMemoryTaskManager();
    }

    //  InMemoryTaskManager действительно добавляет задачи разного типа и может найти их по id
    @Test
    public void testInMemoryTaskManagerAddsAndFindsTasks() {
        Task task = new Task("Задача", "Описание");
        int taskId = taskManager.addNewTask(task);

        Epic epic = new Epic("Эпик", "Описание Эпика");
        int epicId = taskManager.addNewEpic(epic);

        Subtask subtask = new Subtask("Подзадача", "Описание подзадачи", epicId);
        int subtaskId = taskManager.addNewSubtask(subtask);

        assertEquals(task, taskManager.getTask(taskId), "Задача должна быть доступна для извлечения по айди");
        assertEquals(epic, taskManager.getEpic(epicId), "Эпик должен быть доступен для извлечения по айди");
        assertEquals(subtask, taskManager.getSubtask(subtaskId), "Подзадача должна быть доступна для извлечения по айди");
    }

    //задачи с заданным id и сгенерированным id не конфликтуют внутри менеджера
    @Test
    public void testTaskIdsDoNotConflict() {
        Task task1 = new Task("Задача1", "Описание1");
        int id1 = taskManager.addNewTask(task1);

        Task task2 = new Task("Задача2", "Описание2");
        task2.setId(id1);

        int result = taskManager.addNewTask(task2);

        assertEquals(-1, result, "Конфликт идентификаторов задач должен возвращать значение -1");
    }

    // проверяется неизменность задачи (по всем полям) при добавлении задачи в менеджер
    @Test
    public void testTaskImmutabilityUponAddition() {
        Task originalTask = new Task("Задача3", "Описание3");
        int generatedId = taskManager.addNewTask(originalTask);

        Task retrievedTask = taskManager.getTask(generatedId);

        assertEquals(originalTask.getTitle(), retrievedTask.getTitle());
        assertEquals(originalTask.getDescription(), retrievedTask.getDescription());
        assertEquals(originalTask.getStatus(), retrievedTask.getStatus());
    }
}
