package com.fasterxml.jackson.core.io.schubfach;

public final class ByteDoubleToDecimal extends AbstractDoubleToDecimal {
    
    private static final AbstractDoubleToDecimal INSTANCE = new ByteDoubleToDecimal();

    /**
     * Appends the rendering of the {@code v} to {@code array}.
     *
     * @param v the {@code double} whose rendering is appended.
     * @param array the {@link char[]} to append to.
     * @param off the index at which writing into {@link char[]} should start
     * @return next free index in {@link char[]}
     */
    public static int appendTo(double v, byte[] array, int off) {
        return INSTANCE.appendDecimalTo(v, array, off);
    }

    @Override
    int removeTrailingZeroes(int offset, Object array) {
        byte[] b = (byte[]) array;
        int index = offset - 1;
        while (b[index] == '0') {
            index -= 1;
        }
        // but do not remove the one directly to the right of '.'
        if (b[index] == '.') {
            index += 1;
        }
        return index + 1;
    }

    @Override
    int appendString(String s, int offset, Object array) {
        byte[] b = (byte[]) array;
        int len = s.length();
        for (int i = 0; i < len; i++) {
            b[offset + i] = (byte) s.charAt(i);
        }
        return offset + len;
    }

    @Override
    int append(int c, int offset, Object array) {
        ((byte[]) array)[offset] = (byte) c;
        return offset + 1;
    }

    @Override
    int appendDigit(int d, int offset, Object array) {
        ((byte[]) array)[offset] = (byte) ('0' + d);
        return offset + 1;
    }

}
