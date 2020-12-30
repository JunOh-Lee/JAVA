//Please don't change class name 'Main'

class Main {

  public static void main(String[] args) {
    System.out.println(1==2);       //false
    System.out.println(1==1);       //true

    System.out.println(1!=2);       //true
    System.out.println(1!=1);       //false

    System.out.println(10>20);      //false
    System.out.println(10>2);       //true
    System.out.println(10>10);      //false

    System.out.println(10 >= 20);   // false
    System.out.println(10 >= 2);    // true
    System.out.println(10 >= 10);   // true

    String a = "Hello world";
    String b = new String("Hello world");
		System.out.println(a==b);
    System.out.println(a.equals(b));
  }
}
