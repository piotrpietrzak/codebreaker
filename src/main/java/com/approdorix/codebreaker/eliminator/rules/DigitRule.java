package com.approdorix.codebreaker.eliminator.rules;

import java.util.function.Predicate;

import com.approdorix.codebreaker.code.Code;

class DigitRule {

    static Predicate<Code> digitOnPosition(char digit, int position) {
        return new DigitOnPositionRule<>(digit, position);
    }

    static Predicate<Code> digitIsNotOnPosition(char digit, int position) {
        return new DigitOnPositionRule<>(digit, position).negate();
    }

    static Predicate<Code> digitIsInCode(char digit) {
        return digitOnPosition(digit, 0)
                .or(digitOnPosition(digit, 1)
                        .or(digitOnPosition(digit, 2)));
    }

    static Predicate<Code> digitIsNotInCode(char digit) {
        return digitIsInCode(digit).negate();
    }
}
