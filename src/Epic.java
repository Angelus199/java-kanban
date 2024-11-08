import java.util.ArrayList;
import java.util.List;

public class Epic extends Task {
    private List<Subtask> subtasks;

    public Epic(String title, String description) {
        super(title, description, Status.NEW);
        this.subtasks = new ArrayList<>();
    }

    public List<Subtask> getSubtasks() {
        return subtasks;
    }

    public void addSubtask(Subtask subtask) {
        subtasks.add(subtask);
        // Обновление статуса эпика в зависимости от статуса подзадач
        updateStatus();
    }

    private void updateStatus() {
        if (subtasks.isEmpty()) {
            setStatus(Status.NEW);
            return;
        }

        boolean allDone = true;
        boolean anyInProgress = false;

        for (Subtask subtask : subtasks) {
            if (subtask.getStatus() != Status.DONE) {
                allDone = false;
            }
            if (subtask.getStatus() == Status.IN_PROGRESS) {
                anyInProgress = true;
            }
        }

        if (allDone) {
            setStatus(Status.DONE);
        } else if (anyInProgress) {
            setStatus(Status.IN_PROGRESS);
        } else {
            setStatus(Status.NEW);
        }
    }
}
