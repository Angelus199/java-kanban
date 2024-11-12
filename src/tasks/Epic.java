package tasks;

import java.util.ArrayList;
import java.util.List;

public class Epic extends Task {

    private List<Integer> subtaskIds;

    public Epic(String title, String description) {
        super(title, description);
        this.subtaskIds = new ArrayList<>();
    }

    //геттер и сеттер
    public List<Integer> getSubtaskIds() {
        return subtaskIds;
    }

    public void addSubtask(int id) {  // todo реализация в epic метода добавления в список
        subtaskIds.add(id);
    }

    public void removeSubtask(int id) { // todo реализация в epic метода удаления в список
        subtaskIds.remove(Integer.valueOf(id));
    }

    public void clearSubtaskIds() {
        subtaskIds.clear();
    }

    //для удобной печати объектов tasks.Epic
    @Override
    public String toString() {
        return "tasks.Epic{" + "id=" + id + ", title='" + title + '\'' +
                ", description='" + description + '\'' + ", status=" + status +
                ", subtasks=" + subtaskIds + '}';
    }
}
