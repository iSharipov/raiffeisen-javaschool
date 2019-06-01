package ru.raiffeisen.reflection.modifiers;

import ru.raiffeisen.reflection.PersonBase;

import java.lang.reflect.Modifier;

import static ru.raiffeisen.Utils.delimeter;

public class Main {
    public static void main(String[] args) {
        int modifiers = PersonBase.class.getModifiers();
        delimeter("Interface");
        System.out.println(Modifier.isInterface(modifiers));
        delimeter("Final");
        System.out.println(Modifier.isFinal(modifiers));
        delimeter("Private");
        System.out.println(Modifier.isPrivate(modifiers));
        delimeter("Static");
        System.out.println(Modifier.isStatic(modifiers));
        delimeter("Public");
        System.out.println(Modifier.isPublic(modifiers));
        delimeter("Abstract");
        System.out.println(Modifier.isAbstract(modifiers));

    }
}
