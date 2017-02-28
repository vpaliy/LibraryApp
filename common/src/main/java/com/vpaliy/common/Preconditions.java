package com.vpaliy.common;

public final class Preconditions {

    private Preconditions() {
        throw new UnsupportedOperationException();
    }

    public static void checkNotNull(Object object) {
        if(object==null) {
            throw new NullPointerException();
        }
    }

    public static void checkIfContains(String[] strings, String ... samples) {
        if(strings.length<samples.length) {
            throw new IndexOutOfBoundsException("Strings is smaller than samples");
        }

        for(String sample:samples) {
            boolean contains=false;
            for(String string:strings) {
                contains=string.equals(sample);
                if(contains) {
                    break;
                }
            }

            if(contains) {
                throw new IllegalArgumentException("Given array does not contain:"+sample);
            }
        }
    }

}
