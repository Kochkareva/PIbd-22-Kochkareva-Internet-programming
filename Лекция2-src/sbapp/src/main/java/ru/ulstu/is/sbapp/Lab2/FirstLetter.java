package ru.ulstu.is.sbapp.Lab2;

import org.springframework.stereotype.Component;

@Component(value = "first")
public class FirstLetter implements IDopTask{
    @Override
    public String task(String str){
        return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
    }
}
