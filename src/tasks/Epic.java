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

    public boolean addSubtask(int id) {
        if (id == this.id) {
            return false; // не добавляем себя
        }
        subtaskIds.add(id);
        return true;
    }


    public void removeSubtask(int id) {
        subtaskIds.remove(Integer.valueOf(id));
    }

    public void clearSubtaskIds() {
        subtaskIds.clear();
    }

    //для удобной печати объектов
    @Override
    public String toString() {
        return "tasks.Epic{" + "id=" + id + ", title='" + title + '\'' +
                ", description='" + description + '\'' + ", status=" + status +
                ", subtasks=" + subtaskIds + '}';
    }
}
