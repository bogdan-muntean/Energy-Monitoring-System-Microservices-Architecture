package components;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

public class SensorReader {
    private Iterator<String> lines;

    public SensorReader(String filePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        lines = reader.lines().iterator();
//        System.out.println(reader.lines().iterator());

        lines.next(); // Sari peste antet
    }
    public boolean hasNextMeasurement() {
        return lines.hasNext();
    }

    public Measurement nextMeasurement() {
        String line = lines.next();
//        System.out.println(line);
        double value = Double.parseDouble(line);
//        System.out.println(value);
        return new Measurement(System.currentTimeMillis(), value);
    }
}