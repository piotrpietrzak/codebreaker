package com.approdorix.codebreaker.eliminator.rules

import com.approdorix.codebreaker.code.Code
import spock.lang.Specification
import spock.lang.Unroll

import java.util.function.Predicate

class CodeRuleSpec extends Specification {
    @Unroll("one number from #testCode should be correct and well placed")
    def "should one number is correct and well placed"() {
        given:
            Code code = new Code(123)
        when:
            Predicate<Code> predicate = CodeRule.oneNumberIsCorrectAndWellPlaced(new Code(testCode))
            Boolean result = predicate.test(code)
        then:
            result == expected
        where:
            testCode || expected
            123      || false
            124      || false
            134      || false
            144      || true
    }
}
