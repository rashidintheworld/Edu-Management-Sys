package az.orient.edumanagementsystem.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class StudentGetScores {
    public void displayScores(String file, double lowerLimit, double upperLimit) throws IOException {
    /*
    - Proses : Dekan ballari gormesi ucun blok.
    - Qoyulan mehdudiyyet : imtahan ballari 0 ve 100 arasi olmalidir.
     */
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] strings = line.split(" ");
                String name = strings[0];
                String surname = strings[1];
                double score = Double.parseDouble(strings[2]); //+score
                if (score >= lowerLimit && score <= upperLimit) {
                    System.out.println("Name: " + name + " Surname: " + surname + " Score: " + score);
                }
            }
            bufferedReader.close();
        } catch (Exception ex) {
            System.err.println("Error:" + ex.getMessage());
        }
    }

    public void getAllScores(String file) throws IOException {
        displayScores(file, 0, 100);
    }

    public void getScoresExcellents(String file) throws IOException {
        displayScores(file, 91, 100);
    }

    public void getScoresUnsatisfactory(String file) throws IOException {
        displayScores(file, 0, 50);
    }

}
