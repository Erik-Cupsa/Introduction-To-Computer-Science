package finalproject;

public class RatingDistributionByProf extends DataAnalyzer {
	private MyHashTable<String, MyHashTable<String, Integer>> profRatings;
    public RatingDistributionByProf(Parser p) {
        super(p);
    }

	@Override
	public MyHashTable<String, Integer> getDistByKeyword(String keyword) {
		keyword = keyword.trim().toLowerCase();
		return profRatings.get(keyword);
	}

	@Override
	public void extractInformation() {
		profRatings = new MyHashTable<String, MyHashTable<String, Integer>>(10);

		for (String[] data : super.parser.data) {
			if (data == null || data.length == 0) {
				continue;
			}

			String name = data[super.parser.fields.get("professor_name")];
			name = name.trim().toLowerCase();

			String star = data[super.parser.fields.get("student_star")];
			int rating = (int) (Double.parseDouble(star));
			star = Integer.toString(rating);
			MyHashTable<String, Integer> ratings = profRatings.get(name);

			if (profRatings.get(name) == null || profRatings.get(name).isEmpty()) {
				ratings = new MyHashTable<>(5);
				for (int i = 1; i <= 5; i++) {
					ratings.put(Integer.toString(i), 0);
				}
				profRatings.put(name, ratings);
			}

			Integer numRating = ratings.get(star);
			if (numRating == null) {
				numRating = 0;
			}
			ratings.put(star, ++numRating);
		}
	}
}
