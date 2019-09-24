import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ProbD {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        int w = Integer.parseInt(input.readLine()),
        z = Integer.parseInt(input.readLine()),
        tMax = Integer.parseInt(input.readLine()),
        n = Integer.parseInt(input.readLine()),
        p = Integer.parseInt(input.readLine());

        Slice[] slices = new Slice[n];
        StringTokenizer slicesData = new StringTokenizer(input.readLine());
        for(int i = 0; i < n; i++) {
            slices[i] = new Slice(Integer.parseInt(slicesData.nextToken()), Integer.parseInt(slicesData.nextToken()));
        }

        boolean[] behaviour = new boolean[p];
        StringTokenizer behaviourData = new StringTokenizer(input.readLine());
        for(int i = 0; i < p; i++) {
            behaviour[i] = Integer.parseInt(behaviourData.nextToken()) == 1;
        }

        int sol = 0;
        for(int i = 0; i < tMax; i++) {
            boolean found = false;
            for(int j = 0; j < n; j++) {
                if(z >= slices[j].s && z <= slices[j].e && behaviour[i % p]) {
                    sol++;
                    found = true;
                    break;
                }
            }

            z -= w;
            if(z <= 0) z += 360;
            else z %= 360;

            if(!found && behaviour[i % p]) {
                System.out.println(sol);
                System.exit(0);
            }
        }
        System.out.println(sol);
    }

    public static class Slice {

        public int s, e;

        public Slice(int s, int e) {
            this.s = s;
            this.e = e;
        }

    }

}
