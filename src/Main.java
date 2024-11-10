import java.util.List;

public class Main {
    public static void main(String[] args) {
        TaskManager manager = new TaskManager();

        // создание двух задач
        Task task1 = new Task(TaskManager.getNewTaskId(), "Задача 1", "Описание задачи 1", Status.NEW);
        Task task2 = new Task(TaskManager.getNewTaskId(), "Задача 2", "Описание задачи 2", Status.NEW);
        manager.createTask(task1);
        manager.createTask(task2);

        // проверка создания задач
        System.out.println("\n--- Проверка создания задач ---");
        if (manager.getAllTasks().contains(task1)) {
            System.out.println("Успешно создана: " + task1);
        } else {
            System.out.println("Ошибка при создании: Задача 1");
        }

        if (manager.getAllTasks().contains(task2)) {
            System.out.println("Успешно создана: " + task2);
        } else {
            System.out.println("Ошибка при создании: Задача 2");
        }

        // проверка получения задачи по айди
        System.out.println("\n--- Проверка получения задачи по ID ---");
        Task fetchedTask = manager.getTaskById(task1.getId());
        if (fetchedTask != null) {
            System.out.println("Задача 1 успешно получена по ID: " + fetchedTask);
        } else {
            System.out.println("Ошибка получения по ID: Задача 1");
        }

        // создание эпиков и подзадач
        Epic epic1 = new Epic(TaskManager.getNewTaskId(), "Эпик 1", "Описание эпика 1");
        Subtask subtask1 = new Subtask(TaskManager.getNewTaskId(), "Подзадача 1", "Описание подзадачи 1", Status.NEW, epic1.getId());
        Subtask subtask2 = new Subtask(TaskManager.getNewTaskId(), "Подзадача 2", "Описание подзадачи 2", Status.DONE, epic1.getId());
        manager.createTask(epic1);
        manager.createTask(subtask1);
        manager.createTask(subtask2);

        // проверка получения списка всех подзадач определённого эпика
        System.out.println("\n--- Проверка получения подзадач эпика ---");
        List<Subtask> subtasksOfEpic1 = manager.getSubtasksByEpic(epic1);
        System.out.println("Подзадачи для Эпик 1: " + subtasksOfEpic1);
        if (subtasksOfEpic1.size() == 2 && subtasksOfEpic1.contains(subtask1) && subtasksOfEpic1.contains(subtask2)) {
            System.out.println("Успешно получен список всех подзадач для Эпик 1");
        } else {
            System.out.println("Ошибка получения подзадач для Эпик 1");
        }

        // обновление статусов
        task1.setStatus(Status.DONE);
        manager.updateTask(task1);

        // проверка обновления статуса
        System.out.println("\n--- Проверка обновления статуса ---");
        Task updatedTask = manager.getTaskById(task1.getId());
        if (updatedTask != null && updatedTask.getStatus() == Status.DONE) {
            System.out.println("Статус задачи 1 успешно обновлён: " + updatedTask);
        } else {
            System.out.println("Ошибка при обновлении статуса: Задача 1");
        }

        // удаление задачи
        System.out.println("\n--- Проверка удаления задачи ---");
        manager.removeTaskById(task1.getId());
        if (manager.getTaskById(task1.getId()) == null) {
            System.out.println("Задача 1 успешно удалена");
        } else {
            System.out.println("Ошибка при удалении: Задача 1");
        }

        // очистка всех задач
        System.out.println("\n--- Проверка очистки всех задач ---");
        manager.removeAllTasks();
        if (manager.getAllTasks().isEmpty()) {
            System.out.println("Все задачи успешно удалены");
        } else {
            System.out.println("Ошибка при удалении всех задач");
        }



        // создание двух задач
        Task task3 = new Task(TaskManager.getNewTaskId(), "Задача 3", "Описание задачи 3", Status.NEW);
        Task task4 = new Task(TaskManager.getNewTaskId(), "Задача 4", "Описание задачи 4", Status.NEW);
        manager.createTask(task3);
        manager.createTask(task4);

        // создание двух эпиков: первый с двумя подзадачами, второй с одной
        Epic epic2 = new Epic(TaskManager.getNewTaskId(), "Эпик 2", "Описание эпика 2");
        Subtask subtask3 = new Subtask(TaskManager.getNewTaskId(), "Подзадача 3", "Описание подзадачи 4", Status.NEW, epic2.getId());
        Subtask subtask4 = new Subtask(TaskManager.getNewTaskId(), "Подзадача 4", "Описание подзадачи 5", Status.NEW, epic2.getId());
        manager.createTask(epic2);
        manager.createTask(subtask3);
        manager.createTask(subtask4);

        Epic epic3 = new Epic(TaskManager.getNewTaskId(), "Эпик 3", "Описание эпика 3");
        Subtask subtask5 = new Subtask(TaskManager.getNewTaskId(), "Подзадача 5", "Описание подзадачи 3", Status.IN_PROGRESS, epic3.getId());
        manager.createTask(epic3);
        manager.createTask(subtask5);

        // печать всех задач
        System.out.println("Задачи после создания:");
        for (Task task : manager.getAllTasks()) {
            System.out.println(task);
        }

        // изменение статусов задач и подзадач
        Task updatedTask3 = new Task(task3.getId(), task3.getTitle(), task3.getDescription(), task3.getStatus());
        updatedTask3.setStatus(Status.DONE);
        manager.updateTask(updatedTask3);
        subtask4.setStatus(Status.DONE);
        manager.updateTask(subtask4);
        subtask5.setStatus(Status.DONE);
        manager.updateTask(subtask5);

        // печать всех задач после изменения статусов
        System.out.println("\nЗадачи после изменения статусов:");
        for (Task task : manager.getAllTasks()) {
            System.out.println(task);
        }

    }


}

