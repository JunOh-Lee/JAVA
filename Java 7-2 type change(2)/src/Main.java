//Please don't change class name 'Main'

class Main {

  public static void main(String[] args) {
    //자동 형 변환이 이루어지지 않기 때문에 오류가 발생한다.
    //float a = 100.0;
    //int b = 100.0F;

    float a = (float)100.0;
    int b = (int)100.0F;

    System.out.println(a);
    System.out.println(b);
  }
}
