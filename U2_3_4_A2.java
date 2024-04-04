/*
 * Program Purpose: To prompt the user with random multiple choice questions and tell them if they are correct or not when they answer with a letter.
 * Author: Johnson Yep
 */

import java.util.Scanner;

public class U2_3_4_A2 {
    public static void main(String[] args) {
        // declaring variables
        Scanner read = new Scanner(System.in);
        int randomQuestion;
        String answer;
        String[][] multipleChoice = { // array of questions and answers; format: {Question, Choice, Choice, Choice, Answer}
            {"What is the name of the actor that plays Iron Man?", "Robert Downey Jr.", "Chris Evans", "Leonardo DiCaprio", "A"},
            {"What is the largest planet in our solar system?", "Earth", "Jupiter", "Saturn", "B"},
            {"How fast is the average acceleration of gravity on Earth?", "4.45m/s", "1.2km/h", "9.81m/s^2", "C"},
            {"What show is the highest rated episode on IMDb from?", "Breaking Bad", "Vinland Saga", "Avatar: The Last Airbender", "A"}
        };

        // choosing a random question
        randomQuestion = (int)Math.floor(Math.random()*4); // chooses random number between 0-3

        System.out.printf("Question: %s\n", multipleChoice[randomQuestion][0]);

        for (int i = 1; i < multipleChoice[randomQuestion].length - 1; i++) { // loop to print all the possible choices; starts at 1 and ends one before the length of the table because the first and last indexes are the question and answer
            System.out.printf("%5c)  %s\n", (char)96 + i, multipleChoice[randomQuestion][i]); // ASCII value for lowercase 'a' is 97, so 96 + i would make it a, b, c, etc
        }

        // prompting the user for their answer
        System.out.print("\nAnswer: ");
        answer = read.nextLine();
        read.close();

        // checking if the answer is correct
        String correctAnswerLetter = multipleChoice[randomQuestion][multipleChoice.length]; // the last value of the array (which is the answer letter)

        if (answer.equalsIgnoreCase(correctAnswerLetter)) {
            System.out.println("That is correct. Very good!");
        }
        else {
            String correctAnswer = multipleChoice[randomQuestion][(int)correctAnswerLetter.charAt(0) - 64]; // the line of code in the second [] converts the answer letter into a char so that it can then be converted into an int, 64 is then subtracted from it because the ASCII value for A is 65 which leaves us with the table index of the correct answer for the question
            System.out.printf("That is incorrect. The answer was %s", correctAnswer);
        }
    }
}