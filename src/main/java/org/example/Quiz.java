package org.example;

import java.io.File;

public class Quiz {
    File quizFile;
    public Quiz(String fileName){
        String file = this.getClass().getClassLoader().getResource(fileName).getFile();
        quizFile = new File(file);
    }
}
