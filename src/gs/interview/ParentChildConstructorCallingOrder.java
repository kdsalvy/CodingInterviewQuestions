package gs.interview;

public class ParentChildConstructorCallingOrder {

    public static void main(String[] args){
        CC cc = new CC();
    }
}

class AA{
    public AA(){
        System.out.println("Constructor AA");
    }
}

class BB extends AA {
    public BB(){
        System.out.println("Constructor BB");
    }
}

class CC extends BB{
    public CC(){
        System.out.println("Constructor CC");
    }
}
