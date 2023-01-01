import java.util.Arrays;

public class TheSoccerDivTwo {

    public int find(int[] points) {
        Team[] teams = new Team[points.length];
        for (int i = 0; i < points.length; i++) {
            teams[i] = new Team();
            teams[i].index = i;
            teams[i].points = points[i];
        }
        Arrays.sort(teams);
        int k = 0;
        for (int i = 0; i < points.length; i++) if (teams[i].index == 0) k = i;
        for (int j = points.length - 1; j > k && k >= 0; k--, j--) {
            teams[k].points += 3;
            teams[k].played = true;
            teams[j].played = true;
        }
        Arrays.sort(teams);
        int unplayedHigher = 0;
        int unplayedLower = 0;
        for (int i = 0; i < points.length; i++) {
            if (!teams[i].played) unplayedHigher++;
            if (teams[i].index == 0) {
                unplayedLower = unplayedHigher;
                k = i;
            }
        }
        if (unplayedHigher >= unplayedLower) return points.length - k;
        unplayedLower -= unplayedHigher;
        int kk = k;
        int matches = 0;
        for (int i = 0; i < k; i++) {
            if (!teams[i].played) {
                matches++;
                if (teams[i].points + 1 > teams[k].points) kk--;
            }
            if (matches > unplayedLower) break;
        }
        return points.length - kk;
    }

    class Team implements Comparable<Team> {

        int index;

        int points;

        boolean played;

        @Override
        public int compareTo(Team team) {
            int rv = this.points = team.points;
            if (rv != 0) return rv; else return -(this.index - team.index);
        }
    }

    public static void main(String[] args) {
        System.out.println(new TheSoccerDivTwo().find(new int[] { 4, 7, 7, 7 }));
    }
}
