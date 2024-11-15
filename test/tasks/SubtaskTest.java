package tasks;

import manager.InMemoryTaskManager;
import manager.TaskManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SubtaskTest {

    private TaskManager taskManager;

    // наследники класса Task (Subtask) равны, если у них одинаковый id
    @Test
    public void testSubtaskEqualityById() {
        Subtask subtask1 = new Subtask("Подзадача1", "Описание1", 2);
        Subtask subtask2 = new Subtask("Подзадача2", "Описание2", 3);
        subtask1.setId(5);
        subtask2.setId(5);

        assertEquals(subtask1, subtask2);
    }
}
