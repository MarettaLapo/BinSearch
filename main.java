import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        File file = new File("out.txt");
        if(!file.exists()){
            FileOutputStream fos = new FileOutputStream("out.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            InvertedIndex obj = new InvertedIndex();
            obj.indexCollection("collection");
            oos.writeObject(obj);
            oos.flush();
            oos.close();
        }
        FileInputStream fis = new FileInputStream("out.txt");
        ObjectInputStream oin = new ObjectInputStream(fis);
        InvertedIndex obj_main = (InvertedIndex) oin.readObject();
        System.out.println(obj_main.executeQuery("Brutus"));
        System.out.println(obj_main.executeQuery("Caesar"));
        System.out.println(obj_main.executeQuery("Calpurnia"));
        System.out.println(obj_main.executeQuery("Brutus AND Brutus"));
        System.out.println(obj_main.executeQuery("Brutus AND Caesar"));
        System.out.println(obj_main.executeQuery("Brutus AND Caesar AND Calpurnia"));
        System.out.println(obj_main.executeQuery("Brutus OR Brutus"));
        System.out.println(obj_main.executeQuery("Brutus OR Caesar"));
        System.out.println(obj_main.executeQuery("Brutus OR Caesar OR Calpurnia"));
        System.out.println(obj_main.executeQuery("SpiderMan"));
        System.out.println(obj_main.executeQuery("Brutus AND SpiderMan"));
          System.out.println(obj_main.executeQuery("Caesar OR SpiderMan"));
    }
}