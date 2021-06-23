package tests.mail.business_object;

public class Letter {
    private String toWhom;
    private String title;
    private String bodyText;
    public Letter(String toWhom, String title, String bodyText){
        this.toWhom=toWhom;
        this.title=title;
        this.bodyText=bodyText;
    }

    public String getToWhom() {
        return toWhom;
    }

    public String getTitle() {
        return title;
    }

    public String getBodyText() {
        return bodyText;
    }
}
