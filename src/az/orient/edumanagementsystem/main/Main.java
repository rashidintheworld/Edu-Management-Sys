package az.orient.edumanagementsystem.main;
import az.orient.edumanagementsystem.model.Dean;
import az.orient.edumanagementsystem.model.LaboratoryAssistant;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        try {
            Scanner sc = new Scanner(System.in);
            while (true) {
                System.out.println("Who is entering? \n1.Dean\n2.Assistant");
                int operationNumber = sc.nextInt();
                LaboratoryAssistant laboratoryAssistant = new LaboratoryAssistant();
                Dean dean = new Dean();
                if (operationNumber == 1) {
                    /*
                    - Eger emeliyyat nomresi 1 olsa , dekan girish edir -> Istifadechi adi ve shifre.
                    - Eger girish duzdurse, laborant elave ede bilir, 91+ telebelere baxa bilir ve kesilmish telebelere baxa bilir.
                    - Eger girish sehvdirse , mesaj verilir.
                     */
                    System.out.println("Enter a username,password");
                    String username = sc.next();
                    String password = sc.next();
                    if (dean.login(username, password)) {
                        System.out.println("Choose operation->\n1.Register lab assistant 2.Get all scores 3.Get 91+ scores 4.Get unsatisfactory scores");
                        int operationS = sc.nextInt();
                        if (operationS == 1) {
                            System.out.println("Enter a name and password as appropriate");
                            String nameFromDean = sc.next();
                            String passwordFromDean = sc.next();
                            dean.register(nameFromDean, passwordFromDean);
                        } else if (operationS == 2) {
                            dean.getAllScores(LaboratoryAssistant.filePathStudentScores);
                        } else if (operationS == 3) {
                            dean.getScoresExcellents(LaboratoryAssistant.filePathStudentScores);
                        } else if (operationS == 4) {
                            dean.getScoresUnsatisfactory(LaboratoryAssistant.filePathStudentScores);
                        } else {
                            System.out.println("Please, choose right number(1,2,3,4)");
                        }
                    } else {
                        System.out.println("We didnt find related account!");
                    }
                } else if (operationNumber == 2) {
                    /*
                    - Eger emeliyyat nomresi 2 olsa , laborant girish edir -> Istifadechi adi ve shifre.
                    - Eger girish duzdurse, laborantin ishi telebe elave edib(ad,soyad,bal(iqb+ib).
                    - Eger girish sehvdirse, mesaj verilir.
                     */
                    System.out.println("Enter a username,password");
                    String username = sc.next();
                    String password = sc.next();
                    if (laboratoryAssistant.login(username, password)) {
                        System.out.println("Add a student ->\nEnter a student name,surname,score as appropriate");
                        String nameFromAssistant = sc.next();
                        String surnameFromAssistant = sc.next();
                        double scoreFromAssistant = sc.nextDouble();
                        laboratoryAssistant.register(nameFromAssistant, surnameFromAssistant, scoreFromAssistant);
                    } else {
                        System.err.println("We didnt find related account!");
                    }
                } else {
                    System.err.println("Please, choose right number(1,2)");
                }
            }
        } catch (Exception ex) {
            System.err.println("An occured unexpected exception!");
        }
    }
}