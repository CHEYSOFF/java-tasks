package ru.mail.polis.homework.analyzer;

import java.util.Arrays;

public class SpamAnalyzer implements TextAnalyzer {
    private final String[] spam;

    public SpamAnalyzer(String[] spam) {
        this.spam = spam;
    }

    @Override
    public FilterType Analyze(String text) {
        for (String word : spam) {
            if (text.contains(word)) {
                return FilterType.SPAM;
            }
        }

        return FilterType.GOOD;
    }
}
