import java.util.*;
import java.io.*;

public class InvertedIndex {
    private  List<String> documents;
    private Map<String, LinkedList<Integer>> index;

    public InvertedIndex(){
        documents = new ArrayList<>();
        index = new HashMap<>();

    }
    //ключ - слово
    //массив с индексами
    //пустой конструктор, присвоить документам или LinkedList, какой то другой, а индексу hashmap пустые коллекции
    //прочитать документ, разбить сплитом, убрать все лишнее через выражения и занести в мап
    public void indexDocument(String path){ //количество элементов в одном документе
        File file = new File("C:\\Project\\AlgInter\\zad1(bin)\\collection\\King_Lear.txt");
        documents.add(file.getName());
        int iDoc = documents.size() - 1;
        try (Scanner sc = new Scanner(file))
        {ы
            while (sc.hasNextLine()) {
                String[] words = sc.nextLine().toLowerCase().split("\\W+");
                for(String name: words){
                    if(index.containsKey(name)){
                        index.get(name).add(iDoc);
                    }
                    else{
                        LinkedList list = new LinkedList<>(new int[]);
                        index.put(name, list);
                    }
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

}
