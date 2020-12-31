//Please don't change class name 'Main'

class Main {

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            if (i == 5) {
                //i가 5일 때 반복문을 중단시킨다.
                break;
              
                //i가 5일 때 실행을 중단하고 반복문은 계속 실행된다.
                //continue;
            }
            System.out.println("Coding Everybody " + i);
        }
    }
}
