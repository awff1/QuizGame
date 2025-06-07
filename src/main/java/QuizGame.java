import java.io.*;
import java.util.*;

public class QuizGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Question> questions = loadQuestions("questions.dat");
        ArrayList<Player> players = loadRatings("rating.dat");

        System.out.print("Введите ваше имя: ");
        String name = scanner.nextLine();
        int score = 0;

        for (Question q : questions) {
            System.out.println("\n" + q.getQuestionText());
            String[] options = q.getOptions();
            for (int i = 0; i < options.length; i++) {
                System.out.println((i + 1) + ") " + options[i]);
            }
            int answer = 0;
            while (answer < 1 || answer > 3) {
                System.out.print("Ваш ответ (1-3): ");
                if (scanner.hasNextInt()) {
                    answer = scanner.nextInt();
                } else {
                    scanner.next(); // пропуск некорректного ввода
                }
            }
            if (answer - 1 == q.getCorrectAnswer()) {
                score++;
            }
        }

        Player player = new Player(name, score);
        players.add(player);
        Collections.sort(players);

        saveRatings("rating.dat", players);

        System.out.println("\nРезультат: " + score + " правильных ответов.");
        System.out.println("\n--- Рейтинг игроков ---");
        for (Player p : players) {
            System.out.println(p);
        }
    }

    private static ArrayList<Question> loadQuestions(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            return (ArrayList<Question>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Ошибка загрузки вопросов.");
            return new ArrayList<>();
        }
    }

    private static ArrayList<Player> loadRatings(String filename) {
        File file = new File(filename);
        if (!file.exists()) return new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (ArrayList<Player>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<>();
        }
    }

    private static void saveRatings(String filename, ArrayList<Player> players) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(players);
        } catch (IOException e) {
            System.out.println("Ошибка сохранения рейтинга.");
        }
    }
}