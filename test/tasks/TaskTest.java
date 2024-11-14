package tasks;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class TaskTest {

    // экземпляры класса Task равны друг другу, если равен их id
    @Test
    public void testTaskEqualityById() {
        Task task1 = new Task("Задача1", "Описание1");
        Task task2 = new Task("Задача2", "Описание2");
        task1.setId(1);
        task2.setId(1);

        assertEquals(task1, task2);
    }

    // экземпляры класса Task не равны друг другу, если их id разный
    @Test
    public void testTaskInequalityById() {
        Task task1 = new Task("Задача1", "Описание1");
        Task task2 = new Task("Задача", "Описание2");
        task1.setId(1);
        task2.setId(2);

        assertNotEquals(task1, task2);
    }
}
