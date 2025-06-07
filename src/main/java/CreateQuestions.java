import java.io.*;
import java.util.ArrayList;

public class CreateQuestions {
    public static void main(String[] args) {
        ArrayList<Question> questions = new ArrayList<>();

        questions.add(new Question("Сколько этажей в двойке",
                new String[]{"16", "15", "17"}, 0));

        questions.add(new Question("Сколько всего курсов в бакалавриате",
                new String[]{"5", "4", "6"}, 1));

        questions.add(new Question("Год основание КФУ?",
                new String[]{"1904", "1814", "1804"}, 2));

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("questions.dat"))) {
            oos.writeObject(questions);
            System.out.println("Вопросы успешно сохранены в questions.dat");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}