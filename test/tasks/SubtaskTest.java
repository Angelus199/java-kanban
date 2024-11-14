package tasks;

import manager.InMemoryTaskManager;
import manager.TaskManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SubtaskTest {

    private TaskManager taskManager;

    @BeforeEach
    public void setup() {
        taskManager = new InMemoryTaskManager();
    }


    // наследники класса Task (Subtask) равны, если у них одинаковый id
    @Test
    public void testSubtaskEqualityById() {
        Subtask subtask1 = new Subtask("Подзадача1", "Описание1", 2);
        Subtask subtask2 = new Subtask("Подзадача2", "Описание2", 3);
        subtask1.setId(5);
        subtask2.setId(5);

        assertEquals(subtask1, subtask2);
    }

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
