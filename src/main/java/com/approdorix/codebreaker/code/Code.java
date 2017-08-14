package com.approdorix.codebreaker.code;

import java.text.DecimalFormat;
import java.util.Objects;

public class Code {

    private final static DecimalFormat format = new DecimalFormat("000");

    private final char[] digits;

    private Code(char digit1, char digit2, char digit3) {
        digits = new char[3];
        digits[0] = digit1;
        digits[1] = digit2;
        digits[2] = digit3;
    }

    public Code(Integer i) {
        this(charAt(i, 0), charAt(i, 1), charAt(i, 2));
    }

    private static char charAt(Integer i, int position) {
        return String.valueOf(format.format(i)).charAt(position);
    }

    public Integer asInteger() {
        return Integer.valueOf(String.valueOf(digits[0]) + digits[1] + digits[2]);
    }

    @Override
    public String toString() {
        return String.format("[%s] [%s] [%s]", String.valueOf(digits[0]), digits[1], digits[2]);
    }

    @Override
    public boolean equals(Object obj) {
        return Objects.equals(this.toString(), obj.toString());
    }

    public char digitAt(int position) {
        return digits[position];
    }
}
