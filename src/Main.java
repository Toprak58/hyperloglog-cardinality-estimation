import java.util.HashSet;
import java.util.Random;

public class Main {

    public static void main(String[] args) {

        HyperLogLog hll = new HyperLogLog(10);

        HashSet<Integer> realSet = new HashSet<>();

        Random random = new Random();

        for (int i = 0; i < 100000; i++) {

            int value = random.nextInt(200000);

            realSet.add(value);

            hll.add(String.valueOf(value));
        }

        double estimate = hll.estimate();

        System.out.println("Gerçek Cardinality: " + realSet.size());
        System.out.println("HLL Tahmini: " + estimate);
    }
}
