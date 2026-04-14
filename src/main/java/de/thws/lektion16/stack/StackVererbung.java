package de.thws.lektion16.stack;

import java.util.ArrayList;

public class StackVererbung implements StackInterface
{
    ArrayList list;

    @Override
    public boolean push(Object object) {
        return this.list.add(object);
    }

    @Override
    public Object pop() {
        return this.list.removeLast();
    }
}
