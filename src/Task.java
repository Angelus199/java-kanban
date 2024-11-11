public class Task {
    protected int id;
    protected String title;
    protected String description;
    protected Status status;

    public Task(int id, String title, String description, Status status) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
    }

    //геттеры и сеттеры
    public int getId() {
        return id;
    }

    public String getTitle() {   //todo нужны ли данные методы, если они не используются на данный момент?
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }


    //переопределяем метод для удобной печати объектов Task
    @Override
    public String toString() {
        return "Task{" + "id=" + id + ", title='" + title + '\'' +
                ", description='" + description + '\'' + ", status=" + status + '}';
    }
}
