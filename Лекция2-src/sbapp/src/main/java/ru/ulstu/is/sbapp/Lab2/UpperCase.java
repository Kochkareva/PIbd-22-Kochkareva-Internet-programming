package ru.ulstu.is.sbapp.Lab2;

public class UpperCase implements IDopTask{
    @Override
    public String task(String str){
        return str.toUpperCase();
    }
}
