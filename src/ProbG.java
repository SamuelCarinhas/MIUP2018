import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ProbG {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(input.readLine());

        int[] pizza = new int[n];
        StringTokenizer pizzaData = new StringTokenizer(input.readLine());
        for(int i = 0; i < n; i++) {
            pizza[i] = Integer.parseInt(pizzaData.nextToken());
        }

        int maxSum = maxSum(pizza);
        int reverseMaxSum = 0;
        int maxValue = -100;
        for(int i = 0; i < n; i++) {
            if(pizza[i] > maxValue) maxValue = pizza[i];
            reverseMaxSum += pizza[i];
            pizza[i] *= -1;
        }

        if(maxValue < 0) System.out.println(maxValue);
        else System.out.println(Math.max(maxSum, reverseMaxSum + maxSum(pizza)));
    }

    private static int maxSum(int[] pizza) {
        int max = 0, current = 0;
        for(int i = 0; i < pizza.length; i++) {
            current += pizza[i];
            if(current < 0) current = 0;
            if(current > max) max = current;
        }

        return max;
    }

}
