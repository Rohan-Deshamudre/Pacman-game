import nl.tudelft.jpacman.Launcher;
import nl.tudelft.jpacman.game.MultiLevelGame;

import java.io.IOException;

class MultiLevelLauncher extends Launcher {

    private MultiLevelGame multiGame;

    @Override
    public MultiLevelGame getGame(){
        return multiGame;
    }

    /**
     * Main execution method for the Launcher.
     *
     * @param args
     *            The command line arguments - which are ignored.
     * @throws IOException
     *             When a resource could not be read.
     */
    public static void main(String[] args) throws IOException {
        new MultiLevelLauncher().launch();
    }


}
