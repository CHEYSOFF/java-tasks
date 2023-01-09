package ru.mail.polis.homework.analyzer;

public class TooLongAnalyzer implements TextAnalyzer {
    private final long maxLength;
    public TooLongAnalyzer(long maxLength) {
        this.maxLength = maxLength;
    }

    @Override
    public FilterType Analyze(String text) {
        if(maxLength != -1 && text.length() > maxLength) {
            return FilterType.TOO_LONG;
        }
        else {
            return FilterType.GOOD;
        }

    }
}
