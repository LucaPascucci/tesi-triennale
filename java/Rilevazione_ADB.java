private static final String ADB_DEVICES = "adb devices";
private static final String ADB_S = "adb -s ";
private static final String ADB_RIL_SERIAL_NUMBER = " shell getprop ril.serialnumber";

public static Map<String,String> getConnectedDevicesADB(){
    Map<String,String> devices = new HashMap<>();
    boolean checkStartList = false;
    String line = "";
    try {
        Process proc = Runtime.getRuntime().exec(ADB_DEVICES);
        BufferedReader reader = new BufferedReader(new InputStreamReader(proc.getInputStream()));
        while((line = reader.readLine()) != null) {
            if (checkStartList && line.length() > 0){
                int finish = line.indexOf(9);
                String ADBSerial = line.substring(0, finish);
                String RilSerial = getRilSerialADB(ADBSerial);
                if (RilSerial != null){
                    devices.put(RilSerial, ADBSerial);
                }
            }
            if (line.startsWith("List")){
                checkStartList = true;
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
    return devices;
}

private static String getRilSerialADB(String ADBserial){
    String adbRilSerial = ADB_S + ADBserial + ADB_RIL_SERIAL_NUMBER;
    String line = "";
    try {
        Process proc = Runtime.getRuntime().exec(adbRilSerial);
        BufferedReader reader = new BufferedReader(new InputStreamReader(proc.getInputStream()));
        while((line = reader.readLine()) != null) {
            return line;
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
    return null;
}