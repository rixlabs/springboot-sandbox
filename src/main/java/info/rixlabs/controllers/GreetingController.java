package info.rixlabs.controllers;

/**
 * Created by riccardo.causo on 02.05.2016.
 */

import info.rixlabs.models.Greeting;
import info.rixlabs.security.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GreetingController {

    private static final String template = "Ciao, %s!";
    private final AtomicLong counter = new AtomicLong();

    @Autowired
    private TokenUtils tokenUtils;

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="Poldo") String name) {
        return new Greeting(counter.incrementAndGet(),String.format(template, name));
    }

    @RequestMapping("/token")
    public String token(){
        String token;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            token = this.tokenUtils.generateToken((UserDetails)principal);
        } else {
            token = "ciao";
        }

        return token;
    }
}
