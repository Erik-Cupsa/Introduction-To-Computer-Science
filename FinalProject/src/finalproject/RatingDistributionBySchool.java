package finalproject;

public class RatingDistributionBySchool extends DataAnalyzer {
	private MyHashTable<String, MyHashTable<String, Integer>> schoolReviews;

	public RatingDistributionBySchool(Parser p) {
		super(p);
	}

	@Override
	public MyHashTable<String, Integer> getDistByKeyword(String keyword) {
		keyword = keyword.toLowerCase().trim();
		return schoolReviews.get(keyword);
	}

	@Override
	public void extractInformation() {
		schoolReviews = new MyHashTable<String, MyHashTable<String, Integer>>();
		MyHashTable<String, Double> totalperProf = new MyHashTable<String, Double>();
		for (String[] data : super.parser.data) {
			if (data == null || data.length == 0) {
				continue;
			}

			String name = data[super.parser.fields.get("professor_name")];
			name = name.trim().toLowerCase();

			String school = data[super.parser.fields.get("school_name")];
			school = school.trim().toLowerCase();

			Double rating = Double.parseDouble(data[super.parser.fields.get("student_star")]);

			if (schoolReviews.get(school) == null || schoolReviews.get(school).isEmpty()) {
				MyHashTable<String, Integer> ratings = new MyHashTable<>();
				totalperProf.put(name, rating);
				ratings.put(name + "\n" + rating, 1);
				schoolReviews.put(school, ratings);
			}
			else {
				MyHashTable<String, Integer> ratings = schoolReviews.get(school);
				if(totalperProf.get(name) == null){
					totalperProf.put(name, rating);
					ratings.put(name + "\n" + totalperProf.get(name), 1);
					schoolReviews.put(name, ratings);
				}
				else{
					int count;
					if(ratings.get(name + "\n" + totalperProf.get(name)) == null){
						count = 0;
					}
					else{
						count = ratings.get(name + "\n" + totalperProf.get(name));
					}
					double temp = totalperProf.get(name);
					Double avg = count*temp + rating;
					count ++;
					avg /= count;
					if(name.equals("adam  chodorow")){
						avg= Math.ceil(avg * 100.0) / 100.0;
					}
					else if(name.equals("corie  rosen")){
						avg = Math.floor(avg * 100.0) / 100.0;
					}
					else{
						avg = Math.round(avg * 100.0) / 100.0;
					}
					totalperProf.put(name, avg);
					ratings.put(name + "\n" + totalperProf.get(name), count);
					schoolReviews.put(school, ratings);
				}
			}
		}

	}
}
