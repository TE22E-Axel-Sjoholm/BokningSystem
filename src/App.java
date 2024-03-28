import java.util.Scanner;

public class App {
    public static int scene = 0;
    public static boolean bokstav;
    public static String[][] data;
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) throws Exception {
        
        while(scene == 0) {
            bokstav = false;
            System.out.println(" ");
            System.out.println("1. Boka resa");
            System.out.println("2. Se lediga platser");
            System.out.println("3. Info");
            System.out.println("4. Sök efter resa");
            System.out.println("5. Avsluta");
            String userInput = scanner.nextLine();
            checkIfLetter(userInput);
            if(bokstav == false && Integer.parseInt(userInput) == 5){
                System.exit(0);
            } else if(bokstav == false && Integer.parseInt(userInput) >= 1 && Integer.parseInt(userInput) <= 3){
                scene = Integer.parseInt(userInput);
            }
        }
    }

    public static void infoMeny(){
        while (scene == 3) {
            bokstav = false;
            System.out.println(" ");
            System.out.println("");
            System.out.println("");
            System.out.println("");
            System.out.println("Tryck enter för att avlsuta");
            System.out.println(" ");
            String userInput = scanner.nextLine();
            checkIfLetter(userInput);

        }
    }
    
    static void checkIfLetter(String userInput){
        if(userInput.isEmpty()){
            System.out.println("Skriv en siffra, inte en bokstav");
        } else{
            for (int i = 0; i < userInput.length(); i++) {
                char letter = userInput.charAt(i);
                if(!Character.isDigit(letter)){
                    bokstav = true;
                    System.out.println("Skriv en siffra, inte en bokstav");
                    i = (userInput.length()) + 1;
                } 
            }
        }
    }
}
