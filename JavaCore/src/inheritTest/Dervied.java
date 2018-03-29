package inheritTest;
public class Dervied extends Base {

    private String name = "dervied";

    public Dervied() {
    	//调用完父类构造方法后才开始初始子类成员变量！
    	System.out.println("in Dervied()");
        tellName(this);
        printName(this);
    }
    {
    	System.out.println("子类构造代码块");
    }
    public void tellName(Base base) {
    	System.out.println("this:"+base);
        System.out.println("Dervied tell name: " + name);
    }
    
    public void printName(Base base) {
    	System.out.println("this:"+base);
        System.out.println("Dervied print name: " + name);
    }

    public static void main(String[] args){
        
        new Dervied();    
    }
}

class Base {
    private String name = "base";

    public Base() {
    	//子类构造方法调用的父类无参构造方法内调用的函数中this为子类对象
    	System.out.println("in Base()");
        tellName(this);
        printName(this);
    }
    
    public void tellName(Base base) {
    	System.out.println("this:"+base);
        System.out.println("Base tell name: " + name);
    }
    
    public void printName(Base base) {
    	System.out.println("this:"+base);
        System.out.println("Base print name: " + name);
    }
}