private static final String TAG_PROJECTS = "Projects";
private static final String TAG_PROJECT = "Project";
private static final String ATTRIBUTE_NAME = "Nome";
private static final String ATTRIBUTE_PROJECT_DATE = "DataProgetto";
private static final String ATTRIBUTE_CURRENT_PROJECT = "ProgettoAttivo";

public static boolean createProjectsXML(ProjectsXML projects_data, String path) {
    try {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        Document doc = docBuilder.newDocument();
        Element rootElement = doc.createElement(TAG_PROJECTS);
        doc.appendChild(rootElement);

        if (projects_data.getCurrent_project() != null){
            rootElement.setAttribute(ATTRIBUTE_CURRENT_PROJECT, projects_data.getCurrent_project());
        }else{
            rootElement.setAttribute(ATTRIBUTE_CURRENT_PROJECT, "");
        }

        for (ProjectTag proj: projects_data.getProjectsList()){
            if (!proj.getDeleted()){
                Element project_elem = doc.createElement(TAG_PROJECT);
                project_elem.setAttribute(ATTRIBUTE_NAME, proj.getName());
                project_elem.setAttribute(ATTRIBUTE_PROJECT_DATE, proj.getDate());
                rootElement.appendChild(project_elem);
            }
        }

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File(path));

        transformer.transform(source, result);
        return true;
    } catch (ParserConfigurationException e) {
        e.printStackTrace();
    } catch (TransformerException e) {
        e.printStackTrace();
    }
    return false;
}