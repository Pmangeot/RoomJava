public class RoomApp {
    private int[][] grid;
    private int x;
    private int y;
    private Orientation orientation;

    // Constructeur par défaut avec une grille de 10x10
    public RoomApp() {
        this.grid = new int[10][10];
        this.x = 0;
        this.y = 0;
        this.orientation = Orientation.N;
    }

    // Constructeur avec une grille spécifique en double parametre
    public RoomApp(int x, int y) {
        this.grid = new int[x][y];
        this.x = 0;
        this.y = 0;
        this.orientation = Orientation.N;
    }

    // Méthode pour modifier l'orientation de l'objet selon mouvement vers la guache ou la droite
    public void turn(char laterality) {
        switch (orientation) {
            case N:
                if (laterality == 'G') {
                    this.orientation = Orientation.O;
                } else if (laterality == 'D'){
                    this.orientation = Orientation.E;
                }
                break;
            case E:
            if (laterality == 'G') {
                this.orientation = Orientation.N;
            } else if (laterality == 'D'){
                this.orientation = Orientation.S;
            }
                break;
            case S:
            if (laterality == 'G') {
                this.orientation = Orientation.E;
            } else if (laterality == 'D'){
                this.orientation = Orientation.O;
            }
                break;
            case O:
            if (laterality == 'G') {
                this.orientation = Orientation.S;
            } else if (laterality == 'D'){
                this.orientation = Orientation.N;
            }
                break;
        }
    }

    // Méthode pour faire avancer l'objet dans la grille et vérification que toujours dans les limites
    public void move() {
        switch (orientation) {
            case S:
                if (y > 0) {
                    y--;
                }
                break;
            case E:
                if (x < grid.length) {
                    x++;
                }
                break;
            case N:
                if (y < grid.length) {
                    y++;
                }
                break;
            case O:
                if (x > 0) {
                    x--;
                }
                break;
        }
    }

    // Classe enum pour l'orientation
    public enum Orientation {
        N,
        E,
        S,
        O
    }

    public static void main(String[] args) {
        java.util.Scanner entree = new java.util.Scanner(System.in);

        System.out.println("RoomJava a votre service ! ");
        String answer = null;
        RoomApp newCleaning = null;
        boolean nextPrompt = false;
        while (!nextPrompt){  
            System.out.print("Voulez vous une grille standard de 10X10 (Y/N) ?: ");
            answer = entree.next();
            if (answer.equals("y")||answer.equals("Y")){
                newCleaning = new RoomApp();
                nextPrompt = true;
            } else if (answer.equals("N")||answer.equals("n")){
                System.out.print("Indiquer la hauteur de la grille :"); 
                Integer Y = entree.nextInt();  
                System.out.print("Indiquer la largeur de la grille :"); 
                Integer X = entree.nextInt();  
                newCleaning = new RoomApp(X,Y);
                nextPrompt = true;
            }
        }
        nextPrompt = false;
        while(!nextPrompt){
            System.out.print("Voulez vous commencer à la position 0 0 orientation Nord (Y/N) ?: ");
            answer = entree.next();
            if (answer.equals("y")||answer.equals("Y")){      
                System.out.println("Position initiale : (" + newCleaning.x + ", " + newCleaning.y + ")");
                System.out.println("Orientation initiale : " + newCleaning.orientation);
                nextPrompt = true;
            } else if (answer.equals("N")||answer.equals("n")){
                boolean validEntry = false;
                Integer x = null;
                while (!validEntry){
                    System.out.print("Indiquer la position x de RoomJava entre 0 et " + newCleaning.grid.length + " : "); 
                    x = entree.nextInt();
                    if (x>=0 && x <= newCleaning.grid.length){
                        validEntry = true;
                    }
                }
                newCleaning.x = x;
                validEntry = false;
                Integer y = null;
                while (!validEntry){
                    System.out.print("Indiquer la position y de RoomJava entre 0 et " + newCleaning.grid[0].length + " : "); 
                    y = entree.nextInt();
                    if (y>=0 && x <= newCleaning.grid[0].length){
                        validEntry = true;
                    }
                }
                newCleaning.y = y;
                validEntry = false;
                System.out.print("Indiquer l'orientation de RoomJava (N, S, E ou O): "); 
                String orientation = entree.next();
                newCleaning.orientation = Orientation.valueOf(orientation);  
                System.out.println("Position initiale : (" + newCleaning.x + ", " + newCleaning.y + ")");
                System.out.println("Orientation initiale : " + newCleaning.orientation);
                nextPrompt = true;
            }
        }

        System.out.print("Merci de fournir les instructions D (droite) G (gauche) A (avancer) sans espace et en majuscule: ");
        String instructions = entree.next();
        //on créé un array avec les character D G ou A
        char[] instructionArray = instructions.toCharArray();
        // On lance les méthodes de classe pour chaque instruction
        for (char instruction : instructionArray) {
            if (instruction == 'D'|| instruction == 'G'){
                newCleaning.turn(instruction);
            } else if (instruction == 'A'){
                newCleaning.move();
            }
        System.out.println("Nouvelle position et orientation: (" + newCleaning.x + ", " + newCleaning.y + ") "+ newCleaning.orientation);
        }
    }
}
