package javatest;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize
public class Language {

    private String language;

    public Language() {};

    public Language(String language) {
        this.language = language;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
