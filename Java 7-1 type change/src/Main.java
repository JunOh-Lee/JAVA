//Please don't change class name 'Main'

class Main {

  public static void main(String[] args) {
    double a = 3.0F;

    /*상수 3.0은 상수인데, 이 상수는 double 형이다. 
    이 값을 표현 범위가 좁은 float에 넣으려고 하기 때문에 오류가 발생한다.*/
    //float a = 3.0;

    int b = 3;

    float c = 1.0F;

    double d = b + c;

    System.out.println(a);
    System.out.println(d);
  }
}
