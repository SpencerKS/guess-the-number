import java.util.Scanner;

public class GuessingGame {

    static boolean guessAgain = true;
    boolean playAgain = false;
    String invalid = "INVALID_INPUT";

    static GuessingGame game = new GuessingGame();
    Computer npc = new Computer();
    Player player = new Player();
    Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        game.start();
    }

    //using different methods to control the different points in the game

    public void start() {
        System.out.println("Hello! What is your name!");
        try {
            player.setName(in.nextLine());
        }catch(Exception e) {
            System.out.println(invalid);
            throw e;
        }
        if (player.getName().equals("")){
            player.setName("whoever you are");
        }
        play();
    }

    public void play() {
        if (playAgain) {
            System.out.println("I am thinking of a number between 1 and 20.");
        }
        else {
            System.out.println("Well, " + player.getName() + ", I am thinking of a number between 1 and 20.");
        }
        npc.setNumber(Math.floor(Math.random()*20));
        System.out.println("Can you guess what it is?");
        guess();
    }

    public void guess() {
        System.out.println("Take a guess: ");
        try {
            player.setGuess(in.nextInt());
        }catch (Exception e) {
            System.out.println(invalid);
            throw e;
        }
        System.out.println(result());
        if (guessAgain) {
            guess();
        }
        else {
            playAgain();
        }
    }

    public String compare(int guess, int number) {
        if (guess > number) {
            return "TOO_HIGH";
        }
        else if (guess < number) {
            return "TOO_LOW";
        }
        else {
            return "CORRECT";
        }
    }

    public String result() {
        switch (game.compare(player.getGuess(), npc.getNumber())) {
            case "TOO_HIGH" : player.incorrect(); guessAgain = true; return "Your guess is too high.";
            case "TOO_LOW" : player.incorrect(); guessAgain = true; return "Your guess is too low.";
            case "CORRECT" : guessAgain = false; return "You did it! You guessed my number in "+player.getTries();
            default: return "I think my programming is broken...";
        }
    }

    public void playAgain() {
        System.out.println("Would you like to play again?");
        System.out.println("(please enter N twice if no)"); //for some reason, it doesn't take N or n the first time
        System.out.println("Y / N");
        if (in.nextLine().equals("Y") || in.nextLine().equals("y")) {
            playAgain = true;
            player.resetTries();
            play();
        }
        else if (in.nextLine().equals("N") || in.nextLine().equals("n")) {
            end();
            //I don't know what's wrong with it, but I'll fix it later
        }
        else {
            System.out.println(invalid);
            playAgain();
        }
    }

    public void end() {
        in.close();
        System.exit(0);
    }
}

class Computer {
    private int number;

    public void setNumber(double doubleNum) {
        long longNum = Math.round(doubleNum);
        this.number = Math.toIntExact(longNum);
    }
    public int getNumber() {
        return this.number;
    }
}

class Player {
    private String name;
    private int guess;
    private int tries = 1;

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return this.name;
    }
    public void setGuess(int number) {
        this.guess = number;
    }
    public int getGuess() {
        return this.guess;
    }
    public void incorrect() {
        this.tries++;
    }
    public String getTries() {
        return (this.tries == 1 ? "1 try." : (this.tries)+" tries.");
    }
    public void resetTries() {
        this.tries = 1;
    }
}
