import java.io.Serializable;

public class Player implements Serializable, Comparable<Player> {
    private String name;
    private int score;

    public Player(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    @Override
    public int compareTo(Player other) {
        return Integer.compare(other.score, this.score); // убывание
    }

    @Override
    public String toString() {
        return name + ": " + score + " баллов";
    }
}