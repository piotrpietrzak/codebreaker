package com.approdorix.codebreaker.eliminator.rules;

import static com.approdorix.codebreaker.eliminator.rules.DigitRule.*;

import java.util.function.Predicate;

import com.approdorix.codebreaker.code.Code;

public class CodeRule {

    public static Predicate<Code> oneNumberIsCorrectAndWellPlaced(Code code) {
        return digitOnPosition(code.digitAt(0), 0)
                .or(digitOnPosition(code.digitAt(1), 1))
                .or(digitOnPosition(code.digitAt(2), 2)).and(
                        twoDigitsIsNotInCode(code)
                );
    }

    private static Predicate<Code> twoDigitsIsNotInCode(Code code) {
        return twoDigitsIsNotInCode(code, 0, 1).or(
                twoDigitsIsNotInCode(code, 0, 2).or(
                        twoDigitsIsNotInCode(code, 1, 2)
                )
        );
    }

    private static Predicate<Code> twoDigitsIsNotInCode(Code code, int position1, int position2) {
        return digitIsNotInCode(code.digitAt(position1)).and(
                digitIsNotInCode(code.digitAt(position2))
        );
    }

    public static Predicate<Code> nothingIsCorrect(Code code) {
        return digitIsNotInCode(code.digitAt(0))
                .and(digitIsNotInCode(code.digitAt(1)))
                .and(digitIsNotInCode(code.digitAt(2)));
    }

    public static Predicate<Code> oneNumberIsCorrectButWrongPlaced(Code code) {
        return digitIsNotOnPosition(code.digitAt(0), 0).and(
                digitIsNotOnPosition(code.digitAt(1), 1).and(
                        digitIsNotOnPosition(code.digitAt(2), 2))).and(
                digitOnPosition(code.digitAt(0), 1).or(
                        digitOnPosition(code.digitAt(0), 2)
                ).or(
                        digitOnPosition(code.digitAt(1), 0)
                ).or(
                        digitOnPosition(code.digitAt(1), 2)
                ).or(
                        digitOnPosition(code.digitAt(2), 0)
                ).or(
                        digitOnPosition(code.digitAt(2), 1)
                )
        );
    }

    public static Predicate<Code> twoNumbersAreCorrectButWrongPlaced(Code code) {
        return digitIsNotOnPosition(code.digitAt(0), 0).and(
                digitIsNotOnPosition(code.digitAt(1), 1)
        ).and(
                digitIsNotOnPosition(code.digitAt(2), 2)
        ).and(
                firstDigitIsNotInTheCodeButTheRestIs(code).or(
                        firstDigitIsNotInTheCodeButTheRestIs(new Code(26)).or(
                                firstDigitIsNotInTheCodeButTheRestIs(new Code(620))))
        );
    }

    private static Predicate<Code> firstDigitIsNotInTheCodeButTheRestIs(Code code) {
        return digitIsNotInCode(code.digitAt(0)).and(
                digitIsInCode(code.digitAt(1))).and(
                digitIsInCode(code.digitAt(2))
        );
    }
}
