package ru.mail.polis.homework.analyzer;

public class NegativeTextAnalyzer implements TextAnalyzer{
    String[] negativeText = {"=(", ":(", ":|"};
    public NegativeTextAnalyzer() {};
    @Override
    public FilterType Analyze(String text) {
        for (String smile: negativeText) {
            if (text.contains(smile)) {
                return FilterType.NEGATIVE_TEXT;
            }
        }

        return FilterType.GOOD;
    }
}
