package forkjoin;

public class AddSumRecursion {

    int startIndex;
    int endIndex;
    int THRESHOLD = 10;
    int sum = 0;
    AddSumRecursion(int startIndex, int endIndex){

        this.startIndex = startIndex;
        this.endIndex = endIndex;
    }


        int add(int startIndex, int endIndex) {


            if(endIndex - startIndex < THRESHOLD) {
                for (int i = startIndex; i <=endIndex; i++) {
                    sum=sum+i;
                }
            }

            else {

                int mid = (startIndex + endIndex) / 2;
                sum+=add(startIndex, mid);
                sum+=add(mid, endIndex);
            }
            return sum;
        }


    public static void main(String[] args) {

        AddSumRecursion recursion= new AddSumRecursion(1,10);
        int sum = recursion.add(1,10);
        System.out.println(sum);
    }
}
