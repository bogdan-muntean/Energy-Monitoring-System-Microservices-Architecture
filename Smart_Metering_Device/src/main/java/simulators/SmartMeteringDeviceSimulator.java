//package simulators;
//
//import components.Measurement;
//import components.MessageProducer;
//import components.SensorReader;
//import org.springframework.stereotype.Component;
//
//@Component
//public class SmartMeteringDeviceSimulator {
//    public static void main(String[] args) {
//        String csvFilePath = "../assets/sensor.csv";
//        String deviceId = "1"; // ID unic al dispozitivului
//        String rabbitMqHost = "localhost";
//        System.out.println("TEST");
//        try {
//            SensorReader reader = new SensorReader(csvFilePath);
//            MessageProducer producer = new MessageProducer(rabbitMqHost); // RabbitMQ pe localhost
//
//            while (reader.hasNextMeasurement()) {
//                Measurement measurement = reader.nextMeasurement();
//                System.out.println("masuratoarea: " + measurement);
//                producer.sendMeasurement(deviceId, measurement);
//
////                Thread.sleep(600000); // Așteaptă 10 minute (600.000 ms)
//                Thread.sleep(5000); // Așteaptă 5 secunde
//            }
//
//            producer.close();
//        } catch (Exception e) {
//            System.err.println("Eroare: " + e.getMessage());
//            e.printStackTrace();
//        }
//    }
//
//
//}