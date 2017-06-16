import javax.swing.UIManager;

/**
 * Contains the main method
 * 
 * @author Yuting, Ganashsai
 */

public class Main implements GlobalVar{
    public static void main (String args[]){
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch(Exception e) {
        }
        Audio.init();
        frame.setTitle("Rhythm Master");
    }

}
