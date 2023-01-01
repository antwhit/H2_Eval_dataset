import java.util.ArrayList;
import java.util.Random;
import java.util.Calendar;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class QuestionHandler {

    public ArrayList<Question> questions = null;

    private Random rand = null;

    public int noOfQuestions = 0;

    public int noOfUsedQuestions = 0;

    public QuestionHandler(String filename) throws IOException {
        Calendar cal = Calendar.getInstance();
        questions = new ArrayList<Question>();
        rand = new Random();
        rand.setSeed(cal.getTimeInMillis());
        ReadQuestionFile(filename);
    }

    private void ReadQuestionFile(String filename) throws IOException {
        FileReader fr = new FileReader(filename);
        BufferedReader br = new BufferedReader(fr);
        String line = null;
        noOfQuestions = 0;
        do {
            line = br.readLine();
            if (line != null) {
                String split[] = line.split("/");
                if (split.length > 2) {
                    Question q = new Question();
                    q.Question = split[0];
                    q.Hint = split[1];
                    q.Answer = new String[split.length - 2];
                    q.used = false;
                    for (int i = 2; i < split.length; i++) {
                        q.Answer[i - 2] = split[i];
                    }
                    questions.add(q);
                    noOfQuestions++;
                }
            }
        } while (line != null);
    }

    public void ResetUsedQuestions() {
        Question q;
        for (int cntr = 0; cntr < questions.size(); cntr++) {
            q = questions.get(cntr);
            q.used = false;
            questions.set(cntr, q);
        }
        noOfUsedQuestions = 0;
    }

    public Question randomQuestion() {
        int questNumber;
        Question q;
        float d = ((float) noOfUsedQuestions / (float) questions.size());
        d = (float) (d * 100.0);
        if (d >= 75.0) {
            ResetUsedQuestions();
            Trivbot.log.log("Resetting Questions after " + noOfUsedQuestions + " questions");
        }
        do {
            questNumber = (int) (rand.nextFloat() * questions.size());
            q = questions.get(questNumber);
        } while (q.used == true);
        noOfUsedQuestions++;
        q.used = true;
        questions.set(questNumber, q);
        return q;
    }
}
