import java.util.Arrays;
import java.util.Random; 
public class Practice {
	
	static String password = "test"; 
	static char[] vowel = {'a', 'e', 'i', 'u','y', 'o'}; 
	static char[] upper = {'A', 'b', 'C'};
	static int[][] twoArr = {{1,2}, {5,6}, {4,6}};
	static int[][] twoArra = {{1,2}, {5,6}, {4,6}};
	
	public static void main(String[] args) { 
		int[] arr = {1,2,3,4,5};
//		int[] otherArr = {1,2,3,5,1}; 
		mystery(arr); 
//		System.out.println((dotProduct(arr, otherArr))); 
		// System.out.println(Arrays.toString(arr));
		// System.out.println(isUpperCase('A'));
			Random random = new Random();
			char randomChar = (char) (random.nextInt(26) + 'a');
			double randomDouble = random.nextDouble() * 100;
        System.out.println("Random double: " + randomDouble);
			System.out.println("Random character: " + randomChar);
//		int[][] newArr = sumMatrix(twoArr, twoArra); 
//		for(int i=0; i<newArr.length; i++) {
//			for(int j=0; j<newArr[0].length; j++) {
//				System.out.println(newArr[i][j]);
//			}
//		}
	}
	
	public static char charRightShift(char a, int n) { 
		n %= 26; 
		if(n<0) {
			n = 26 + n;
		}
		if (Character.isLowerCase(a)) { 
			a =(char) ((int) a+ n); 
			return a; 
		}
		else return a; 
	}
	
	public static boolean isPassword(String a) { 
		if (a.equals(password)) {
			return true; 
		}
		return false; 
	}
	
	public static boolean isVowel(String a, int n) {
		for(int i=0; i<vowel.length; i++) {
			if (a.charAt(n) == vowel[i]) return true; 
		}
		return false; 
	}
	
	public static boolean isUpperCase(char a) {
		int test = (int) a; 
		if(test>= 65 && test <= 90) return true; 
		return false; 
	}
	
	public static int countUpper(String a) {
		int n = 0; 
		for(int i=0; i<a.length(); i++) {
			if (Character.isUpperCase(a.charAt(i))) {
				n ++; 
			}
		}
		return n; 
	}
	
	public static int largest(int[] arr) {
		int largest = 0; 
		for(int i=0; i<arr.length; i++) {
			if(largest < arr[i]) largest = arr[i];
		}
		return largest; 
	}
	
	public static int[] shift(int[] n) {
		int a = n.length; 
		int[] arr = new int[a]; 
		arr[0] = n[a-1]; 
		for(int i=a-1; i>0; i--) {
			arr[i] = n[i-1];
		}
		return arr; 
	}
	
	public static void move(int[] n) {
		int a = n.length; 
		int[] arr = new int[a]; 
		arr[a -1] = 0; 
		for(int i=n.length-2; i>=0; i--) {
			arr[i] = n[i+1];
		}
		System.out.println(Arrays.toString(arr));
	}
	
	public static int[] intersection(int[] a, int[] b) {
		int n=0;  
		for(int i=0; i<a.length; i++) {
			for(int j=0; j<b.length; j++) {
				if(b[j] == a[i]) {
					n++; 
				}
			}
		}
		int[] arr = new int[n];
		int index = 0;
		for(int i=0; i<a.length; i++) {
			for(int j=0; j<b.length; j++) {
				if(b[j] == a[i]) {
					arr[index] = a[i];; 
					index++; 
				}
			}
		}
		return arr; 
	}
	
	public static boolean isMatrix(int[][] arr) {
		for(int i=1; i< arr.length; i++) {
			if(arr[0].length != arr[i].length) return false; 
		}
		
		return true; 
	}
	
	public static int[] getColumn(int[][] arr, int i) {
		int[] newArr = new int[arr.length]; 
		for(int n=0; n<arr.length; n++) {
			newArr[n] = arr[n][i];
		}
		return newArr; 
	}
	
	public static int[][] sumMatrix(int[][] a, int[][] b){
		int[][] newArr = new int[a.length][a[0].length]; 
		for(int i=0; i<a.length; i++) {
			for(int j=0; j<a[0].length; j++) {
				newArr[i][j] = a[i][j] + b[i][j];
			}
		}
		return newArr; 
	}
	
	public static int dotProduct(int[] one, int[] two) {
		int sum = 0; 
		for(int i=0; i<one.length; i++) {
			sum += one[i] * two[i]; 
		}
		return sum; 
	}
	
	public static int RandomInt() {
		Random random = new Random(); 
		return random.nextInt(); 
	}
	public static int RandomIntSeed(int a) {
		Random random = new Random(); 
		return random.nextInt(a); 
	}
	
	public static String toLowerCase(String a) {
		String out = a;
		for(int i=0; i<a.length(); i++) { 
			if(a.charAt(i) >= 65 && a.charAt(i) <= 90) {
				out = out.replace(a.charAt(i),(char) (a.charAt(i) + 32)) ; 
			}
		}
		return out; 
	}
	
	public static char[] toLowerCaseArray(char[] a) {
		for(int i=0; i<a.length; i++) {
			if((int)a[i] >= 65 && (int) a[i] <= 90) {
				a[i] = (char) (a[i] + 32);
			}
		}
		return a; 
	}
	
	public static void mystery(int[] a) {
		for(int i=0; i<a.length/2; i++) {
			int temp = a[a.length-1-i];
			a[a.length-1-i] = a[i]; 
			a[i] = temp; 
		}
	}

}
