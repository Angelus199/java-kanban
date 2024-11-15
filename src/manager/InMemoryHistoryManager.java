package manager;

import tasks.Task;
import java.util.ArrayList;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager {
    private final List<Task> history = new ArrayList<>();
    private static final int MAX_HISTORY_SIZE = 10;

    @Override
    public void add(Task task) {
        if (task != null) {
            if (history.size() >= MAX_HISTORY_SIZE) {
                history.remove(0); // удаляем самый старый элемент
            }
            history.add(task); // добавляем новый просмотр
        }
    }

    @Override
    public List<Task> getHistory() {
        return new ArrayList<>(history);
    }
}

