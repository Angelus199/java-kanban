package tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EpicTest {

    // объекты Epic равны, если у них одинаковые id
    @Test
    public void testEpicEqualityById() {
        Epic epic1 = new Epic("Эпик1", "Описание1");
        Epic epic2 = new Epic("Эпик2", "Описание2");
        epic1.setId(3);
        epic2.setId(3);

        assertEquals(epic1, epic2);
    }

    // объект Epic нельзя добавить в самого себя в виде подзадачи
    @Test
    public void testEpicDoesNotAllowSelfAsSubtask() {
        Epic epic = new Epic("Эпик", "Описание");
        boolean result = epic.addSubtask(epic.getId()); // попытка добавить себя

        assertFalse(result, "Объект Epic не должен добавлять самого себя в виде подзадачи");
        assertTrue(epic.getSubtaskIds().isEmpty(), "Список подзадач должен быть пустым");
    }
}

