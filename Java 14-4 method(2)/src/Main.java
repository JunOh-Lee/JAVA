//Please don't change class name 'Main'

class Main {

  public static void numbering(int init, int limit) {
    int i = init;
    while (i < limit) {
        System.out.println(i);
        i++;
    }
  }

  public static void main(String[] args) {
    numbering(1, 5);  //numbering 메소드로 1과 5라는 인자를 전달한다.
  }
}
