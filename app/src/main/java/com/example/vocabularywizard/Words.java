package com.example.vocabularywizard;

public class Words {
     public String word;
    public String definition;
    public String sentence;
    public String synonym;

    public Words() {
    }

    public Words(  String word, String definition, String sentence, String synonym) {
         this.word = word;
        this.definition = definition;
        this.sentence = sentence;
        this.synonym = synonym;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public String getSentence() {
        return sentence;
    }

    public void setSentence(String sentence) {
        this.sentence = sentence;
    }

    public String getSynonym() {
        return synonym;
    }

    public void setSynonym(String synonym) {
        this.synonym = synonym;
    }
}
