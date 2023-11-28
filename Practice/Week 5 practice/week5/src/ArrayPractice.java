public class ArrayPractice {
    public int[] remove(int[] arr, int element){
        int index = 0;
        int[] copy = new int[arr.length-1];
        for(int i =0; i<arr.length; i++){
            if(arr[i]==element){
                index = i;
                break;
            }
            else {
                copy[i] = arr[i];
            }
        }
        for(int j = index; j<arr.length; j++){
            copy[j] = arr[j+1];
        }
        return copy;
    }
}
