public class COMP250_ExtraExercises_Week2_Solutions {
    //Primitive Data Types and String
    //charRightShift
    public static char charRightShift(char c, int shift) {
        if (c < 'a' || c > 'z') {
            return c;
        }
        
        int pos = c - 'a';
        if (shift < 0) {
            shift = 26 - ((shift * -1) % 26);
        }
        
        int newPos = (pos + shift) % 26;
        
        return (char)(c + newPos);
    }
    
    //take a string and print if it is equal to the password
    // without using equals()
    public static boolean comparePassword(String s) {
        String password = "password";
        if (s.length() != password.length()) {
            return false;
        }
        for (int i=0; i<s.length(); i++) {
            // if one char is not the same, return false
            if (s.charAt(i) != password.charAt(i))
                return false;
        }
        return true;
    }
    
    //take a string and a integer, print if index at the given location is a vowel
    public static boolean isVowel(String s, int pos) {
        char c = s.charAt(pos);
        
        if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
            return true;
        }
        
        return false;
    }
    
    //return true if character is a upper case letter, false otherwise
    public static boolean isUpperCase(char c) {
        return c >= 'A' && c <= 'Z';
    }
    
    //count number of upper case letter in a string
    public static int countUpper(String s) {
        char c;
        int count = 0;
        for (int i=0; i<s.length(); i++) {
            if (isUpperCase(s.charAt(i))) {
                count++;
            }
        }
        return count;
    }
    
    /*********************************/
    // Arrays
    // method that returns the largest value in an int array
    public static int getLargest(int[] a) {
        if(a==null || a.length == 0) {
            return 0;
            // or maybe throw an Exception?
        }
        // assume the largest element is at index 0
        int largest = a[0];
        // loop through all the elements to see if we can find
        // a larger one.
        for(int i =0; i<a.length; i++) {
            // if so, update the value in largest
            if(a[i]>largest) {
                largest = a[i];
            }
        }
        return largest;
    }
    
    public static int[] firstNPrime(int n) {
        //create an array with n elements
        int[] primes = new int[n];
        // declare and initialize a variable to store the numbers
        // and one to count how many primes we have found
        int number = 2;
        int count = 0;
        // loop until we found n primes
        while(count<n) {
            // if number is prime we store it inside the array
            if(isPrime(number)) {
                primes[count] = number;
                count++;
            }
            number++;
        }
        return primes;
        
    }
    
    //helper function to test prime number
    public static boolean isPrime(int n) {
        if (n <= 1) {
            return false;
        }
        int d = 2;
        while (d<=Math.sqrt(n)) {
            if (n%d == 0) {
                return false;
            }
            d++;
        }
        return true;
    }
    
    // moving up element of an array by one position using a new array
    public static int[] moveUp1(int[] a) {
        int[] newArray = new int[a.length];
        newArray[0] = a[a.length -1];
        for(int i=1; i<a.length; i++) {
            newArray[i] = a[i-1];
        }
        return newArray;
    }
    
    // moving up element of an array by one position without creating a new array
    public static void moveUp2(int[] a) {
        int temp = a[a.length-1];
        for(int i = a.length-1; i>0; i--) {
            a[i] = a[i-1];
        }
        a[0] = temp;
    }
    
    // a method that returns the intersection between two arrays
    // assume each element in the array is unique
    public static int[] slow_intersect(int[] a, int[] b) {
        //first figure out the size of the intersection
        // that is, how many elements the two arrays have in common
        int size = 0;
        for(int i=0; i<a.length; i++) {
            for (int j=0; j<b.length; j++) {
                if (a[i] == b[j])
                    size++;
            }
        }
        
        // now populate the array representing the intersection
        int[] intersection = new int[size];
        int index = 0;
        for(int i =0; i<a.length; i++) {
            for (int j=0; j<b.length; j++) {
                if (a[i] == b[j]) {
                    intersection[index] = a[i];
                    index++;
                }
            }
        }
        return intersection;
    }
    
    public static boolean isMatrix(int[][] a) {
        for(int i=1; i<a.length; i++) {
            if(a[i].length != a[i-1].length) {
                return false;
            }
        }
        return true; 
    }
    
    
    public static int[] getColumn(int[][] a, int i) {
        int[] col = new int[a.length];
        for(int j=0; j<a.length; j++) {
            col[j] = a[j][i];
        }
        return col;
    }
    
    public static int[][] sumMatrix(int[][] a, int[][] b) {
        if(!isMatrix(a) || !isMatrix(b)) {
            throw new IllegalArgumentException("the inputs are not matrices");
        }
        int rowA = a.length;
        int rowB = b.length;
        int colA = a[0].length;
        int colB = b[0].length;
        if(rowA!=rowB || colA!=colB) {
            throw new IllegalArgumentException("dimensions are not the same");
        }
        int[][] sum = new int[rowA][colA];
        for(int i=0; i<sum.length; i++) {
            for(int j=0; j<sum[0].length; j++) {
                sum[i][j] = a[i][j] + b[i][j];
            }
        }
        return sum;
    }
    
    // (2,4,1)
    // (1,5,9)
    // 2*1 + 4*5 + 1*9 = 31
    public static int dotProduct(int[] a, int[] b) {
        int prod = 0;
        if(a.length != b.length) {
            throw new IllegalArgumentException("not same length");
        }
        for(int i =0; i<a.length; i++) {
            prod += a[i]*b[i];
        }
        return prod;
    }
    
    
    public static int[][] multiplyMatrix(int[][] a, int[][] b) {
        int rowsA = a.length;
        int colsA = a[0].length;
        int rowsB = b.length; 
        int colsB = b[0].length; 
        if(!isMatrix(a) || !isMatrix(b) || colsA!=rowsB) {
            throw new IllegalArgumentException("Invalid inputs");
        }
        int[][] result = new int[rowsA][colsB];
        for(int i=0; i<rowsA; i++) {
            for (int j=0; j<colsB; j++) {
                int[] columnJ = getColumn(b,j);
                result[i][j] = dotProduct(a[i],columnJ);
            }
        }
        return result; 
    }
    
    
}