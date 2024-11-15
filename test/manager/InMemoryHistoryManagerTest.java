package managers;

import manager.InMemoryHistoryManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tasks.Task;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class InMemoryHistoryManagerTest {

    private static final int MAX_HISTORY_SIZE = 10;
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

    //todo добавлен тест, проверяющий, что количество элементов в истории не может превышать 10

    // проверка ограничения кол-ва задач в истории
    @Test
    public void testHistoryManagerLimit() {
        for (int i = 0; i < 11; i++) {
            Task task = new Task("Задача " + i, "Описание " + i);
            historyManager.add(task);
        }

        List<Task> history = historyManager.getHistory();

        assertEquals(MAX_HISTORY_SIZE, history.size(), "История должна содержать максимум 10 задач");


        Task firstTaskInHistory = history.get(0);
        assertEquals("Задача 1", firstTaskInHistory.getTitle(), "Название первой задачи в истории должно быть 'Задача 1'");

        Task lastTaskInHistory = history.get(9);
        assertEquals("Задача 10", lastTaskInHistory.getTitle(), "Название последней задачи в истории должно быть 'Задача 10'");

    }
}
