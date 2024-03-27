import java.util.Scanner;

public class App {
    public static int scene = 0;
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        boolean bokstav;
        while(scene == 0) {
            bokstav = false;
            System.out.println(" ");
            System.out.println("1. Boka resa");
            System.out.println("2. Se lediga platser");
            System.out.println("3. Info");
            System.out.println("4. Avsluta");
            System.out.println(" ");
            String userInput = scanner.nextLine();
            for (int i = 0; i < userInput.length(); i++) {
                char letter = userInput.charAt(i);
                if(!Character.isDigit(letter)){
                    bokstav = true;
                    System.out.println("Skriv en siffra, inte en bokstav");
                    i = (userInput.length()) + 1;
                } 
            }
            if(bokstav == false && Integer.parseInt(userInput) == 4){
                System.exit(0);
            } else if(bokstav == false && Integer.parseInt(userInput) >= 1 && Integer.parseInt(userInput) <= 3){
                scene = Integer.parseInt(userInput);
            }
            System.out.println(scene);
        }
    }

}
