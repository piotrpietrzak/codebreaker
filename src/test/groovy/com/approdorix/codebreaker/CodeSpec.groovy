package com.approdorix.codebreaker

import com.approdorix.codebreaker.code.Code
import spock.lang.Specification

class CodeSpec extends Specification {
    def "should provide the same number from code"() {
        given:
            Code code = new Code(number)
        when:
            Integer codeNumber = code.asInteger()
        then:
            codeNumber == number
        where:
            number << [123, 12, 1]
    }
    
    def "equals should work for instances created for the same number"() {
        when:
            Code codeOne = new Code(number)
            Code codeTwo = new Code(number)
        then:
            codeOne == codeTwo
        where:
            number << [123, 12, 1]
    }
}
