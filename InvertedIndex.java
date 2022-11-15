import java.util.*;
import java.io.*;
import java.util.List;
import java.io.Serializable;

public class InvertedIndex implements Serializable{
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
                for (String name : words) {
                    if (index.containsKey(name)) { //проверяем есть ли такое слово, если есть добавлем номер документа
                        if(index.get(name).getLast() != iDoc){
                            index.get(name).add(iDoc);
                        }
                    }
                    else{ //если нет, то создаем новый линкед лист с ключем по слову
                        LinkedList list = new LinkedList<>();
                        index.put(name, list);
                        index.get(name).add(iDoc);
                    }
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
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
        ListIterator<Integer> iter_list1 = list1.listIterator();
        ListIterator<Integer> iter_list2 = list2.listIterator();
        while(iter_list1.hasNext() && iter_list2.hasNext()){
            if(list1.get(iter_list1.nextIndex()) == list2.get(iter_list2.nextIndex())){
                inter_list.add(list1.get(iter_list1.nextIndex()));
                iter_list1.next();
                iter_list2.next();
            }
            else{
                if(list1.get(iter_list1.nextIndex()) < list2.get(iter_list2.nextIndex())){
                    while(iter_list1.hasNext() && (list1.get(iter_list1.nextIndex()) < list2.get(iter_list2.nextIndex()))){
                        inter_list.add(list1.get(iter_list1.nextIndex()));
                        iter_list1.next();
                    }
                }
                else{
                    while(iter_list2.hasNext() && list1.get(iter_list1.nextIndex()) > list2.get(iter_list2.nextIndex())){
                        inter_list.add(list2.get(iter_list2.nextIndex()));
                        iter_list2.next();
                    }
                }
            }
        }
        while (iter_list1.hasNext()){
            inter_list.add(list1.get(iter_list1.nextIndex()));
            iter_list1.next();
        }
        while(iter_list2.hasNext()){
            inter_list.add(list2.get(iter_list2.nextIndex()));
            iter_list2.next();
        }
        return inter_list;
    }
    public void sortList(LinkedList<LinkedList<Integer>> list){
        Collections.sort(list, new Comparator<LinkedList>(){
            public int compare(LinkedList a1, LinkedList a2) {
                return a1.size() - a2.size(); // assumes you want biggest to smallest
            }
        });
    }
    public LinkedList<Integer> executeQuery(String query){
        LinkedList<Integer> inter_list = new LinkedList<Integer>();
        LinkedList<LinkedList<Integer>> sort_list= new LinkedList<LinkedList<Integer>>();
        if(query.contains("AND")){
            String[] temp = query.toLowerCase().split(" and ");
            for(String item: temp){
                if(!index.containsKey(item)){
                    return inter_list;
                }
                sort_list.add(index.get(item));
            }
            sortList(sort_list);
            ListIterator<LinkedList<Integer>> iter_list = sort_list.listIterator();
            inter_list = sort_list.get(0);
            iter_list.next();
            while(iter_list.hasNext()){
                inter_list = getIntersection(inter_list, sort_list.get(iter_list.nextIndex()));
                iter_list.next();
            }
            return inter_list;
        }
        else {
            if (query.contains("OR")) {
                String[] temp = query.toLowerCase().split(" or ");
                for(String item: temp){
                    if(index.containsKey(item)){
                        sort_list.add(index.get(item));
                    }
                }
                if(sort_list.size() < 1){
                    return inter_list;
                }
                else{
                    sortList(sort_list);
                    ListIterator<LinkedList<Integer>> iter_list = sort_list.listIterator();
                    inter_list = sort_list.get(0);
                    iter_list.next();
                    while(iter_list.hasNext()){
                        inter_list = getUnion(inter_list, sort_list.get(iter_list.nextIndex()));
                        iter_list.next();
                    }
                    return inter_list;
                }
            } else {
                if(!index.containsKey(query.toLowerCase())){
                    return inter_list;
                }
                else{
                    return index.get(query.toLowerCase());
                }

            }
        }
    }
}

