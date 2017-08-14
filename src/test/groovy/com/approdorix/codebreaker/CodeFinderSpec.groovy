package com.approdorix.codebreaker

import com.approdorix.codebreaker.code.Code
import spock.lang.Specification

import static com.approdorix.codebreaker.CodeFinder.allCodes
import static com.approdorix.codebreaker.CodeFinder.findCode
import static com.approdorix.codebreaker.eliminator.rules.CodeRule.oneNumberIsCorrectAndWellPlaced
import static java.util.stream.Collectors.toList

class CodeFinderSpec extends Specification {
    def "should find code"() {
        when:
            List<Code> resultList = findCode(allCodes()).collect(toList())
        then:
            1 == resultList.size()
            resultList.contains(new Code(42))
    }
    
    def "should eliminate those without certain number on certain position"() {
        when:
            List<Code> remainingNumbers =
                    allCodes().filter(oneNumberIsCorrectAndWellPlaced(new Code(682)))
                            .collect(toList())
        then:
            remainingNumbers.contains(new Code(693))
            remainingNumbers.contains(new Code(783))
            remainingNumbers.contains(new Code(792))
            !remainingNumbers.contains(new Code(793))
            !remainingNumbers.contains(new Code(571))
    }
}
