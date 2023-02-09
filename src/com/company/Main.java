package com.company;

import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        File file = new File("input.txt");
        File files = new File("output.txt");
        StandartFileReader fileRead = new StandartFileReader(file);
        fileRead.readLines();
    }

}

