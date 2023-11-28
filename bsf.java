import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class bsf {
    static class state {
        int jugA;
        int jugB;
        state parent;

        public state(int jugA, int jugB, state parent) {
            this.jugA = jugA;
            this.jugB = jugB;
            this.parent = parent;
        }
    }

    public static boolean isgoal(state cur, int target) {
        if (cur.jugA == target)
            return true;
        return false;
    }

    public static List<int[]> way(state goal) {

        List<int[]> path = new ArrayList<int[]>();

        while (goal != null) {
            path.add(new int[] { goal.jugA, goal.jugB });
            goal = goal.parent;
        }
        Collections.reverse(path);
        return path;
    }

    static List<int[]> solution(int jugA, int jugB, int target) {

        state initial = new state(jugA, jugB, null);

        Set<state> visited = new HashSet<>();
        Queue<state> queue = new LinkedList<>();

        queue.add(initial);

        while (!queue.isEmpty()) {

            state cur = queue.poll();

            if (cur.jugA == target)
                return way(cur);

            if (!visited.contains(cur)) {

                visited.add(cur);

                // poor water from A to B
                if (cur.jugA > 0) {
                    int AmountToPoor = jugB - cur.jugB;
                    int newJugB = cur.jugB + cur.jugA < AmountToPoor ? cur.jugA : AmountToPoor;
                    int newjugA = cur.jugA < AmountToPoor ? 0 : cur.jugA - AmountToPoor;
                    queue.add(new state(newjugA, newJugB, cur));
                }

                // poor water from B to A
                if (cur.jugB > 0) {
                    int AmountToPoor = jugA - cur.jugA;
                    int newjugA = cur.jugA + cur.jugB < AmountToPoor ? cur.jugB : AmountToPoor;
                    int newJugB = cur.jugB < AmountToPoor ? 0 : cur.jugB - AmountToPoor;
                    queue.add(new state(newjugA, newJugB, cur));
                }

                // Fill jugA
                queue.add(new state(jugA, cur.jugB, cur));

                // Fill jugB
                queue.add(new state(cur.jugA, jugB, cur));

                // Emmpty jugA
                queue.add(new state(0, cur.jugB, cur));

                // Empty jugB
                queue.add(new state(cur.jugA, 0, cur));
            }
        }

        return null;
    }

    public static void main(String[] args) {
        // state initial = new state(4, 3);

        List<int[]> path = solution(5, 3, 2);

        for (int[] arr : path) {
            System.out.print(Arrays.toString(arr));
            System.out.print("->");
        }
    }
}
