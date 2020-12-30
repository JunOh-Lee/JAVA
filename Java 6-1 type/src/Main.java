//Please don't change class name 'Main'

class Main {

  public static void main(String[] args) {
    //실수를 정수 타입의 변수 a에 저장하려고 했기 때문에 오류가 발생한다.
    //int a = 2.2;

    //float 형 변수에는 2.2가 float 형이라는 것을 분명하게 명시해주어야 한다.
    //float a = 2.2;

    double a = 2.2;

    float b = 2.2F;

    //int의 최댓값인 2147483647 보다 1 많기 때문에 오류가 발생한다.
    //int num = 2147483648;

    /*변수는 long 타입이지만 이 변수에 대입되는 상수가 여전히 int 타입이기 때문에
    int로 표현할 수 있는 최대 숫자를 여전히 초과하고 있다.*/
    //long num = 2147483648;

    long num = 2147483648L;

    byte c = 100;
    short d = 200;

    System.out.println(a);
    System.out.println(b);
    System.out.println(num);
    System.out.println(c);
    System.out.println(d);
  }
}
