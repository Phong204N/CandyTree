import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class FastaReader {

    private File file;

    FastaReader(File file) {
        this.file = file;
    }

    public ArrayList<CandyTree> readFasta() {
        ArrayList<CandyTree> ct = new ArrayList<>();
        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);

            String read = br.readLine();
            while (read != null) {
                String title = read;
                String sequence = "";
                read = br.readLine();
                while (read != null && read.charAt(0) != '>') {
                    sequence += read;
                    read = br.readLine();
                }
                ct.add(new CandyTree(title, sequence));
            }

            br.close();
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ct;
    }

}
