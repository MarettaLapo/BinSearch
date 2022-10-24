import java.util.*;
import java.io.*;
import java.util.List;

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
        File file = new File(path); //путь до файла
        documents.add(file.getName()); //добавляем имя файла
        int iDoc = documents.size() - 1; //узнаем какой по счету документ
        try (Scanner sc = new Scanner(file)){ //читаем документ
            while (sc.hasNextLine()) {
                String[] words = sc.nextLine().toLowerCase().split("\\W+"); //сплит строки
                for(String name: words){
                    if(index.containsKey(name)){ //проверяем есть ли такое слово, если есть добавлем номер документа
                        index.get(name).add(iDoc);
                    }
                    else{ //если нет, то создаем новый линкед лист с ключем по слову
                        LinkedList list = new LinkedList<>();
                        index.put(name, list);
                    }
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(iDoc + " " + index.size());
    }
    public void indexCollection(String folder){
        File file = new File(folder);
        for(File var: file.listFiles()){
            indexDocument(var.getAbsolutePath());
        }
    }
    public LinkedList<Integer> getIntersection(LinkedList<Integer> list1, LinkedList<Integer> list2){
        LinkedList<Integer> inter_list = new LinkedList<>();
        ListIterator iter_list1 = list1.listIterator();
        ListIterator iter_list2 = list2.listIterator();
        while(iter_list1.hasNext() && iter_list2.hasNext()){
            if(list1.get(iter_list1.nextIndex()) == list2.get(iter_list2.nextIndex())){
                inter_list.add(list1.get(iter_list1.nextIndex()));
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
        return inter_list;
    }
    public LinkedList<Integer> getUnion(LinkedList<Integer> list1, LinkedList<Integer> list2){
        LinkedList<Integer> inter_list = new LinkedList<>();

        return inter_list;
    }
}

