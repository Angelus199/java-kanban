import java.util.List;

public class Main {
    public static void main(String[] args) {
        TaskManager manager = new TaskManager();

        // создание двух задач
        Task task1 = new Task(0, "Задача 1", "Описание задачи 1", Status.NEW);
        Task task2 = new Task(0, "Задача 2", "Описание задачи 2", Status.NEW);
        manager.addNewTask(task1);
        manager.addNewTask(task2);

        // проверка создания задач
        System.out.println("\n--- Проверка создания задач ---");
        if (manager.getTasks().contains(task1)) {
            System.out.println("Успешно создана: " + task1);
        } else {
            System.out.println("Ошибка при создании: Задача 1");
        }

        if (manager.getTasks().contains(task2)) {
            System.out.println("Успешно создана: " + task2);
        } else {
            System.out.println("Ошибка при создании: Задача 2");
        }

        // проверка получения задачи по ID
        System.out.println("\n--- Проверка получения задачи по ID ---");
        Task fetchedTask = manager.getTask(task1.getId());
        if (fetchedTask != null) {
            System.out.println("Задача 1 успешно получена по ID: " + fetchedTask);
        } else {
            System.out.println("Ошибка получения по ID: Задача 1");
        }

        // создание эпиков и подзадач
        Epic epic1 = new Epic(0, "Эпик 1", "Описание эпика 1");
        manager.addNewEpic(epic1);

        Subtask subtask1 = new Subtask(0, "Подзадача 1", "Описание подзадачи 1", Status.NEW, epic1.getId());
        Subtask subtask2 = new Subtask(0, "Подзадача 2", "Описание подзадачи 2", Status.NEW, epic1.getId());
        manager.addNewSubtask(subtask1);
        manager.addNewSubtask(subtask2);

        Epic epic2 = new Epic(0, "Эпик 2", "Описание эпика 2");
        manager.addNewEpic(epic2);

        Subtask subtask3 = new Subtask(0, "Подзадача 3", "Описание подзадачи 3", Status.NEW, epic2.getId());
        manager.addNewSubtask(subtask3);

        // Печать всех задач, эпиков и подзадач
        System.out.println("\n--- Список задач ---");
        for (Task task : manager.getTasks()) {
            System.out.println(task);
        }

        System.out.println("\n--- Список эпиков ---");
        for (Epic epic : manager.getEpics()) {
            System.out.println(epic);
        }

        System.out.println("\n--- Список подзадач ---");
        for (Subtask subtask : manager.getSubtasks()) {
            System.out.println(subtask);
        }

        // Проверка получения подзадач эпика
        List<Subtask> subtasksOfEpic1 = manager.getEpicSubtasks(epic1.getId());
        System.out.println("\nПодзадачи для Эпик 1: " + subtasksOfEpic1);
        if (subtasksOfEpic1.size() == 2 && subtasksOfEpic1.contains(subtask1) && subtasksOfEpic1.contains(subtask2)) {
            System.out.println("Успешно получен список всех подзадач для Эпик 1");
        } else {
            System.out.println("Ошибка получения подзадач для Эпик 1");
        }

        // обновление статусов подзадач
        task1.setStatus(Status.DONE);
        manager.updateTask(task1);

        subtask1.setStatus(Status.DONE);
        manager.updateSubtask(subtask1);

        subtask2.setStatus(Status.IN_PROGRESS);
        manager.updateSubtask(subtask2);

        subtask3.setStatus(Status.DONE);
        manager.updateSubtask(subtask3);

        // проверка обновления статуса эпика
        System.out.println("\n--- Проверка обновления статуса эпика ---");
        Epic updatedEpic = manager.getEpic(epic1.getId());
        if (updatedEpic != null && updatedEpic.getStatus() == Status.IN_PROGRESS) {
            System.out.println("Статус эпика 1 успешно обновлён: " + updatedEpic);
        } else {
            System.out.println("Ошибка при обновлении статуса эпика 1");
        }

        // печать после изменения статусов
        System.out.println("\n--- Список задач после изменения статусов ---");
        for (Task task : manager.getTasks()) {
            System.out.println(task);
        }

        System.out.println("\n--- Список эпиков после изменения статусов ---");
        for (Epic epic : manager.getEpics()) {
            System.out.println(epic);
        }

        System.out.println("\n--- Список подзадач после изменения статусов ---");
        for (Subtask subtask : manager.getSubtasks()) {
            System.out.println(subtask);
        }


        // удаление задачи и эпика
        manager.deleteTask(task1.getId());
        manager.deleteEpic(epic2.getId());

        // Печать после удаления
        System.out.println("\n--- Список задач после удаления ---");
        for (Task task : manager.getTasks()) {
            System.out.println(task);
        }

        System.out.println("\n--- Список эпиков после удаления ---");
        for (Epic epic : manager.getEpics()) {
            System.out.println(epic);
        }

        System.out.println("\n--- Список подзадач после удаления ---");
        for (Subtask subtask : manager.getSubtasks()) {
            System.out.println(subtask);
        }


        // очистка всех задач
        System.out.println("\n--- Очистка всех задач ---");
        manager.deleteTasks();
        manager.deleteEpics(); // Учитывая, что эпики в нашей логике будут очищать и подзадачи
        manager.deleteSubtasks();

        if (manager.getTasks().isEmpty() && manager.getEpics().isEmpty() && manager.getSubtasks().isEmpty()) {
            System.out.println("Все задачи, эпики и подзадачи успешно удалены");
        } else {
            System.out.println("Ошибка при очистке всех задач, эпиков и подзадач");
        }
    }
}
