package com.wackycodes.rest.service;

import com.wackycodes.rest.entity.Greeting;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.concurrent.atomic.AtomicLong;

@Controller
public class HelloWorldCounter {
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/helloWorld")
    @ResponseBody
    public Greeting sayHello(@RequestParam(name="name", required=false, defaultValue="WackyCodes!! Check : https://linktr.ee/wackycodes") String name) {
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }

}
