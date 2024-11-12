package tasks;

public class Task {
    protected int id;
    protected String title;
    protected String description;
    protected Status status;

    public Task(String title, String description) { // todo изменен конструктор теперь принимает только 2 параметра
        this.title = title;
        this.description = description;
        this.status = Status.NEW;   // todo статус инициализирован значением NEW
    }

    //геттеры и сеттеры
    public void setTitle(String title) {  // todo реализованы сеттеры для description, title
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Status getStatus() {
        return status;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setStatus(Status status) {
        this.status = status;
    }


    // todo переопределены equals() и hashCode()
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Task task = (Task) obj;
        return id == task.id;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }

    //переопределяем метод для удобной печати объектов Task
    @Override
    public String toString() {
        return "tasks.Task{" + "id=" + id + ", title='" + title + '\'' +
                ", description='" + description + '\'' + ", status=" + status + '}';
    }
}
