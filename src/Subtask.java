public class Subtask extends Task {
    private int epicId;

    public Subtask(int id, String title, String description, Status status, int epicId) {
        super(id, title, description, status);
        this.epicId = epicId;
    }

    //геттер
    public int getEpicId() {
        return epicId;
    }

    //для удобной печати объектов Subtask
    @Override
    public String toString() {
        return "Subtask{" + "id=" + id + ", title='" + title + '\'' +
                ", description='" + description + '\'' + ", status=" + status +
                ", epicId=" + epicId + '}';
    }
}
