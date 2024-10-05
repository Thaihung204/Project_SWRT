package com.example.SV_Market.mailling;

import java.util.HashMap;
import java.util.Map;

public class AbstractEmailContext  {
    private String to;
    private String form;
    private String subject;
    private  String email;
    private String templateLocation;
    private Map<String,String> context;
    public AbstractEmailContext(){
        this.context=new HashMap<>();
    }
    public <T> void init(T context){

    }
}
