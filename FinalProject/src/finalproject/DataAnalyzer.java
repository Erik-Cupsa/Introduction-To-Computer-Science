package finalproject;

public abstract class DataAnalyzer {

    Parser parser;
    
    public DataAnalyzer(Parser p) {
        parser = p;
        extractInformation();
    }

    public abstract MyHashTable<String, Integer> getDistByKeyword(String keyword);

    public abstract void extractInformation();
  
    
}
