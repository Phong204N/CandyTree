import java.awt.Dimension;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import java.lang.StringBuilder;

import static javax.swing.JOptionPane.*;

public class Control {
    private static final String appTitle = "CandyTree";

    public static void main(String[] args) {
        Object options1[] = { "File Read/Write", "Alignment" };
        int actChoice = JOptionPane.showOptionDialog(null, "Choose tool", appTitle, JOptionPane.DEFAULT_OPTION,
                JOptionPane.WARNING_MESSAGE, null, options1, null);
        if (actChoice == 0)
            testunit1();
        else if (actChoice == 1)
            testunit2();
    }

    private static File askForPath(String customMsg) {
        boolean valid = false;
        String path = "";
        while (!valid) {
            path = JOptionPane.showInputDialog(null, "Enter FASTA File Path (" + customMsg + "):", appTitle,
                    JOptionPane.QUESTION_MESSAGE);
            System.out.println(path.substring(path.length() - 3));
            if (path.length() < 3 || !path.substring(path.length() - 3).equals(".fa"))
                JOptionPane.showMessageDialog(null, "FILE PATH MUST BE OF TYPE .FA", appTitle,
                        JOptionPane.WARNING_MESSAGE);
            else
                valid = true;
        }
        File file = new File(path);
        return file;
    }

    private static void testunit2() {
        ArrayList<CandyTree> ct = new ArrayList<>();
        Boolean flag = false;
        while (!flag) {
            File file = askForPath("File must contain exactly 2 FASTA entries to align");
            FastaReader fr = new FastaReader(file);
            ct = fr.readFasta();
            if (ct.size() == 2)
                flag = true;
            else
                JOptionPane.showMessageDialog(null, "FILE CONTAINS LESS/MORE THAN 2 FASTA ENTRIES", appTitle,
                        JOptionPane.WARNING_MESSAGE);
        }

        Alignment aligner = new Alignment(ct.get(0).getSequence(), ct.get(1).getSequence());
        String[] aligned = aligner.align();

        StringBuilder show = new StringBuilder();
        show.append("<html><table><tr>");
        for (int ia = 0; ia < aligned[0].length(); ia++) {
            show.append("<td>" + aligned[0].charAt(ia) + "</td>");
        }
        show.append("</tr><tr>");
        for (int ia = 0; ia < aligned[1].length(); ia++) {
            show.append("<td>" + aligned[1].charAt(ia) + "</td>");
        }
        show.append("</tr></table><br/><p>Score: " + aligner.getScore() + "</p>");

        // "<html>" + aligned[0] + "<br/>" + aligned[1]
        JOptionPane.showMessageDialog(null, show, appTitle,
                JOptionPane.INFORMATION_MESSAGE, null);
    }

    private static void testunit1() {
        File file = askForPath("");

        Object[] options1 = { "READ", "WRITE", "APPEND" };
        int actChoice = showOptionDialog(null, "Read, Write, or Append to File", appTitle,
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.WARNING_MESSAGE, null, options1, null);

        if (actChoice == 0) {
            FastaReader fr = new FastaReader(file);
            ArrayList<CandyTree> ct = fr.readFasta();

            for (int ia = 0; ia < ct.size(); ia++) {
                JTextArea ta = new JTextArea(ct.get(ia).format(), 50, 200);
                JScrollPane sp = new JScrollPane(ta);
                sp.setPreferredSize(new Dimension(500, 500));
                ta.setWrapStyleWord(true);
                ta.setLineWrap(true);
                ta.setOpaque(false);
                ta.setBorder(null);
                ta.setEditable(false);
                ta.setFocusable(false);

                showMessageDialog(null,
                        sp,
                        appTitle + ": FASTA " + (ia + 1) + "/" + ct.size(),
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
