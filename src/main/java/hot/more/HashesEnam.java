package hot.more;

public enum HashesEnam {

    md2("md2"),
    md5("md5"),
    sha1("sha1"),
    sha256("sha256"),
    sha384("sha384"),
    sha512("sha512");

    private final String info;

    private HashesEnam(String info) {
        this.info = info;
    }

    public String getInfo() {
        return info;
    }
    
}
