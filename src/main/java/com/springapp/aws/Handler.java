package com.springapp.aws;

import java.util.Map;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class Handler implements RequestHandler<Map<String, Object>, String> {

    @Override
    public String handleRequest(Map<String, Object> event, Context context) {
        String name = (String) event.get("name");
        Integer age = (Integer) event.get("age");
        String city = (String) event.get("city");

        return "Hello " + name + "! You are " + age + " years old and live in " + city + ".";
    }
}
