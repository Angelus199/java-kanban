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

    @Test
    void getTask() {
        Task task = new Task("Задача", "Описание");
        int id = taskManager.addNewTask(task);
        assertEquals(task, taskManager.getTask(id), "Должна вернуть ту же задачу по ID");
    }

    @Test
    void getSubtask() {
        Epic epic = new Epic("Эпик", "Описание Эпика");
        int epicId = taskManager.addNewEpic(epic);
        Subtask subtask = new Subtask("Подзадача", "Описание подзадачи", epicId);
        int subtaskId = taskManager.addNewSubtask(subtask);
        assertEquals(subtask, taskManager.getSubtask(subtaskId), "Должна вернуть ту же подзадачу по ID");
    }

    @Test
    void getEpic() {
        Epic epic = new Epic("Эпик", "Описание Эпика");
        int id = taskManager.addNewEpic(epic);
        assertEquals(epic, taskManager.getEpic(id), "Должен вернуть тот же эпик по ID");
    }

    @Test
    void getTasks() {
        Task task1 = new Task("Задача1", "Описание1");
        Task task2 = new Task("Задача2", "Описание2");
        taskManager.addNewTask(task1);
        taskManager.addNewTask(task2);
        assertEquals(2, taskManager.getTasks().size(), "Должно быть две задачи");
    }

    @Test
    void getSubtasks() {
        Epic epic = new Epic("Эпик", "Описание Эпика");
        int epicId = taskManager.addNewEpic(epic);
        Subtask subtask1 = new Subtask("Подзадача1", "Описание подзадачи1", epicId);
        Subtask subtask2 = new Subtask("Подзадача2", "Описание подзадачи2", epicId);
        taskManager.addNewSubtask(subtask1);
        taskManager.addNewSubtask(subtask2);
        assertEquals(2, taskManager.getSubtasks().size(), "Должно быть две подзадачи");
    }

    @Test
    void getEpics() {
        Epic epic1 = new Epic("Эпик1", "Описание Эпика1");
        Epic epic2 = new Epic("Эпик2", "Описание Эпика2");
        taskManager.addNewEpic(epic1);
        taskManager.addNewEpic(epic2);
        assertEquals(2, taskManager.getEpics().size(), "Должно быть два эпика");
    }

    @Test
    void getEpicSubtasks() {
        Epic epic = new Epic("Эпик", "Описание Эпика");
        int epicId = taskManager.addNewEpic(epic);
        Subtask subtask1 = new Subtask("Подзадача1", "Описание подзадачи1", epicId);
        Subtask subtask2 = new Subtask("Подзадача2", "Описание подзадачи2", epicId);
        taskManager.addNewSubtask(subtask1);
        taskManager.addNewSubtask(subtask2);
        assertEquals(2, taskManager.getEpicSubtasks(epicId).size(), "Должно быть две подзадачи у эпика");
    }

    @Test
    void addNewTask() {
        Task task = new Task("Задача", "Описание");
        int taskId = taskManager.addNewTask(task);
        assertNotEquals(-1, taskId, "Должен вернуться уникальный ID");
    }

    @Test
    void addNewEpic() {
        Epic epic = new Epic("Эпик", "Описание Эпика");
        int epicId = taskManager.addNewEpic(epic);
        assertNotEquals(-1, epicId, "Должен вернуться уникальный ID");
    }

    @Test
    void addNewSubtask() {
        Epic epic = new Epic("Эпик", "Описание Эпика");
        int epicId = taskManager.addNewEpic(epic);
        Subtask subtask = new Subtask("Новая подзадача", "Описание подзадачи", epicId);
        Integer subtaskId = taskManager.addNewSubtask(subtask);
        assertNotNull(subtaskId, "Должен вернуться уникальный ID");
    }

    @Test
    void updateTask() {
        Task task = new Task("Старая задача", "Описание");
        int taskId = taskManager.addNewTask(task);
        Task updatedTask = new Task("Обновленная задача", "Новое описание");
        updatedTask.setId(taskId);
        taskManager.updateTask(updatedTask);
        assertEquals("Обновленная задача", taskManager.getTask(taskId).getTitle(), "Имя задачи должно быть обновлено");
    }

    @Test
    void updateEpic() {
        Epic epic = new Epic("Старый эпик", "Описание Эпика");
        int epicId = taskManager.addNewEpic(epic);
        Epic updatedEpic = new Epic("Обновленный эпик", "Новое описание Эпика");
        updatedEpic.setId(epicId);
        taskManager.updateEpic(updatedEpic);
        assertEquals("Обновленный эпик", taskManager.getEpic(epicId).getTitle(), "Имя эпика должно быть обновлено");
    }

    @Test
    void updateSubtask() {
        Epic epic = new Epic("Эпик", "Описание Эпика");
        int epicId = taskManager.addNewEpic(epic);
        Subtask subtask = new Subtask("Старая подзадача", "Описание подзадачи", epicId);
        int subtaskId = taskManager.addNewSubtask(subtask);
        Subtask updatedSubtask = new Subtask("Обновленная подзадача", "Новое описание подзадачи", epicId);
        updatedSubtask.setId(subtaskId);
        taskManager.updateSubtask(updatedSubtask);
        assertEquals("Обновленная подзадача", taskManager.getSubtask(subtaskId).getTitle(), "Имя подзадачи должно быть обновлено");
    }

    @Test
    void deleteTask() {
        Task task = new Task("Задача для удаления", "Описание");
        int id = taskManager.addNewTask(task);
        taskManager.deleteTask(id);
        assertNull(taskManager.getTask(id), "Задача должна быть удалена");
    }

    @Test
    void deleteEpic() {
        Epic epic = new Epic("Эпик для удаления", "Описание Эпика");
        int epicId = taskManager.addNewEpic(epic);
        Subtask subtask = new Subtask("Подзадача", "Описание подзадачи", epicId);
        taskManager.addNewSubtask(subtask);
        taskManager.deleteEpic(epicId);
        assertNull(taskManager.getEpic(epicId), "Эпик должен быть удален");
        assertTrue(taskManager.getEpicSubtasks(epicId).isEmpty(), "Все подзадачи эпика должны быть удалены");
    }

    @Test
    void deleteSubtask() {
        Epic epic = new Epic("Эпик для подзадачи", "Описание Эпика");
        int epicId = taskManager.addNewEpic(epic);
        Subtask subtask = new Subtask("Подзадача для удаления", "Описание подзадачи", epicId);
        int subtaskId = taskManager.addNewSubtask(subtask);
        taskManager.deleteSubtask(subtaskId);
        assertNull(taskManager.getSubtask(subtaskId), "Подзадача должна быть удалена");
    }

    @Test
    void deleteTasks() {
        taskManager.addNewTask(new Task("Задача1", "Описание1"));
        taskManager.addNewTask(new Task("Задача2", "Описание2"));
        taskManager.deleteTasks();
        assertTrue(taskManager.getTasks().isEmpty(), "Все задачи должны быть удалены");
    }

    @Test
    void deleteSubtasks() {
        Epic epic = new Epic("Эпик для подзадач", "Описание Эпика");
        int epicId = taskManager.addNewEpic(epic);
        taskManager.addNewSubtask(new Subtask("Подзадача1", "Описание подзадачи1", epicId));
        taskManager.addNewSubtask(new Subtask("Подзадача2", "Описание подзадачи2", epicId));
        taskManager.deleteSubtasks();
        assertTrue(taskManager.getSubtasks().isEmpty(), "Все подзадачи должны быть удалены");
    }

    @Test
    void deleteEpics() {
        taskManager.addNewEpic(new Epic("Эпик1", "Описание Эпика1"));
        taskManager.addNewEpic(new Epic("Эпик2", "Описание Эпика2"));
        taskManager.deleteEpics();
        assertTrue(taskManager.getEpics().isEmpty(), "Все эпики должны быть удалены");
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

    // todo перенесла тест из Subtask для того, чтобы не внедрять в него таск менеджер

    //подзадачи не могут ссылаться на самих себя через epicId
    @Test
    public void testSubtaskCannotBeItsOwnEpic() {
        Epic epic = new Epic("Эпик", "Описание Эпика");
        int epicId = taskManager.addNewEpic(epic);

        // создаем подзадачу, которая пытается ссылаться на свой epicId
        Subtask subtask = new Subtask("Подзадача", "Описание подзадачи", epicId);
        subtask.setId(epicId); // намеренно устанавливаем, хотя обычно manager назначает ID

        // проверяем, что подзадача не была добавлена
        Integer subtaskId = taskManager.addNewSubtask(subtask);

        // проверяем, что subtaskId равен null, указывая на отказ в добавлении
        assertNull(subtaskId, "Подзадача не должна ссылаться на саму себя как на эпик");
    }
}

