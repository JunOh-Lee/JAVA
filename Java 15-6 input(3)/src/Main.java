//Please don't change class name 'Main'

import java.util.Scanner;
import java.io.*;

class Main {

  public static void main(String[] args) {
    try {
        File file = new File("input.txt");
        //input.txt�� �Էµ� ���� �޴´�.
        Scanner sc = new Scanner(file);
        while(sc.hasNextInt()) {
            System.out.println(sc.nextInt()*1000);
        }
        sc.close();
    } catch(FileNotFoundException e){
        e.printStackTrace();
    }
  }
}
