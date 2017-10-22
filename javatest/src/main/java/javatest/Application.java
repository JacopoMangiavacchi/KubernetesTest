package javatest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

@JsonDeserialize
class Language {

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

@JsonDeserialize
class Request {

    private String url;

    public Request() {};

    public Request(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(Application.class);
        System.getProperties().put( "server.port", 8060);
        app.run(args);
    }
}

@RestController
class LanguageController {

    @RequestMapping("/language")
    public Language language() {
        return new Language("Java");
    }
    @RequestMapping(value="/request", method=RequestMethod.POST)
    public Language request(@RequestBody Request request) {
        RestTemplate restTemplate = new RestTemplate();
        Language language = restTemplate.getForObject(request.getUrl(), Language.class);

        return new Language(language.getLanguage());
    }
}

