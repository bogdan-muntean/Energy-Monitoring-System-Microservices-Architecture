package components;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.json.JSONObject;

public class MessageProducer {
    private static final String QUEUE_NAME = "energy_data";
    private Connection connection;
    private Channel channel;

    public MessageProducer(String host) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("sparrow.rmq.cloudamqp.com");
        factory.setPort(5671); // Portul pentru AMQPS
        factory.setUsername("bsikrzxb");
        factory.setPassword("yl5Jt60ZMZXpMvBrfIFnGyC6_ufyQGx_");
        factory.setVirtualHost("bsikrzxb");
        factory.useSslProtocol();

        connection = factory.newConnection();
        channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
    }

    public void sendMeasurement(String deviceId, Measurement measurement) throws Exception {
        JSONObject json = new JSONObject();
        json.put("timestamp", measurement.getTimestamp());
        json.put("device_id", deviceId);
        json.put("measurement_value", measurement.getValue());

        channel.basicPublish("", QUEUE_NAME, null, json.toString().getBytes());
        System.out.println("S-a trimis device-ul: " + deviceId + " cu timestamp: " + measurement.getTimestamp()
                + " si valoarea" + measurement.getValue());
        System.out.println("Mesaj trimis: " + json);
    }

    public void close() throws Exception {
        channel.close();
        connection.close();
    }
}
