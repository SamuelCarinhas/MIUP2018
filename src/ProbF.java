import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ProbF {

    private static int[] primes = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47};

    private static long hcn = 0, divisors = 0;
    private static void findNearHCN(int pos, int limit, long num, int div, long x) {
        if(div > divisors) {
            hcn = num;
            divisors = div;
        } else if (div == divisors && num < hcn) {
            hcn = num;
        }

        if(pos == 15) return;

        int p = primes[pos];

        for(int i = 1; i <= limit; i++) {
            if(num * p > x) break;
            findNearHCN(pos + 1, i, num * p, div * (i + 1), x);
            p *= primes[pos];
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        long x = Long.parseLong(input.readLine());

        findNearHCN(0, 30, 1, 1, x);

        System.out.println(divisors);
    }

}
