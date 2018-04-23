package gs.interview;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class SerializationConcepts {

    public SerializationConcepts(){}
    public SerializationConcepts(SerializationConcepts sc){}

    public static void main(String[] args) throws Exception{
        A a = new A();
        a.x = 5;
        a.y = "Apple";

        B b = new B();
        b.x1 = 5;
        b.y1 = "Apple";
        b.x = 6;
        b.y = "Mango";

        C c = new C();
        c.x = 6;
        c.y = "Mango";
        c.b = b;

//        try(FileOutputStream fos = new FileOutputStream(new File("D:\\kd\\s.txt"));
//        ObjectOutputStream oos = new ObjectOutputStream(fos)) {
//            oos.writeObject(a);
//        }
//
//        A a1 = null;
//
//        try(FileInputStream fis = new FileInputStream(new File("D:\\kd\\s.txt"));
//            ObjectInputStream ois = new ObjectInputStream((fis))){
//            a1 = (A)ois.readObject();
//        }
//
//        System.out.println(a1.toString());

        try(FileOutputStream fos = new FileOutputStream(new File("D:\\kd\\s.txt"));
            ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(b);
        }

        B b1 = null;

        try(FileInputStream fis = new FileInputStream(new File("D:\\kd\\s.txt"));
            ObjectInputStream ois = new ObjectInputStream((fis))){
            b1 = (B)ois.readObject();
        }

        System.out.println(b1.toString());

//        try(FileOutputStream fos = new FileOutputStream(new File("D:\\kd\\s.txt"));
//            ObjectOutputStream oos = new ObjectOutputStream(fos)) {
//            oos.writeObject(c);
//        }
//
//        C c1 = null;
//
//        try(FileInputStream fis = new FileInputStream(new File("D:\\kd\\s.txt"));
//            ObjectInputStream ois = new ObjectInputStream((fis))){
//            c1 = (C)ois.readObject();
//        }
//
//        System.out.println(c1.toString());

        Map map = new HashMap();
        map.put(1, "Saurabh");
        map.put("Two", "kedia");
        map.put(true, "true");

        System.out.println(map);
    }
}

class A {
    public int x;
    public String y;

    public String toString(){
        return x + " : " + y;
    }
}

class B extends A implements Serializable{
    int x1;
    String y1;

    public String toString(){
        return x1 + " : " + y1;
    }
}

class C implements Serializable{
    int x;
    String y;
    B b;

    public String toString(){
        return x + " : " + y;
    }
}