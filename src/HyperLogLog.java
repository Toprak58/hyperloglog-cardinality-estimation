import java.util.Arrays;

public class HyperLogLog {

    private int p;
    private int m;
    private byte[] registers;

    public HyperLogLog(int p) {
        this.p = p;
        this.m = 1 << p;
        this.registers = new byte[m];
    }

    public void add(String value) {

        long hash = HashFunction.hash(value);

        int bucket = (int) (hash >>> (64 - p));

        long remaining = (hash << p) | (1L << (p - 1));

        int leadingZeros = Long.numberOfLeadingZeros(remaining) + 1;

        registers[bucket] = (byte) Math.max(registers[bucket], leadingZeros);
    }

    public double estimate() {

        double alpha;

        if (m == 16) alpha = 0.673;
        else if (m == 32) alpha = 0.697;
        else if (m == 64) alpha = 0.709;
        else alpha = 0.7213 / (1 + 1.079 / m);

        double sum = 0.0;

        for (int i = 0; i < m; i++) {
            sum += Math.pow(2.0, -registers[i]);
        }

        double estimate = alpha * m * m / sum;

        int zeros = 0;
        for (int i = 0; i < m; i++) {
            if (registers[i] == 0) zeros++;
        }

        if (estimate <= 2.5 * m && zeros != 0) {
            estimate = m * Math.log((double) m / zeros);
        }

        return estimate;
    }

    public void merge(HyperLogLog other) {

        if (this.m != other.m) {
            throw new IllegalArgumentException("Register size mismatch");
        }

        for (int i = 0; i < m; i++) {
            this.registers[i] = (byte) Math.max(this.registers[i], other.registers[i]);
        }
    }

    public void printRegisters() {
        System.out.println(Arrays.toString(registers));
    }
}
