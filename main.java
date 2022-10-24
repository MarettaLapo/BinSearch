import java.util.*;
import java.io.*;

public class main {
    public static void main(String[] args) throws IOException {
//        File file = new File("C:\\Users\\akgon\\IdeaProjects\\untitled\\collection\\King_Lear.txt");
//        LinkedList list = new LinkedList<>();
//        try (Scanner sc = new Scanner(file))
//        {
//            while (sc.hasNextLine()) {
//                String[] words = sc.nextLine().toLowerCase().split("\\W+");
//                for(String name: words){
//                    list.add(name);
//                }
//            }
//        }
//        catch (IOException e) {
//            e.printStackTrace();
//        }
//        new InvertedIndex().indexCollection("C:\\Users\\akgon\\IdeaProjects\\untitled\\collection");
        LinkedList<Integer> list1 = new LinkedList<>();
        LinkedList<Integer> list2 = new LinkedList<>();
        LinkedList<Integer> list = new LinkedList<>();
        list1.add(1);
        list1.add(2);
        list1.add(3);
        list1.add(4);
        list2.add(1);
        list2.add(3);
        list2.add(4);
        list2.add(5);
        list2.add(6);
        ListIterator<Integer> iter_list1 = list1.listIterator();
        ListIterator<Integer> iter_list2 = list2.listIterator();
        while(iter_list1.hasNext() && iter_list2.hasNext()){
            if(list1.get(iter_list1.nextIndex()) == list2.get(iter_list2.nextIndex())){
                list.add(list1.get(iter_list1.nextIndex()));
                iter_list1.next();
                iter_list2.next();
            }
            else{
                if(list1.get(iter_list1.nextIndex()) < list2.get(iter_list2.nextIndex())){
                    iter_list1.next();
                }
                else{
                    iter_list2.next();
                }
            }
        }
        System.out.println(list);
    }
}