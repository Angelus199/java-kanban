package manager;

import tasks.Epic;
import tasks.Subtask;
import tasks.Task;

import java.util.List;

public interface TaskManager {
    // возвращает список всех задач из tasks
    List<Task> getTasks();

    // возвращает список всех подзадач из subtasks
    List<Subtask> getSubtasks();

    // возвращает список всех эпиков из epics
    List<Epic> getEpics();

    // возвращает список подзадач, которые принадлежат определенному эпику по айди
    List<Subtask> getEpicSubtasks(int epicId);

    //возвращает копию списка, содержащего последние 10 просмотренных задач
    List<Task> getHistory();

    // возвращает задачу по указанному айди
    Task getTask(int id);

    // возвращает эпик по указанному айди
    Epic getEpic(int id);

    // возвращает подзадачу по указанному айди
    Subtask getSubtask(int id);

    // добавляет новую задачу в список, генерируя для нее уникальный айди и возвращает этот айди
    int addNewTask(Task task);

    // добавляет новый эпик в список, генерируя для нее уникальный айди и возвращает этот айди
    int addNewEpic(Epic epic);

    // добавляет новую подзадачу
    // проверяет существование эпика, к которому относится подзадача и обновляет список подзадач эпика
    // возвращает уникальный айди новой подзадачи
    Integer addNewSubtask(Subtask subtask);


    // обновляет задачу, если она есть в tasks
    void updateTask(Task task);

    // обновляет существующий эпик, а также обновляет его статус на основе текущего состояния подзадач
    void updateEpic(Epic epic);

    // обновляет подзадачу, а также обновляет статус связанного эпика
    void updateSubtask(Subtask subtask);

    // удаляет задачу по айди
    void deleteTask(int id);

    // удаляет эпик и связанные с ним подзадачи
    void deleteEpic(int id);

    // удаляет подзадачу по айди, также обновляет список подзадач в эпике и его статус
    void deleteSubtask(int id);

    // удаляет все задачи
    void deleteTasks();

    // удаляет все подзадачи из списка, а также и из эпиков
    void deleteSubtasks();

    // удаляет все эпики и их подзадачи
    void deleteEpics();
}
