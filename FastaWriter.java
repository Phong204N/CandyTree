import java.io.FileWriter;
import java.io.File;
import java.io.IOException;

public class FastaWriter {

    private File file;

    FastaWriter(File file) {
        this.file = file;
    }

    public void writeFasta(CandyTree ct, Boolean append) {
        try {
            FileWriter fw = new FileWriter(file, append);
            if (append)
                fw.write("\n" + ct.format());
            else
                fw.write("\n" + ct.format());
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}