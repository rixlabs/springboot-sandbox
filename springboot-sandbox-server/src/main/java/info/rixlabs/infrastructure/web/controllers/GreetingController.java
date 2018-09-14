package info.rixlabs.infrastructure.web.controllers;

/**
 * Created by riccardo.causo on 02.05.2016.
 */

import info.rixlabs.core.domain.Greeting;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GreetingController {

    private static final String template = "Ciao, %s!";
    private final AtomicLong counter = new AtomicLong();


    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="Poldo") String name) {
        return new Greeting(counter.incrementAndGet(),String.format(template, name));
    }


}
