public static List<PortableDevice> getConnectedDeviceJMTP(){
    PortableDeviceManager manager = new PortableDeviceManager();
    List<PortableDevice> list = new ArrayList<>();
    for(PortableDevice device : manager) {
        device.open();
        if (!device.getModel().contains("Apple") || !device.getModel().contains("Windows Phone"){
            list.add(device);
        }
        device.close();
    }
    return list;
}