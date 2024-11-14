package tasks;

public class Subtask extends Task {
    private int epicId;

    public Subtask(String title, String description, int epicId) {
        super(title, description);
        this.epicId = epicId;
    }

    //геттер
    public int getEpicId() {
        return epicId;
    }

    //для удобной печати объектов
    @Override
    public String toString() {
        return "tasks.Subtask{" + "id=" + id + ", title='" + title + '\'' +
                ", description='" + description + '\'' + ", status=" + status +
                ", epicId=" + epicId + '}';
    }
}
