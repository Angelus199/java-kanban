package managers;

import manager.InMemoryHistoryManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tasks.Task;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class InMemoryHistoryManagerTest {

    private InMemoryHistoryManager historyManager;

    @BeforeEach
    public void setup() {
        historyManager = new InMemoryHistoryManager();
    }

    // задачи, добавляемые в HistoryManager, сохраняют предыдущую версию задачи и её данных
    @Test
    public void testHistoryManagerPreservesTaskData() {
        Task task = new Task("Задача", "Описание");
        historyManager.add(task);

        List<Task> history = historyManager.getHistory();

        assertFalse(history.isEmpty(), "История должна содержать задачу");
        Task taskFromHistory = history.get(0);

        assertEquals(task.getTitle(), taskFromHistory.getTitle(), "Название в истории должно соответствовать названию задачи");
        assertEquals(task.getDescription(), taskFromHistory.getDescription(), "Описание в истории должно соответствовать описанию задачи");
        assertEquals(task.getStatus(), taskFromHistory.getStatus(), "Статус в истории должен соответствовать статусу задачи");
    }
}
