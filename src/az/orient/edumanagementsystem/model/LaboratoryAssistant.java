package az.orient.edumanagementsystem.model;

import az.orient.edumanagementsystem.interfaces.Login;

import java.io.*;
import java.util.Scanner;

public class LaboratoryAssistant implements Login {
    public static final String filePathLabAssist = "src/az/orient/edumanagementsystem/txts/laboratory_assistants_login.txt";
    public static final String filePathStudentScores = "src/az/orient/edumanagementsystem/txts/students_scores.txt";

    public void register(String name, String surname, double score) throws IOException {
        /*
        - Proses : Laborant telebeni(ad ,soyad,imtahan bali ) formatinda qeydiyyat edir.
        - Qoyulan mehdudiyyet : eyni ad soyad bazamda varsa qebul olunmur.
        */
        File file = new File(filePathStudentScores);
        try {
            file.createNewFile();
            Scanner scanner = new Scanner(file);
            boolean nameSurnameExists = false;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] strings = line.split(" ");
                String nameFromFile = strings[0];
                String surnameFromFile = strings[1];
                if (name.equalsIgnoreCase(nameFromFile) && surname.equalsIgnoreCase(surnameFromFile)) {
                    System.err.println("Name  surname already exists");
                    nameSurnameExists = true;
                    break;
                }
            }
            scanner.close();
            if (!nameSurnameExists) {
                if (score < 0 || score > 100) {
                    System.err.println("Average must be between 0,100");
                    return;
                }
                FileWriter fileWriter = new FileWriter(file, true);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                bufferedWriter.write("\n" + name + " " + surname + " " + score);
                bufferedWriter.close();
                System.out.println("Successfully added!");
                System.out.println("---");
            }
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }}

    @Override
    public boolean login(String username, String password) throws IOException {
        /*
        - Proses : Laborant girish edir hesabina
        - Qoyulan mehdudiyyet : istifadechi adi ve shifre bazada olan laborant melumatlari ile uygun gelmelidir
         */
        boolean isAuth = false;
        File file = new File(filePathLabAssist);
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        try {
            fileReader = new FileReader(file);
        } catch (FileNotFoundException e) {
            System.err.println("File not found exception");
        }
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            String[] strings = line.split(" ");
            String usernameF = strings[0];
            String passwordF = strings[1];
            if (username.equals(usernameF) && password.equals(passwordF)) {
                isAuth = true;
                break;
            }
        }
        bufferedReader.close();
        return isAuth;
    }
}
