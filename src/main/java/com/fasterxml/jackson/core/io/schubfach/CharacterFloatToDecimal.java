package com.fasterxml.jackson.core.io.schubfach;

public final class CharacterFloatToDecimal extends AbstractFloatToDecimal {
    
    private static final AbstractFloatToDecimal INSTANCE = new CharacterFloatToDecimal();

    /**
     * Appends the rendering of the {@code v} to {@code array}.
     *
     * @param v the {@code float} whose rendering is appended.
     * @param array the {@link char[]} to append to.
     * @param off the index at which writing into {@link char[]} should start
     * @return next free index in {@link char[]}
     */
    public static int appendTo(float v, char[] array, int off) {
        return INSTANCE.appendDecimalTo(v, array, off);
    }

    @Override
    int removeTrailingZeroes(int offset, Object array) {
        char[] c = (char[]) array;
        int index = offset - 1;
        while (c[index] == '0') {
            index -= 1;
        }
        // but do not remove the one directly to the right of '.'
        if (c[index] == '.') {
            index += 1;
        }
        return index + 1;
    }

    @Override
    int appendString(String s, int offset, Object array) {
        char[] c = (char[]) array;
        int len = s.length();
        for (int i = 0; i < len; i++) {
            c[offset + i] = s.charAt(i);
        }
        return offset + len;
    }

    @Override
    int append(char c, int offset, Object array) {
        ((char[]) array)[offset] = c;
        return offset + 1;
    }

    @Override
    int appendDigit(int d, int offset, Object array) {
        ((char[]) array)[offset] = (char) ('0' + d);
        return offset + 1;
    }

}
