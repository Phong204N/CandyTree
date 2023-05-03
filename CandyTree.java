import java.io.File;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import static javax.swing.JOptionPane.*;

class CandyTree {
    private String seqTitle;
    private String sequence;

    private static final String appTitle = "CandyTree";

    CandyTree(String seqTitle, String sequence) {
        this.seqTitle = seqTitle;
        this.sequence = sequence;
    }

    // CandyTree(String dump) {
    // this.seqTitle = dump.substring(0, dump.indexOf("\n"));
    // this.sequence = dump.substring(dump.indexOf("\n"));
    // }

    public String format() {
        // String fasta = "";
        // fasta = ">" + seqTitle + "\n" + sequence;
        // return fasta;
        return ">" + seqTitle + "\n" + sequence;
    }

    public String getTitle() {
        return seqTitle;
    }

    public String getSequence() {
        return sequence;
    }

    public void setTitle(String setTitle) {
        this.seqTitle = setTitle;
    }

    public void setSequence(String setSequence) {
        this.sequence = setSequence;
    }

    public static void main(String[] args) {
        testunit1();
    }

    public static void testunit1() {
        // C:/Users/phong/Downloads/NM_022168.4.exons.fa
        boolean valid = false;
        String path = "";
        while (!valid) {
            path = JOptionPane.showInputDialog(null, "Enter FASTA File Path:", appTitle,
                    JOptionPane.QUESTION_MESSAGE);
            System.out.println(path.substring(path.length() - 3));
            if (path.length() < 3 || !path.substring(path.length() - 3).equals(".fa"))
                JOptionPane.showMessageDialog(null, "FILE PATH MUST BE OF TYPE .FA", appTitle,
                        JOptionPane.WARNING_MESSAGE);
            else
                valid = true;
        }
        File file = new File(path);

        Object[] options = { "READ", "WRITE", "APPEND" };
        int actChoice = showOptionDialog(null, "Read, Write, or Append to File", appTitle,
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.WARNING_MESSAGE, null, options, null);

        if (actChoice == 0) {
            FastaReader fr = new FastaReader(file);
            ArrayList<CandyTree> ct = fr.readFasta();

            for (int ia = 0; ia < ct.size(); ia++) {
                showMessageDialog(null,
                        ct.get(ia).format(),
                        "FASTA " + (ia + 1) + "/" + ct.size(),
                        JOptionPane.INFORMATION_MESSAGE);
            }
        }

        else {
            FastaWriter fw = new FastaWriter(file);
            String envoyTitle = JOptionPane.showInputDialog(null, "Enter title of new FASTA: ", appTitle,
                    JOptionPane.PLAIN_MESSAGE);
            String envoySequence = JOptionPane.showInputDialog(null, "Enter sequence of new FASTA: ", appTitle,
                    JOptionPane.PLAIN_MESSAGE);
            fw.writeFasta(new CandyTree(envoyTitle, envoySequence), (actChoice - 1 == 1));
        }

    }
}