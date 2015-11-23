private static final String ACRYL_LOOK_AND_FELL = "com.jtattoo.plaf.acryl.AcrylLookAndFeel";

private static void setGUI() {
    try {
        Properties props = new Properties();
        props.put("logoString", "EBWorld");
        AcrylLookAndFeel.setTheme(props);
        UIManager.setLookAndFeel(ACRYL_LOOK_AND_FELL);
    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException exc) {
        Logger.getLogger("Unable to load the GUI");
    }
}