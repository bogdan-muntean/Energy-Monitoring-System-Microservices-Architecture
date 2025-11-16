//package commands;
//
//import com.example.Smart_Metering_Device.SmartMeteringDeviceApplication;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//@Component
//public class DeviceCommandRunner implements CommandLineRunner {
//
//    private final SmartMeteringDeviceApplication application;
//
//    public DeviceCommandRunner(SmartMeteringDeviceApplication application) {
//        this.application = application;
//    }
//
//    @Override
//    public void run(String... args) throws Exception {
//        String test = System.getProperty("spring-boot.run.arguments");
//        System.out.println("Raw command arguments: " + test);
//
//        if (args.length == 0) {
//            System.out.println("No command provided. Use 'start-device' or other commands.");
//            return;
//        }
//
//        String command = args[0];
//        if ("start-device".equals(command)) {
//            if (args.length < 3) {
//                System.out.println("Utilizare: start-device <deviceId> <csvFilePath>");
//            } else {
//                String csvFilePath = args[1];
//                String deviceId = args[2];
//                application.startDeviceSimulation(csvFilePath, deviceId);
//            }
//        } else {
//            System.out.println("Comanda necunoscuta: " + command);
//        }
//    }
//}
