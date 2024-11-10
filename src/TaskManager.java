import java.util.*;

public class TaskManager {
    private static int idCounter = 0; //для счетчика
    private List<Task> tasks = new ArrayList<>();//список всех задач

    //метод для получения нового айди на основе текущего значения счетчика
    public static int getNewTaskId() {
        return idCounter++;
    }

    //получение всех задач всех типов
    public List<Task> getAllTasks() {
        return tasks;
    }

    //очистка всех задач
    public void removeAllTasks() {
        tasks.clear();
    }

    //получение задачи по айди
    public Task getTaskById(int id) {
        for (Task task : tasks) {
            if (task.getId() == id) {
                return task;
            }
        }
        return null; // todo при условии, если таска не была найдена в списке тасок, метод возвращает null. Насколько такое поведение корректное? В тз про это ничего не было написано
    }

    //создание задачи с передачей в параметры задачи
    public void createTask(Task task) {
        tasks.add(task);
        if (task instanceof Subtask) {
            Epic epic = (Epic) getTaskById(((Subtask) task).getEpicId());
            if (epic != null) {
                addSubtaskId(epic, task.getId());
                updateEpicStatus(epic);
            }
        }
    }

    //обновление задачи
    public void updateTask(Task task) {
        Task existTask = getTaskById(task.getId());
        if (existTask == null) {
            return;
        }

        int index = tasks.indexOf(existTask);
        tasks.set(index, task);

        if (task instanceof Subtask) {
            Epic epic = (Epic) getTaskById(((Subtask) task).getEpicId());
            if (epic != null) {
                updateEpicStatus(epic);
            }
        }
    }


    //удаление задачи по айди
    public void removeTaskById(int id) {
        Task task = getTaskById(id);
        if (task != null) {
            tasks.remove(task);
            if (task instanceof Subtask) {
                Epic epic = (Epic) getTaskById(((Subtask) task).getEpicId());
                if (epic != null) {
                    epic.getSubtaskIds().remove(task.getId());
                    updateEpicStatus(epic);
                }
            } else if (task instanceof Epic) {
                for (Integer subtaskId : ((Epic) task).getSubtaskIds()) {
                    tasks.removeIf(t -> t.getId() == subtaskId);
                }
            }
        }
    }


    // получение списка всех подзадач определённого эпика
    public List<Subtask> getSubtasksByEpic(Epic epic) {
        List<Subtask> subtasks = new ArrayList<>();
        for (Integer id : epic.getSubtaskIds()) {
            Task task = getTaskById(id);
            if (task instanceof Subtask) {
                subtasks.add((Subtask) task);
            }
        }
        return subtasks;
    }

    //обновление статуса эпика
    private void updateEpicStatus(Epic epic) {
        List<Subtask> subtasksByEpic = getSubtasksByEpic(epic);

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

    //сравнение всех статусов сабтасок эпика с переданным значением статуса
    private boolean allSubtaskStatusesEquals(List<Subtask> subtasks, Status expectedStatus) {
        for (Subtask subtask : subtasks) {
            if (!subtask.getStatus().equals(expectedStatus)) {
                return false;
            }
        }
        return true;
    }

    //метод для добавления идентификатора подзадачи в список идентификаторов подзадач, которыми управляет объект класса Epic
    public void addSubtaskId(Epic epic, int subtaskId) {
        List<Integer> newSubtaskIds = epic.getSubtaskIds();
        newSubtaskIds.add(subtaskId);
        epic.setSubtaskIds(newSubtaskIds);
    }

}
