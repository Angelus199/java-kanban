package manager;

import manager.InMemoryHistoryManager;
import manager.InMemoryTaskManager;
import manager.Managers;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ManagersTest {

    // утилитарный класс всегда возвращает проинициализированные и готовые к работе экземпляры менеджеров
    @Test
    public void testUtilityClassReturnsInitializedManagers() {
        assertNotNull(Managers.getDefault(), "TaskManager должен быть инициализирован");
        assertNotNull(Managers.getDefaultHistory(), "HistoryManager должен быть инициализирован");

        assertTrue(Managers.getDefault() instanceof InMemoryTaskManager, "TaskManager должен быть типа InMemoryTaskManager");
        assertTrue(Managers.getDefaultHistory() instanceof InMemoryHistoryManager, "HistoryManager должен быть типа InMemoryHistoryManager");
    }
}
