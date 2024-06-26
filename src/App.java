import java.util.Scanner;

public class App {
    public static int scene = 0;
    public static boolean bokstav;
    public static String[][] data = new String[21][5];
    public static double price;
    static Scanner scanner = new Scanner(System.in);
    public static double priceSum(int k) {
        if (k == 21) {
            return 0;
        }
        return Double.parseDouble(data[k][4]) + priceSum(k + 1);
    }
    public static void main(String[] args) throws Exception {
        for (int i = 0; i < data.length; i++) { //går igenom hela arrayen och sätter allt till 0
            for (int j = 0; j < data[i].length; j++) {
                data[i][j] = "0";
            }
        }
        while(scene == 0) { //visar huvudmenyn
            System.out.println("1. Boka resa");
            System.out.println("2. Se lediga platser");
            System.out.println("3. Info");
            System.out.println("4. Sök efter resa");
            System.out.println("5. Ta bort en bokning");
            System.out.println("6. Räkna sammanlagd inkomst");
            System.out.println("7. Se sorterad lista av bokningar");
            System.out.println("8. Avsluta");
            String userInput = scanner.nextLine();
            bokstav = false;
            checkIfLetter(userInput);
            if(bokstav){
                continue;
            } else if(Integer.parseInt(userInput) == 8){
                    System.exit(0);
            } else if(Integer.parseInt(userInput) == 1 || Integer.parseInt(userInput) == 2 || Integer.parseInt(userInput) == 3 || Integer.parseInt(userInput) == 4 || Integer.parseInt(userInput) == 5 || Integer.parseInt(userInput) == 6 || Integer.parseInt(userInput) == 7){
                scene = Integer.parseInt(userInput); // sätter scene som det man har skrivit in
                infoMeny();
                booking();
                seatMap();
                search();
                unbook();
                list();
            }
        }
        if(scene == 6){
            System.out.println(priceSum(0) + "kr");
            scene = 0;
        }
    }
    public static void seatMap(){ //skriver ut karta för säten som visar dem som sitt nummer om dem är obokade och X om dem är bokade
        while (scene == 2) {
            for (int i = 0; i < 20; i++) {
                if (Integer.parseInt(data[i+1][1]) == 0) {
                    String platsNummer = (i + 1 < 10) ? (i + 1) + " " : Integer.toString(i + 1);
                    System.out.print("|" + platsNummer + "|");
                } else {
                    System.out.print("|X |");
                }
                if (i % 4 == 3 || i == 20) {
                    System.out.println();
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println("Tryck enter för att komma tillbaks till huvudmenyn");
            scanner.nextLine();
            scene = 0;
        }
    }
    public static void infoMeny(){ //visar infomeny, enter för att klicka ut
        while(scene == 3){
            System.out.println(" ");
            System.out.println("Pris för vuxen: 299.90kr");
            System.out.println("Pris för barn < 18: 149.90kr");
            System.out.println("Bussen går 16:30 från stationen");
            System.out.println("Vid ytterligare frågor, kontakta oss på: +46 072-4482422");
            System.out.println("Tryck enter för huvudmenyn");
            scanner.nextLine();
            scene = 0;   
        }
    }
    static void checkIfLetter(String userInput){ // kollar om string userInput har en bokstav eller symbol i sig, om det har det skriver den ut att användaren ska skriva en siffra.
        if(userInput == ""){
            System.out.println("Du skrev inget");
            bokstav = true;
        } else{
            for (int i = 0; i < userInput.length(); i++) {
                char letter = userInput.charAt(i);
                if(!Character.isDigit(letter)){
                    bokstav = true;
                    System.out.println("Skriv en siffra, inte en bokstav eller symbol");
                    i = (userInput.length()) + 1;
                } 
            }
        }
    }
    public static void priceCalc(int userAge){ //räknar ut priser beroende på age som blir insatt
        if(2024 - (userAge/10000) >= 18){
            price = 299.90;
        }
        if(2024 - (userAge/10000) < 18){
            price = 149.90;
        }
    }
    public static void booking(){ //påbörjar processes av att boka en plats
        while (scene == 1) {
            System.out.println("Vill du ha en fönsterplats? Svara Ja eller Nej");
            String userInput = scanner.nextLine();
            if(userInput.equalsIgnoreCase("nej") || userInput.equalsIgnoreCase("no") || userInput.equalsIgnoreCase("ne") || userInput.equalsIgnoreCase("n") || userInput.equalsIgnoreCase("nah")){
            } else if(userInput.equalsIgnoreCase("ja") || userInput.equalsIgnoreCase("yes") || userInput.equalsIgnoreCase("ya") || userInput.equalsIgnoreCase("y") || userInput.equalsIgnoreCase("yea") || userInput.equalsIgnoreCase("yep")){
                String[] windows = new String[100]; // aray för alla fönsterplatser
                for(int i = 0; i < 10; i++){
                    if(i < 5){
                        windows[i] = Integer.toString((4*i+1));
                    } else if(i >= 5){
                        windows[i] = Integer.toString((i-4)*4);
                    }
                }
                System.out.println("Här de lediga fönsterplatserna: ");
                for(int i = 0; i < 10; i++){ // skriver ut fönsterplatser
                    if(i < 9) {
                        if(Integer.parseInt(data[Integer.parseInt(windows[i])][1]) == 0){
                            System.out.print(windows[i] + " ");
                        }
                    }
                    if(i == 9){
                        if(Integer.parseInt(data[Integer.parseInt(windows[i])][1]) == 0){
                            System.out.println(windows[i]);
                        }
                    }                
                }
            } else{
                System.out.println("Skriv ja eller nej");
                continue;
            }
            System.out.println("Vilken plats vill du boka? Om du inte vet, kolla lediga platser i huvudmenyn.");
            String userPlace = scanner.nextLine();
            bokstav = false;
            checkIfLetter(userPlace);
            if(bokstav || Integer.parseInt(userPlace) >= 21 || Integer.parseInt(userPlace) <= 0){    //kollar så att platsen är giltig
                System.out.println("Skriv ett en plats mellan 1 - 20");    
                continue;
            } else if(data[Integer.parseInt(userPlace)][1] == "1"){ // kollar om platsen är bokad
                System.out.println("Platsen är upptagen, kolla efter lediga platser i huvudmenyn.");
            } else if(data[Integer.parseInt(userPlace)][1] == "0"){ // om den är obokad
                System.out.println("Platsen är ledig, Skriv ditt namn:");
                String userName = scanner.nextLine();
                String userAge = "";
                bokstav = false;
                while(userAge.length() != 8 && !bokstav) { //medans man har skrivit fel på åldern
                    System.out.println("Vad är ditt födelsedatum? ÅÅÅÅMMDD");
                    userAge = scanner.nextLine();
                    bokstav = false;
                    checkIfLetter(userAge);
                    if(userAge.length() == 8 && !bokstav){ // om man har skrivit rätt på åldern
                        priceCalc(Integer.parseInt(userAge));
                        System.out.println("Platsen är bokad, det kostar "+price+"0kr.");
                        data[Integer.parseInt(userPlace)][1] = "1";
                        data[Integer.parseInt(userPlace)][2] = userName;
                        data[Integer.parseInt(userPlace)][3] = userAge;
                        data[Integer.parseInt(userPlace)][4] = Double.toString(price);
                    }
                    bokstav = false;
                }
                scene = 0;
            }
        }
    }
    public static void unbook() { // avbokningsmetod
        while (scene == 5) {
            System.out.println("Vilken plats vill du avboka?");
            String userInput = scanner.nextLine();
            checkIfLetter(userInput);
            if(!bokstav){ //kollar så att input är en siffra
                if(data[Integer.parseInt(userInput)][1] == "1"){ // avbokar platsen om den är bokad
                    data[Integer.parseInt(userInput)][1] = "0";
                    data[Integer.parseInt(userInput)][2] = "0";
                    data[Integer.parseInt(userInput)][3] = "0";
                    data[Integer.parseInt(userInput)][4] = "0";
                    System.out.println("Platsen är avbokad");
                } else{ // om den inte är bokad
                    System.out.println("Platsen är obokad");
                }
            }
            scene = 0;
        }
    }
    public static void search() { // sökningsmetod
        while (scene == 4) {   
            boolean nameSearch = false;
            boolean yearSearch = false;
            bokstav = false;
            System.out.println("Sök födelsedatum eller namn");
            String userInput = scanner.nextLine();
            if (userInput.length() == 8) { // avgör om det är age eller name search
                checkIfLetter(userInput);
                if (!bokstav) {
                    yearSearch = true;
                }
            } else {
                nameSearch = true;
            }
            if (yearSearch) { // om det är age search
                for (int i = 0; i < data.length; i++) {
                    if (data[i][3].equals(userInput)) {
                        System.out.println("Plats: " + i);
                        System.out.println("Namn: " + data[i][2]);
                        System.out.println("Födelsedatum: " + data[i][3]);
                        System.out.println("Pris: " + data[i][4] + "kr" );
                    }
                }
                System.out.println("Tryck enter för att komma tillbaks till huvudmenyn.");
                scanner.nextLine();
                scene = 0;
            } else if (nameSearch) { // om det är name search
                for (int i = 0; i < data.length; i++) {
                    if (data[i][2].equalsIgnoreCase(userInput)) {
                        System.out.println("Plats: " + i);
                        System.out.println("Namn: " + data[i][2]);
                        System.out.println("Födelsedatum: " + data[i][3]);
                        System.out.println("Pris: " + data[i][4] + "kr");
                    }
                }
                System.out.println("Tryck enter för att komma tillbaks till huvudmenyn.");
                scanner.nextLine();
                scene = 0;
            } else{ // om det inte finns några resultat för search
                System.out.println("Inga resultat.");
                System.out.println("Tryck enter för att komma tillbaks till huvudmenyn.");
                scanner.nextLine();
                scene = 0;
            }
        }
    }
    public static void list(){ 
        while (scene == 7){
           System.out.println("Comming soon.. kanske");
           scene = 0;
        }
    }
}
