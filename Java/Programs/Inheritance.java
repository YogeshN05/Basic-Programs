class Animals{
    void Name()
    {
        System.out.println("I am Animal");
    }
}

class Dog extends Animals{
    void Communication()
    {
        System.out.println("I Will Bark");
    }
}

class Cat extends Dog{
    void Communication1()
    {
        System.out.println("Mew....");
    }
}

public class Inheritance {
    public static void main(String[] args) {
        Cat c=new Cat();
    
        c.Name();
        c.Communication();
        c.Communication1();

    }
    
}
