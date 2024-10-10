/*
 * NAME: Clayton Hammen Tan
 * PID: A17819097
 */

/**
 * Simplified Round Robin Scheduling
 *
 * @author Clayton Hammen Tan
 * @since 04-29-24
 */
public class RoundRobin {

    /* constants */
    private static final String TASKS_COMPLETED = "All tasks are already completed.";
    private static final int DEFAULT_QUANTUM = 4;

    /* instance variables */
    private MyQueue<Task> waitlist, finished;
    private int quantum, burstTime, waitTime;

    /**
     * Constructs a RoundRobin scheduler with a default quantum of 4 and an array of tasks.
     *
     * @param toRun the array of tasks to be scheduled.
     * @throws IllegalArgumentException if the task array is null or empty.
     */
    public RoundRobin(Task[] toRun) {
        this(DEFAULT_QUANTUM, toRun);
    }

    /**
     * Constructs a RoundRobin scheduler with a specified quantum and an array of tasks.
     *
     * @param quantum the quantum time unit.
     * @param toRun the array of tasks to be scheduled.
     * @throws IllegalArgumentException if quantum is less than 1, or if toRun is null or empty.
     */
    public RoundRobin(int quantum, Task[] toRun) {
        if (quantum < 1) {
            throw new IllegalArgumentException();
        }
        if (toRun == null || toRun.length == 0) {
            throw new IllegalArgumentException();
        }

        this.waitlist = new MyQueue<>();
        this.finished = new MyQueue<>();
        this.quantum = quantum;
        this.burstTime = 0;
        this.waitTime = 0;

        for (Task task : toRun) {
            this.waitlist.enqueue(task);
        }
    }

    /**
     * Runs all tasks in the waitlist according to the Round Robin scheduling algorithm.
     *
     * @return A string representation of the completion of tasks including burst and wait times.
     */
    public String runAllTasks() {
        if (waitlist.isEmpty()) {
            return TASKS_COMPLETED;
        }
        while (!waitlist.isEmpty()) {
            Task currentTask = waitlist.dequeue();
            int timeToRun = 0;

            while (timeToRun < quantum && currentTask.runTask()) {
                timeToRun++;
            }

            burstTime += timeToRun;
            waitTime += waitlist.size() * timeToRun;

            if (currentTask.isFinished()) {
                finished.enqueue(currentTask);
            } else {
                waitlist.enqueue(currentTask);
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append("All tasks are run within ")
                .append(burstTime)
                .append(" units of burst time and ")
                .append(waitTime)
                .append(" units of wait time. The tasks are finished in this order:\n");

        while (!finished.isEmpty()) {
            Task currentTask = finished.dequeue();
            sb.append(currentTask.toString());
            if (!finished.isEmpty()) {
                sb.append(" -> ");
            }
        }
        return sb.toString();
    }

    /*
      Main method for testing.
      @param args command-line arguments

    public static void main(String[] args) {
        Task[] test1 = {new Task("A", 3), new Task("B", 4),
                        new Task("C", 4), new Task("D", 12),
                        new Task("E", 6), new Task("F", 3)};
        RoundRobin rr1 = new RoundRobin(3, test1);     // Quantum: 3, toRun: test1
        System.out.println(rr1.runAllTasks());   // Burst: 32, Wait: 86, Order: AFBCED
        System.out.println();
        System.out.println(rr1.runAllTasks());   // TASKS_COMPLETED
        System.out.println();

        Task[] test2 = {new Task("A", 9), new Task("B", 8),
                        new Task("C", 6), new Task("D", 4),
                        new Task("E", 4), new Task("F", 3)};
        RoundRobin rr2 = new RoundRobin(test2);  // Quantum: 4, toRun: test2
        System.out.println(rr2.runAllTasks());   // Burst: 34, Wait: 123, Order: DEFBCA
        System.out.println();
        System.out.println(rr2.runAllTasks());   // TASKS_COMPLETED
        System.out.println();

        Task[] test3 = {new Task("A", 7), new Task("B", 5),
                        new Task("C", 3), new Task("D", 1),
                        new Task("E", 2), new Task("F", 4),
                        new Task("G", 6), new Task("H", 8)};
        RoundRobin rr3 = new RoundRobin(3, test3);     // Quantum: 3, toRun: test3
        System.out.println(rr3.runAllTasks());   // Burst: 36, Wait: 148, Order: CDEBFGAH
        System.out.println();
        System.out.println(rr3.runAllTasks());   // TASKS_COMPLETED
        System.out.println();
    }
    */
}
