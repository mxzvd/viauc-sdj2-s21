public class Main {
    public static void main(String[] args) {

        Project project1 = new Project("Project 1", "uk");
        project1.addGlossaryItem("Client", "The client part of a client/server" + "application.");
        project1.addGlossaryItem("User", "End user in form of a doctor or a nurse.");
        project1.addGlossaryItem("Account", "A location on the server application " + "storing username, password and phone number.");
        System.out.println("Project 1: Client: " + project1.getDefinition("Client"));
        System.out.println(project1);

        Project project2 = new Project("Project 2", "uk");
        try {
            project2.addGlossaryItem("Client", "The client part of a client/server" + "application.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Project 2: Client: " + project2.getDefinition("Client"));

        Project project3 = new Project("Project 3", "dk");
        project3.addGlossaryItem("Client", "The client part of a client/server" + "application.");
        project3.addGlossaryItem("Usesdfgr", "Ensdfgsdfgd user in form of a doctor or a nurse.");
        project3.addGlossaryItem("Accougsdfgnt", "A locadsgdfgtion on the server application " + "storing username, password and phone number.");
        System.out.println("Project 3: Client: " + project3.getDefinition("Client"));
        System.out.println(project3);

        Project project4 = new Project("Project 4", "dk");
        try {
            project4.addGlossaryItem("Clientc", "The clientc part of a client/server" + " application.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Project 2: Client: " + project4.getDefinition("Clientc"));
    }
}
