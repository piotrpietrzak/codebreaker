package com.approdorix.codebreaker.eliminator.rules;

import java.util.function.Predicate;

import com.approdorix.codebreaker.code.Code;

class DigitOnPositionRule<T extends Code> implements Predicate<T> {

    private final char digit;

    private final int position;

    DigitOnPositionRule(char digit, int position) {
        this.digit = digit;
        this.position = position;
    }

    @Override
    public boolean test(T t) {
        return t.digitAt(position) == digit;
    }
}
