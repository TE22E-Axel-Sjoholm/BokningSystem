import java.util.Scanner;

public class App {
    public static int scene = 0;
    public static boolean bokstav;
    public static Object[][] data = new Object[21][5];
    public static double price;
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) throws Exception {
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                data[i][j] = 0;
            }
        }
        while(scene == 0) {
            System.out.println("1. Boka resa");
            System.out.println("2. Se lediga platser");
            System.out.println("3. Info");
            System.out.println("4. Sök efter resa");
            System.out.println("5. Räkna sammanlagd inkomst");
            System.out.println("6. Avsluta");
            String userInput = scanner.nextLine();
            bokstav = false;
            checkIfLetter(userInput);
            if(bokstav){
                continue;
            } else if(Integer.parseInt(userInput) == 6){
                    System.exit(0);
                } else if(Integer.parseInt(userInput) == 1 || userInput == "2" || userInput == "3" || userInput == "4" || userInput == "5"){
                    scene = 1;
                }
                infoMeny();
                booking(); 
            
            
        }
    }

    public static void infoMeny(){
        while (scene == 3) {
            System.out.println(" ");
            System.out.println("Pris för vuxen: 299.90kr");
            System.out.println("Pris för barn < 18: 149.90kr");
            System.out.println("Bussen går 16:30 från stationen");
            System.out.println("Vid ytterligare frågor, kontakta oss på: 0724482422");
            System.out.println("Tryck enter för att avlsuta");
            scanner.nextLine();
            scene = 0;   
        }
    }
    
    static void checkIfLetter(String userInput){
        if(userInput.equals("")){
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
    public static void priceCalc(int userAge){
        if(2024 - (userAge/10000) >= 18){
            price = 299.90;
        }
        if(2024 - (userAge/10000) < 18){
            price = 149.90;
        }
    }
    public static void booking(){
        while (scene == 1) {
            System.out.println("Vilken plats vill du boka? Om du inte vet, kolla lediga platser i huvudmenyn.");
            String userPlace = scanner.nextLine();
            bokstav = false;
            checkIfLetter(userPlace);
            if(bokstav){
                continue;
            } else if(data[Integer.parseInt(userPlace)][1].equals(1)){
                System.out.println("Platsen är upptagen, kolla efter lediga platser i huvudmenyn.");
            } else if(data[Integer.parseInt(userPlace)][1].equals(0)){
                System.out.println("Platsen är ledig, Skriv ditt namn:");
                String userName = scanner.nextLine();
                String userAge = "";
                bokstav = false;
                while(userAge.length() != 8 && bokstav == false) {
                    System.out.println("Vad är ditt födelsedatum? ÅÅÅÅMMDD");
                    userAge = scanner.nextLine();
                    bokstav = false;
                    checkIfLetter(userAge);
                    if(userAge.length() == 8 && bokstav == false){
                        priceCalc(Integer.parseInt(userAge));
                        System.out.println("Platsen är bokad, det kostar "+price+"kr.");
                        data[Integer.parseInt(userPlace)][1] = "1";
                        data[Integer.parseInt(userPlace)][2] = userName;
                        data[Integer.parseInt(userPlace)][3] = Integer.parseInt(userAge);
                        data[Integer.parseInt(userPlace)][4] = price;
                    }
                    bokstav = false;
                }
                scene = 0;
            }
        }
    }
}
