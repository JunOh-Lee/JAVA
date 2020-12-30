//Please don't change class name 'Main'

class Main {

  public static void main(String[] args) {
    String id = args[0];
    String password = args[1];
    if ((id.equals("egoing") || id.equals("k8805") || id.equals("sorialgi")) && password.equals("111111")) {
        System.out.println("right");
    } else {
        System.out.println("wrong");
    }
  } /*cmd창에 cd 파일주소~bin까지 입력하고
  java class명(Main) 입력값1 입력값2   입력*/
}
