public List<MobileDevice> mergeConnectedDevicesList(){
    List<MobileDevice> list = new ArrayList<>();
    List<PortableDevice> JMTPDevices = DeviceManager.getConnectedDevice();
    Map<String,String> ADBDevices = DeviceManager.getConnectedDevicesADB();
    for (PortableDevice device : JMTPDevices){
        if (ADBDevices.containsKey(device.getSerialNumber())){
            boolean ready = DeviceManager.checkDevice(device);
            list.add(new MobileDevice(device,ADBDevices.get(device.getSerialNumber()),ready));
        }
    }
    return list;
}