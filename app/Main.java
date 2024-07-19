package app;
import controller.Controller;
import view.View;

public class Main {
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
