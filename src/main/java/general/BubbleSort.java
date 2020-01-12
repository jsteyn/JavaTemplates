package general;

public class BubbleSort {

    static private int[] array = {3,4,1,5,3,2,90,0,21,34,139};

    static public int[] bubbleSort(int[] numbers) {
        // Clone array (because of pass by reference)
        int[] newnum = numbers.clone();
        for (int i = 0; i < newnum.length-1; i++) {
            for (int j = i + 1; j< newnum.length; j++) {
                if (newnum[i] > newnum[j]) {
                    int tmp = newnum[j];
                    newnum[j] = newnum[i];
                    newnum[i] = tmp;
                }
            }
        }
        return newnum;
    }

    static public void main(String[] args) {
        for (int integer : array) {
            System.out.print(integer + "\t");
        }
        System.out.println();
        int[] ret = bubbleSort(array);
        for (int integer : ret) {
            System.out.print(integer + "\t");
        }
        System.out.println();
        for (int integer : array) {
            System.out.print(integer + "\t");
        }
    }
}
