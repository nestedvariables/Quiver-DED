package nestedvar.Quiver;

import javax.security.auth.login.LoginException;

public class Launcher {

    static Quiver quiver;

    public static void main(String[] args) {
        try {
            /*
            JFrame frame = new JFrame("Quiver");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(250,250);
            JButton button = new JButton("Kill");
            frame.getContentPane().add(button);
            frame.setVisible(true);
            */

            quiver = new Quiver();
        }
        catch (LoginException e) {
            System.out.println("🎟 Looks like your token is invalid.");
            System.exit(1);
        }
    }

    /**
     * Kills and completely
     * reboots Quiver and JDA
     */
    public static void restart() {
        try {
            quiver.exit();
            quiver = new Quiver();
        }
        catch (LoginException e) {
            System.out.println("🎟 Looks like your token is invalid.");
            System.exit(1);
        }
    }
}