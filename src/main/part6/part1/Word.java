package main.part6.part1;

import java.util.Objects;

public class Word implements Comparable<Word> {

    private String content;

    private int frequency;

    public Word(String content, int frequency) {
        this.content = content;
        this.frequency = frequency;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Word word = (Word) o;
        if (frequency != word.frequency) return false;
        return Objects.equals(content, word.content);
    }

    @Override
    public int hashCode() {
        int result = content != null ? content.hashCode() : 0;
        result = 31 * result + frequency;
        return result;
    }

    @Override
    public int compareTo(Word o) {
        int a = Integer.compare(o.frequency, this.frequency);
        if (a != 0) return a;
        return this.content.compareTo(o.content);
    }
}
