import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Task {
    private static int idCounter = 0;
    private final int id;
    private String title;
    private String description;
    private Status status;

    public Task(String title, String description, Status status) {
        this.id = ++idCounter;
        this.title = title;
        this.description = description;
        this.status = status.NEW;
    }

    //геттеры и сеттеры

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
    //переопределяем методы, необходимые для id и сравнивания их
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true; // проверяем адреса объектов
        if (obj == null || getClass() != obj.getClass()) return false; // проверяем ссылку на null, сравниваем классы
        Task task = (Task) obj;// открываем доступ к полям другого объекта
        return id == task.id; // сравниваем
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


}
