package com.approdorix.codebreaker;

import static com.approdorix.codebreaker.eliminator.rules.CodeRule.*;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import com.approdorix.codebreaker.code.Code;

class CodeFinder {

    static Stream<Code> findCode(Stream<Code> codes) {
        return codes
                .filter(twoNumbersAreCorrectButWrongPlaced(new Code(206)))
                .filter(oneNumberIsCorrectAndWellPlaced(new Code(682)))
                .filter(oneNumberIsCorrectButWrongPlaced(new Code(614)))
                .filter(oneNumberIsCorrectButWrongPlaced(new Code(780)))
                .filter(nothingIsCorrect(new Code(738)));
    }

    static Stream<Code> allCodes() {
        return IntStream.range(0, 1000).mapToObj(Code::new);
    }
}
