package javatest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LanguageController {

    @RequestMapping("/language")
    public Language language() {
        return new Language("Java");
    }
}
