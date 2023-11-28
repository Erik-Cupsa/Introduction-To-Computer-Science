package finalproject;

public class RatingByKeyword extends DataAnalyzer {
	private MyHashTable<String, MyHashTable<String, Integer>> wordRatings;
	
    public RatingByKeyword(Parser p) {
        super(p);
    }

	@Override
	public MyHashTable<String, Integer> getDistByKeyword(String keyword) {

		keyword = keyword.trim().toLowerCase();
		return wordRatings.get(keyword);
	}

	@Override
	public void extractInformation() {
		wordRatings = new MyHashTable<String, MyHashTable<String, Integer>>();
		MyHashTable<String, Integer> allWords = new MyHashTable<>();

		for (String[] data : super.parser.data) {
			if (data == null || data.length == 0) {
				continue;
			}

			String rating = Integer.toString((int)Double.parseDouble(data[super.parser.fields.get("student_star")]));

			String comments = data[super.parser.fields.get("comments")];
			comments = comments.trim().toLowerCase();
			comments = comments.replaceAll("[^a-z']", " ");
			String[] words = comments.split("\\s+");

			MyHashTable<String, Integer> keywords = new MyHashTable<>();
			for(String word: words){
				keywords.put(word,0);
			}
			for(String keyword : keywords.getKeySet()){
				if(wordRatings.get(keyword) == null){
					allWords = new MyHashTable<>();
					for(int i=1; i<=5; i++){
						allWords.put(Integer.toString(i), 0);
					}
				}
				else{
					allWords = wordRatings.get(keyword);
				}
				Integer count = allWords.get(rating);
				if (count == null) {
					count = 0;
				}
				count++;
				allWords.put(rating, count);
				wordRatings.put(keyword, allWords);
			}
		}
	}
}
