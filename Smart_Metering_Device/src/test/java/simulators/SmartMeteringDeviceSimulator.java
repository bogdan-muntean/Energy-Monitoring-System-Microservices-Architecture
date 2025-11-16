package simulators;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class SmartMeteringDeviceSimulator {

    private static final String QUEUE_NAME = "energy_data";  // numele cozii din RabbitMQ
    private static final String CSV_FILE = "path/to/sensor.csv"; // calea catre fisierul CSV
    private static final String DEVICE_ID = "1";  // ID unic pentru dispozitivul simulat

    public static void main(String[] args) {
        // Configurare conexiune RabbitMQ
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("sparrow.rmq.cloudamqp.com");
        factory.setPort(5671);
        factory.setUsername("bsikrzxb");
        factory.setPassword("yl5Jt60ZMZXpMvBrfIFnGyC6_ufyQGx_");
        factory.setVirtualHost("bsikrzxb");

        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {

            channel.queueDeclare(QUEUE_NAME, false, false, false, null);

            // Task care trimite date la fiecare 10 minute
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    sendMeasurements(channel);
                }
            }, 0, 600000);  // Programare task la fiecare 10 minute
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void sendMeasurements(Channel channel) {
        try (BufferedReader br = new BufferedReader(new FileReader(CSV_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                long timestamp = System.currentTimeMillis();
                double measurementValue = Double.parseDouble(values[0]);

                // Creare mesaj JSON
                JSONObject message = new JSONObject();
                message.put("timestamp", timestamp);
                message.put("device_id", DEVICE_ID);
                message.put("measurement_value", measurementValue);

                System.out.println(message);

                // Trimite mesajul la RabbitMQ
                channel.basicPublish("", QUEUE_NAME, null, message.toString().getBytes());
                System.out.println("Sent: " + message.toString());

                // Pauza de 10 minute in milisecunde pentru a simula intervalul real
                Thread.sleep(600000);  // 10 minute
            }
        } catch (IOException | InterruptedException | JSONException e) {
            e.printStackTrace();
        }
    }
}
