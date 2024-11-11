import java.util.*;

public class TaskManager {
    private static int idCounter = 0; //для счетчика
    private Map<Integer, Task> tasks = new HashMap<>();
    private Map<Integer, Epic> epics = new HashMap<>();
    private Map<Integer, Subtask> subtasks = new HashMap<>();

    public static int getNewTaskId() {
        return idCounter++;
    }

    // возвращает список всех задач из tasks
    public List<Task> getTasks() {
        return new ArrayList<>(tasks.values());
    }

    // возвращает список всех подзадач из subtasks
    public List<Subtask> getSubtasks() {
        return new ArrayList<>(subtasks.values());
    }

    // возвращает список всех всех эпиков из epics
    public List<Epic> getEpics() {
        return new ArrayList<>(epics.values());
    }

    // возвращает список подзадач, которые принадлежат определенному эпику по айди
    public List<Subtask> getEpicSubtasks(int epicId) {
        List<Subtask> result = new ArrayList<>();
        Epic epic = epics.get(epicId);
        if (epic != null) {
            for (Integer subtaskId : epic.getSubtaskIds()) {
                Subtask subtask = subtasks.get(subtaskId);
                if (subtask != null) {
                    result.add(subtask);
                }
            }
        }
        return result;
    }

    // возвращает задачу по указанному айди
    public Task getTask(int id) {
        return tasks.get(id);
    }

    // возвращает эпик по указанному айди
    public Epic getEpic(int id) {
        return epics.get(id);
    }

    // возвращает подзадачу по указанному айди
    public Subtask getSubtask(int id) {
        return subtasks.get(id);
    }

    // добавляет новую задачу в список, генерируя для нее уникальный айди и возвращает этот айди
    public int addNewTask(Task task) {
        int id = getNewTaskId();
        task.id = id;
        tasks.put(id, task);
        return id;
    }

    // добавляет новый эпик в список, генерируя для нее уникальный айди и возвращает этот айди
    public int addNewEpic(Epic epic) {
        int id = getNewTaskId();
        epic.id = id;
        epics.put(id, epic);
        return id;
    }

    // добавляет новую подзадачу
    // проверяет существование эпика, к которому относится подзадача и обновляет список подзадач эпика
    // возвращает уникальный айди новой подзадачи
    public Integer addNewSubtask(Subtask subtask) {
        Epic epic = epics.get(subtask.getEpicId());
        if (epic == null) {
            return null; // Невозможно добавить подзадачу без существующего эпика
        }
        int id = getNewTaskId();
        subtask.id = id;
        subtasks.put(id, subtask);
        epic.getSubtaskIds().add(id);
        updateEpicStatus(epic);
        return id;
    }

    // обновляет задачу, если она есть в tasks
    public void updateTask(Task task) {
        if (tasks.containsKey(task.getId())) {
            tasks.put(task.getId(), task);
        }
    }

    // обновляет существующий эпик, а также обновляет его статус на основе текущего состояния подзадач
    public void updateEpic(Epic epic) {
        if (epics.containsKey(epic.getId())) {
            epics.put(epic.getId(), epic);
            updateEpicStatus(epic); // Обновить статус эпика в случае обновления подзадач
        }
    }

    // обновляет подзадачу, а также обновляет статус связанного эпика
    public void updateSubtask(Subtask subtask) {
        if (subtasks.containsKey(subtask.getId())) {
            subtasks.put(subtask.getId(), subtask);
            Epic epic = epics.get(subtask.getEpicId());
            if (epic != null) {
                updateEpicStatus(epic);
            }
        }
    }

    // удаляет задачу по айди
    public void deleteTask(int id) {
        tasks.remove(id);
    }

    // удаляет эпик и связанные с ним подзадачи
    public void deleteEpic(int id) {
        Epic epic = epics.remove(id);
        if (epic != null) {
            for (Integer subtaskId : epic.getSubtaskIds()) {
                subtasks.remove(subtaskId);
            }
        }
    }

    // удаляет подзадачу по айди, также обновляет список подзадач в эпике и его статус
    public void deleteSubtask(int id) {
        Subtask subtask = subtasks.remove(id);
        if (subtask != null) {
            Epic epic = epics.get(subtask.getEpicId());
            if (epic != null) {
                epic.getSubtaskIds().remove(Integer.valueOf(id));
                updateEpicStatus(epic);
            }
        }
    }

    // удаляет все задачи
    public void deleteTasks() {
        tasks.clear();
    }

    // удаляет все подзадачи из списка, а также и из эпиков
    public void deleteSubtasks() {
        for (Epic epic : epics.values()) {
            epic.getSubtaskIds().clear(); // Очистка списка подзадач в каждом эпике
        }
        subtasks.clear();
    }

    // удаляет все эпики и их подзадачи
    public void deleteEpics() {
        epics.clear();
        subtasks.clear(); // Удаление всех подзадач, связанных с эпиками
    }

    // дополнительные методы

    // обновляет статус эпика с учетом всех его подзадач
    private void updateEpicStatus(Epic epic) {
        List<Subtask> subtasksByEpic = getEpicSubtasks(epic.getId());

        if (subtasksByEpic.isEmpty() || allSubtaskStatusesEquals(subtasksByEpic, Status.NEW)) {
            epic.setStatus(Status.NEW);
            return;
        }
        if (allSubtaskStatusesEquals(subtasksByEpic, Status.DONE)) {
            epic.setStatus(Status.DONE);
            return;
        }
        epic.setStatus(Status.IN_PROGRESS);
    }

    // возвращает true, если все подзадачи внутри списка имеют ожидаемый статус, иначе false
    private boolean allSubtaskStatusesEquals(List<Subtask> subtasks, Status expectedStatus) {
        for (Subtask subtask : subtasks) {
            if (!subtask.getStatus().equals(expectedStatus)) {
                return false;
            }
        }
        return true;
    }
}

