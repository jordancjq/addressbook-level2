package seedu.addressbook.data.person;

public class Contact {

    protected String value;
    protected boolean isPrivate;

    public Contact() {
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    public boolean isPrivate() {
        return isPrivate;
    }
}
