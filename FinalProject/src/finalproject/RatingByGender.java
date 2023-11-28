package finalproject;

public class RatingByGender extends DataAnalyzer{
	private MyHashTable<String, MyHashTable<String, Integer>> genderQuality;
	private MyHashTable<String, MyHashTable<String, Integer>> genderDifficulty;


	public RatingByGender(Parser p) {
		super(p);
	}

	@Override
	public MyHashTable<String, Integer> getDistByKeyword(String keyword) {
		keyword = keyword.toLowerCase().trim();
		keyword = keyword.replaceAll("[^a-z']", " ");
		String[] words = keyword.split("\\s+");
		if(words[1].equals("difficulty")){
			return genderDifficulty.get(words[0]);
		}
		else if(words[1].equals("quality")){
			return genderQuality.get(words[0]);
		}
		else{
			return null;
		}
	}

	@Override
	public void extractInformation() {
		genderQuality = new MyHashTable<>();
		genderDifficulty = new MyHashTable<>();
		MyHashTable<String, Integer> ratings = new MyHashTable<>();
		MyHashTable<String, Integer> ratings2 = new MyHashTable<>();

		for (String[] data : super.parser.data) {
			if (data == null || data.length == 0) {
				continue;
			}

			String quality = Integer.toString((int)Double.parseDouble(data[super.parser.fields.get("student_star")]));
			String difficulty = Integer.toString((int)Double.parseDouble(data[super.parser.fields.get("student_difficult")]));
			String gender = data[super.parser.fields.get("gender")].trim().toLowerCase();

			if(genderDifficulty.get(gender) == null){
				ratings = new MyHashTable<>();
				for(int i=1; i<=5; i++){
					ratings.put(Integer.toString(i), 0);
				}
			}
			else{
				ratings = genderDifficulty.get(gender);
			}
			Integer count = ratings.get(difficulty);
			if (count == null) {
				count = 0;
			}
			count++;
			ratings.put(difficulty, count);
			genderDifficulty.put(gender, ratings);

			if(genderQuality.get(gender) == null){
				ratings2 = new MyHashTable<>();
				for(int i=1; i<=5; i++){
					ratings2.put(Integer.toString(i), 0);
				}
			}
			else{
				ratings2 = genderQuality.get(gender);
			}
			Integer count2 = ratings2.get(quality);
			if (count2 == null) {
				count2 = 0;
			}
			count2++;
			ratings2.put(quality, count2);
			genderQuality.put(gender, ratings2);
		}
	}
}
