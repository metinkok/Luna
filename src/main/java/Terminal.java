import java.io.IOException;
public class Terminal {
    private String command;
    Terminal(String command){
        this.command = command;
        run();
    }
    private void run(){
        Runtime run = Runtime.getRuntime();
        Process pr = null;
        try {
            pr = run.exec(command);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
