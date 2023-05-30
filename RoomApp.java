import javax.print.attribute.standard.OrientationRequested;

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
        this.orientation = Orientation.Nord;
    }

    // Constructeur avec une grille spécifique en double parametre
    public RoomApp(int gridSize) {
        this.grid = new int[gridSize][gridSize];
        this.x = 0;
        this.y = 0;
        this.orientation = Orientation.Nord;
    }

    // Méthode pour modifier l'orientation de l'objet selon mouvement vers la guache ou la droite
    public void turn(String laterality) {
        switch (orientation) {
            case Nord:
                if (laterality == "G") {
                    this.orientation = Orientation.Ouest;
                } else if (laterality == "D"){
                    this.orientation = Orientation.Est;
                }
                break;
            case Est:
            if (laterality == "G") {
                this.orientation = Orientation.Nord;
            } else if (laterality == "D"){
                this.orientation = Orientation.Sud;
            }
                break;
            case Sud:
            if (laterality == "G") {
                this.orientation = Orientation.Est;
            } else if (laterality == "D"){
                this.orientation = Orientation.Ouest;
            }
                break;
            case Ouest:
            if (laterality == "G") {
                this.orientation = Orientation.Sud;
            } else if (laterality == "D"){
                this.orientation = Orientation.Nord;
            }
                break;
        }
    }

    // Méthode pour faire avancer l'objet dans la grille et vérification que toujours dans les limites
    public void move() {
        switch (orientation) {
            case Nord:
                if (y > 0) {
                    y--;
                }
                break;
            case Est:
                if (x <= grid.length) {
                    x++;
                }
                break;
            case Sud:
                if (y <= grid.length) {
                    y++;
                }
                break;
            case Ouest:
                if (x > 0) {
                    x--;
                }
                break;
        }
    }

    // Classe enum pour l'orientation
    public enum Orientation {
        Nord,
        Est,
        Sud,
        Ouest
    }

    public static void main(String[] args) {
        java.util.Scanner entree = new java.util.Scanner(System.in);

        System.out.println("RoomJava a votre service ! ");
        System.out.print("Voulez vous une grille standard de 10X10 (Y/N) ?: ");
        String init = entree.next();
        RoomApp newCleaning;
        if (init.equals("y")||init.equals("Y")){
            newCleaning = new RoomApp();
        } else {
            System.out.print("Indiquer la taille de la grille :"); 
            Integer gridSize = entree.nextInt();
            newCleaning = new RoomApp(gridSize);
        }
        System.out.print("Voulez vous commencer à la position 0 0 orientation Nord (Y/N) ?: ");
        init = entree.next();
        if (init.equals("Y")){       
        System.out.println("Position initiale : (" + newCleaning.x + ", " + newCleaning.y + ")");
        System.out.println("Orientation initiale : " + newCleaning.orientation);
        } else {
            System.out.print("Indiquer la position x de RoomJava entre 0 et " + newCleaning.grid.length + " : "); 
            Integer x = entree.nextInt();
            newCleaning.x = x;
            System.out.print("Indiquer la position y de RoomJava entre 0 et " + newCleaning.grid.length + " : "); 
            Integer y = entree.nextInt();
            newCleaning.y = y;
            System.out.print("Indiquer l'orientation de RoomJava : "); 
            String orientation = entree.next();
            newCleaning.orientation = Orientation.valueOf(orientation);  
            System.out.println("Position initiale : (" + newCleaning.x + ", " + newCleaning.y + ")");
            System.out.println("Orientation initiale : " + newCleaning.orientation);
        }
        //test
        newCleaning.turn("D");
        newCleaning.move();
        System.out.println("Nouvelle position : (" + newCleaning.x + ", " + newCleaning.y + ")");
        System.out.println("Orientation initiale : " + newCleaning.orientation);

        newCleaning.turn("D");
        newCleaning.move();
        System.out.println("Nouvelle position : (" + newCleaning.x + ", " + newCleaning.y + ")");
        System.out.println("Orientation initiale : " + newCleaning.orientation);
    }
}