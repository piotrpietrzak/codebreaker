package com.approdorix.codebreaker;

import static com.approdorix.codebreaker.CodeFinder.*;

public class Main {

    public static void main(String[] args) {
        findCode(allCodes()).forEach(System.out::println);
    }
}
