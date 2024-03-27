package org.example;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Quiz {
    File quizFile;
    public Quiz(String fileName){
        String file = Objects.requireNonNull(this.getClass().getClassLoader().getResource(fileName)).getFile();
        quizFile = new File(file);
    }

    /*public void displayQuestions() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        List<Question> questions = mapper.readValue(quizFile, new TypeReference<List<Question>>() {
        });

        questions.stream()
                .map(Question::getPytanie)
                .forEach(System.out::println);
    }*/

    public void play() throws IOException {
        Scanner scanner = new Scanner(System.in);
        int points = 0;
        int numberOfQuestions = 0;
        ObjectMapper mapper = new ObjectMapper();
        List<Question> questions = mapper.readValue(quizFile, new TypeReference<List<Question>>() {
        });

        for (Question question:questions){
            System.out.println(question.getPytanie());
            System.out.println("a: " + question.getA());
            System.out.println("b: " + question.getB());
            System.out.println("c: " + question.getC());
            System.out.println("Podaj odpowiedź (a,b lub c): ");

            String answer = scanner.nextLine();

            if (question.getPrawidłowaOdpowiedź().equals(answer)){
                System.out.println("To prawidłowa odpowiedź");
                points++;
                numberOfQuestions++;
            } else{
                System.out.println("Niestety, to błędna odpowiedź. Prawidłowa odpowiedź to: " + question.getPrawidłowaOdpowiedź());
                numberOfQuestions++;
            }
        }
        System.out.println("To już koniec gry. Twoja liczba punktów: " + points + " na możliwych: " + numberOfQuestions);
        scanner.close();
    }
}
