import java.util.Random;
import java.util.Scanner;

public class NG {
    public static void main(String[] args) {
        int minN = 1;
        int maxN = 100;
        int attempts = 0;
        int score = 0;

        Random random = new Random();
        int randomNumber = random.nextInt(maxNumber - minNumber + 1) + minNumber;

        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Number Guessing Game!");
        boolean isCorrect = false;
        while (!isCorrect) {
            System.out.print("Enter your guess between " + minNumber + " and " + maxNumber + ": ");
            int guess = scanner.nextInt();
            attempts++;

            if (guess == randomNumber) {
                isCorrect = true;
                score = maxNumber - attempts + 1;
                System.out.println("Congratulations! You guessed the number correctly in " + attempts + " attempts.");
                System.out.println("Your score: " + score);
            } else if (guess < randomNumber) {
                System.out.println("The number is higher. Try again!");
            } else {
                System.out.println("The number is lower. Try again!");
            }
        }

        scanner.close();
    }
}
