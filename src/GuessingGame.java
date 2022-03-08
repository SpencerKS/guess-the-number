import java.util.Scanner;

public class GuessingGame {

    static boolean guessAgain = true;

    static GuessingGame game = new GuessingGame();
    Computer npc = new Computer();
    Player player = new Player();
    Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        game.start();
    }

    public void start() {
        System.out.println("Hello! What is your name!");
        player.setName(in.nextLine());
        play();
    }

    public void play() {
        System.out.println("Well, " + player.getName() + ", I am thinking of a number between 1 and 20.");
        npc.setNumber(Math.floor(Math.random()*20));
        System.out.println("Can you guess what it is?");
        guess();
    }

    public void guess() {
        System.out.println("Take a guess: ");
        player.setGuess(in.nextInt());
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
        System.out.println("Y / N");
        if (in.next().equalsIgnoreCase("Y")) {
            play();
        }
        else {
            end();
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
}
