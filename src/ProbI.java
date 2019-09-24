import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class ProbI {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer firstLine = new StringTokenizer(input.readLine());
        int totalBirds = Integer.parseInt(firstLine.nextToken()),
                totalObservations = Integer.parseInt(firstLine.nextToken());

        Bird[] birdList = new Bird[totalBirds];
        for(int birdId = 0; birdId < totalBirds; birdId++) {
            birdList[birdId] = new Bird(birdId);
        }

        for(int observation = 0; observation < totalObservations; observation++) {
            StringTokenizer observationData = new StringTokenizer(input.readLine());

            int birdId = Integer.parseInt(observationData.nextToken()),
            time = Integer.parseInt(observationData.nextToken()),
            longitude = Integer.parseInt(observationData.nextToken()),
            latitude = Integer.parseInt(observationData.nextToken());

            birdList[birdId].setPosition(time, new Position(longitude, latitude));
        }

        int testCases = Integer.parseInt(input.readLine());
        for(int test = 0; test < testCases; test++) {
            StringTokenizer setData = new StringTokenizer(input.readLine());

            int totalSetBirds = Integer.parseInt(setData.nextToken());
            Bird[] setBirds = new Bird[totalSetBirds];

            int minObservations = 200001;
            Bird correctBird = birdList[0];
            for(int i = 0; i < totalSetBirds; i++) {
                int birdId = Integer.parseInt(setData.nextToken());
                setBirds[i] = birdList[birdId];
                if(birdList[birdId].getTimes().size() < minObservations) correctBird = birdList[birdId];
            }

            int sol = 0;

            for(int time : correctBird.getTimes()) {
                boolean skip = false;
                for(Bird bird : setBirds) {
                    if(!bird.containsTime(time)) {
                        skip = true;
                        break;
                    } else if(!correctBird.getPosition(time).equals(bird.getPosition(time))) {
                        skip = true;
                        break;
                    }
                }
                if(skip) continue;
                else {
                    sol ++;
                }
            }

            System.out.println(sol);

        }

    }

    public static class Bird {

        private int id;
        private HashMap<Integer, Position> data;

        public Bird(int id) {
            this.id = id;
            this.data = new HashMap<>();
        }

        public int getId() {
            return this.id;
        }

        public Set<Integer> getTimes() {
            return data.keySet();
        }

        public boolean containsTime(int time) {
            return this.data.containsKey(time);
        }

        public Position getPosition(int time) {
            return data.get(time);
        }

        public void setPosition(int time, Position position) {
            this.data.put(time, position);
        }

    }

    public static class Position {

        private int latitude;
        private int longitude;

        public Position(int latitude, int longitude) {
            this.latitude = latitude;
            this.longitude = longitude;
        }

        public int getLatitude() {
            return this.latitude;
        }

        public int getLongitude() {
            return this.longitude;
        }

        public boolean equals(Position position) {
            return this.latitude == position.getLatitude() && this.longitude == position.getLongitude();
        }

    }

}
