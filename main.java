import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        File file = new File("C:\\Project\\AlgInter\\zad1(bin)\\collection\\King_Lear.txt");
        LinkedList list = new LinkedList<>();
        try (Scanner sc = new Scanner(file))
        {
            while (sc.hasNextLine()) {
                String[] words = sc.nextLine().toLowerCase().split("\\W+");
                for(String name: words){
                    list.add(name);
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(list);
    }
}