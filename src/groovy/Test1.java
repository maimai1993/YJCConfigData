public class Test1 {
    public static void main(String[] args) {

        System.out.println("121");

        int[] A={1,2,5,6,7,8};
        int[] B={2,3,6,8,9};
        merge(A,6,B,5);

        for (int i:
        A){
            System.out.println(i);
        }

    }


    public static  void merge(int A[], int m, int B[], int n) {



//        for(int i=0;i<n;i++){
//            A[m+i]=B[i];
//        }
        for(int j=0;j<A.length;j++){

            for(int k=0;k<A.length-1-j;k++){

                if(A[k]>=A[k+1]){
                    int temp=A[k+1];
                    A[k+1]=A[k];
                    A[k]=temp;
                }



            }

        }


    }
}

