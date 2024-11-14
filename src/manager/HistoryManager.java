package manager;

import tasks.Task;

import java.util.List;

public interface HistoryManager {

    // помечает задачи, как просмотренные
    void add(Task task);

    // возвращает список просмотренных задач
    List<Task> getHistory();
}
