package az.orient.edumanagementsystem.model;

import az.orient.edumanagementsystem.interfaces.Login;

import java.io.*;
import java.util.Scanner;

import static az.orient.edumanagementsystem.model.LaboratoryAssistant.filePathLabAssist;

public class Dean extends StudentGetScores implements Login {
    public static final String filePathDeanLogin = "src/az/orient/edumanagementsystem/txts/dean_login.txt";

    public void register(String username, String password) throws IOException {
        /*
        - Proses : Dekan laboranti qeydiyyat edir
        - Qoyulan mehdudiyyet : eyni istifadechi adi ve shifre bazamda varsa qebul olunmur.
         */
        File file = new File(filePathLabAssist);
        try {
            file.createNewFile();
            Scanner sc = new Scanner(file);
            boolean userExists = false;
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] strings = line.split(" ");
                String usernameFromFile = strings[0];
                String passwordFromFile = strings[1];
                if (username.equalsIgnoreCase(usernameFromFile) && password.equalsIgnoreCase(passwordFromFile)) {
                    System.err.println("Username, password already exists");
                    userExists = true;
                    break;
                }
            }
            sc.close();
            if (!userExists) {
                FileWriter fileWriter = new FileWriter(file, true);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                bufferedWriter.write("\n" + username + " " + password);
                bufferedWriter.close();
                System.out.println("Successfully added!");
                System.out.println("---");

            }
        } catch (IOException e) {
            System.err.println("Error " + e.getMessage());
        }}

    @Override
    public boolean login(String username, String password) throws IOException {
        /*
        - Proses : Dekan girish edir hesabina
        - Qoyulan mehdudiyyet : istifadechi adi ve shifre bazada olan dekan melumatlari ile uygun gelmelidir
         */
        boolean isAuth = false;
        File file = new File(filePathDeanLogin);
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(file);
        } catch (FileNotFoundException e) {
            System.out.println("File not found exception");
        }
        BufferedReader bufferedReader = new BufferedReader(fileReader);
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
