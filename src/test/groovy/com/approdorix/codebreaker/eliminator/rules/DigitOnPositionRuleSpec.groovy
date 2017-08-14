package com.approdorix.codebreaker.eliminator.rules

import com.approdorix.codebreaker.code.Code
import spock.lang.Specification

import java.util.function.Predicate

import static com.approdorix.codebreaker.CodeFinder.allCodes
import static com.approdorix.codebreaker.eliminator.rules.DigitRule.digitIsNotOnPosition
import static com.approdorix.codebreaker.eliminator.rules.DigitRule.digitOnPosition
import static java.util.stream.Collectors.toList

class DigitOnPositionRuleSpec extends Specification {
    def "should return true for test on all positions"() {
        given:
            Predicate<Code> rule = digitOnPosition(digit, position)
        when:
            boolean result = rule.test(new Code(123))
        then:
            result
        where:
            digit       | position
            '1' as char | 0
            '2' as char | 1
            '3' as char | 2
    }
    
    def "should return valid return for 682 pattern"() {
        given:
            Predicate<Code> rule = digitOnPosition(digit, position)
        when:
            boolean result = rule.test(new Code(682))
        then:
            result == expected
        where:
            digit       | position || expected
            '6' as char | 0        || true
            '5' as char | 0        || false
            '7' as char | 0        || false
            '8' as char | 1        || true
            '7' as char | 1        || false
            '9' as char | 1        || false
            '2' as char | 2        || true
            '1' as char | 2        || false
            '3' as char | 2        || false
    }
    
    def "should return false for test on all positions"() {
        given:
            Predicate<Code> rule = digitOnPosition(digit, position)
        when:
            boolean result = rule.test(new Code(456))
        then:
            !result
        where:
            digit       | position
            '1' as char | 0
            '2' as char | 1
            '3' as char | 2
    }
    
    def "should return exactly one code"() {
        when:
            List<Code> remainingNumbers = allCodes().filter(
                    digitOnPosition('1' as char, 0) &
                            digitOnPosition('2' as char, 1) &
                            digitOnPosition('3' as char, 2)
            ).collect(toList())
        then:
            remainingNumbers.toList().size() == 1
            remainingNumbers.contains(new Code(123))
    }
    
    def "should eliminate those with 1 on first position and not 2 on second and 3rd position"() {
        when:
            List<Code> remainingNumbers =
                    allCodes()
                            .filter(digitIsNotOnPosition('1' as char, 0))
                            .filter(digitOnPosition('2' as char, 1))
                            .filter(digitOnPosition('2' as char, 2))
                            .collect(toList())
        then:
            remainingNumbers.contains(new Code(222))
            remainingNumbers.contains(new Code(22))
            !remainingNumbers.contains(new Code(122))
            !remainingNumbers.contains(new Code(212))
            !remainingNumbers.contains(new Code(221))
    }
}
