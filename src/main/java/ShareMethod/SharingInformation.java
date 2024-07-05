package ShareMethod;

public class SharingInformation{
    public void share(int phoneNumber) {
        System.out.println("Sharing ticket with phone number: " + phoneNumber);
    }
    public void share(int phoneNumber, String emailAddress) {
        System.out.println("Sharing ticket with phone number: " + phoneNumber + " and email address: " + emailAddress);
    }

}
