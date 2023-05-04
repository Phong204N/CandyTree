
class CandyTree {
    private String seqTitle;
    private String sequence;

    CandyTree(String seqTitle, String sequence) {
        this.seqTitle = seqTitle;
        this.sequence = sequence;
    }

    // CandyTree(String dump) {
    // this.seqTitle = dump.substring(0, dump.indexOf("\n"));
    // this.sequence = dump.substring(dump.indexOf("\n"));
    // }

    public String format() {
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

}