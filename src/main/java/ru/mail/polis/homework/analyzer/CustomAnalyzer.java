package ru.mail.polis.homework.analyzer;

public class CustomAnalyzer implements TextAnalyzer {
    private final float uniqeness;

    public CustomAnalyzer(float uniqeness) {
        this.uniqeness = uniqeness;
    }

    @Override
    public FilterType Analyze(String text) {
        text = text.toLowerCase();
        int[] alphabet = new int[33];
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) - 'а' >= 0 && text.charAt(i) - 'а' < 33) {
                alphabet[(int) (text.charAt(i) - 'а')]++;
            }
        }
        for (int count : alphabet) {
            if ((float) count >= uniqeness * text.length()) {
                return FilterType.CUSTOM;
            }
        }
        return FilterType.GOOD;
    }
}
