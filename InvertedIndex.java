import java.util.*;

public class InvertedIndex {
    private  List<String> documents;
    private Map<String, LinkedList<Integer>> index;

    public InvertedIndex(){
        documents = new LinkedList<>();
        index = new HashMap<>();

    }
    //ключ - слово
    //массив с индексами
    //пустой конструктор, присвоить документам или LinkedList, какой то другой, а индексу hashmap пустые коллекции
    //прочитать документ, разбить сплитом, убрать все лишнее через выражения и занести в мап
    public void indexDocument(String path){ //количество элементов в одном документе
        
    }

}
