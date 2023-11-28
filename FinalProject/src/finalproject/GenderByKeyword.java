package finalproject;


public class GenderByKeyword extends DataAnalyzer {
	private MyHashTable<String, MyHashTable<String, Integer>> textMap;


	public GenderByKeyword(Parser p) {
		super(p);
	}

	@Override
	public MyHashTable<String, Integer> getDistByKeyword(String keyword) {
		keyword = keyword.trim().toLowerCase();
		return textMap.get(keyword);
	}

	@Override
	public void extractInformation() {
		textMap = new MyHashTable<String, MyHashTable<String, Integer>>(3);
		MyHashTable<String, Integer> allWords = new MyHashTable<>();


		for (String[] data : super.parser.data){
			if (data == null || data.length == 0) {
				continue;
			}

			String gender = data[super.parser.fields.get("gender")].trim();

			String comments = data[super.parser.fields.get("comments")];
			comments = comments.trim().toLowerCase();
			comments = comments.replaceAll("[^a-z']", " ");
			String[] words = comments.split("\\s+");

			MyHashTable<String, Integer> keywords = new MyHashTable<>();
			for(String word: words){
				keywords.put(word,0);
			}
			for(String keyword : keywords.getKeySet()){
				if(textMap.get(keyword) == null){
					allWords = new MyHashTable<>();
					allWords.put("M", 0);
					allWords.put("F", 0);
					allWords.put("X", 0);
					textMap.put(keyword,allWords);
				}
				else{
					allWords = textMap.get(keyword);
				}
				Integer count = allWords.get(gender);
				if (count == null) {
					count = 0;
				}
				count++;
				allWords.put(gender, count);
				textMap.put(keyword, allWords);
			}
		}
	}

}
