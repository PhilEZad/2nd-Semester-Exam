package Application.BE;

public class Inquiry
{
    private int id;
    private String inquiry;

    public Inquiry(int id, String inquiry)
    {
        this.id = id;
        this.inquiry = inquiry;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInquiry() {
        return inquiry;
    }

    public void setInquiry(String inquiry) {
        this.inquiry = inquiry;
    }

    @Override
    public String toString()
    {
        return inquiry;
    }
}
