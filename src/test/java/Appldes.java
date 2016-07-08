import java.io.*;
import java.io.IOException;
import java.io.Serializable;


class Appldes {
  public static void main(String args[]) throws IOException {
    try {
      FileInputStream fis = new FileInputStream("temp.out");
      ObjectInputStream oin = new ObjectInputStream(fis);
      TestSerial ts = (TestSerial)oin.readObject();
      System.out.println("version="+ts.version);
    } catch (Exception e){
    }
  }
}