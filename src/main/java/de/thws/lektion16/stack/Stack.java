package de.thws.lektion16.stack;

import java.util.ArrayList;

public class Stack extends ArrayList implements StackInterface{

    public boolean push(Object object){
        return this.add(object);
    }
    public Object pop(){
        return this.removeLast();
    }
}
