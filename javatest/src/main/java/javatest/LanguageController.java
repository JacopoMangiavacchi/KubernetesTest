package javatest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;


@RestController
public class LanguageController {

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
