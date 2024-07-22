package app;
import controller.Controller;
import view.View;

/**
 * The Main class serves as the entry point for the hotel management application.
 */
public class Main {

    /**
     * Main method that launches the application.
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                View view = new View();
                new Controller(view);
            }
        });
    }
}
