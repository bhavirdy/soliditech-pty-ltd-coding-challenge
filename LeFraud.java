import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@SuppressWarnings("deprecation")// ignore this
public class LeFraud {

    public static void main(String[] args) throws Exception {
        String reference = "21042023abcde";
        System.out.println(String.format("reference: %s is %s", reference, validate(reference) ? "valid" : "invalid"));
    }

    public static boolean validate(String reference)
    {
        //write validation logic here
        
        // Check if the reference has the correct length
        if (reference.length() != 13) {
            return false;
        }

        // Extract the date portion from the reference
        String dateStr = reference.substring(0, 6);

        // Parse the date from the string
        SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
        Date referenceDate;
        try {
            referenceDate = dateFormat.parse(dateStr);
        } catch (ParseException e) {
            // If the date portion is not a valid date, the reference is invalid
            return false;
        }

        // Get the current date
        Date currentDate = new Date();

        // Compare the reference date with the company's inception date and the current date
        Date inceptionDate = new Date(100, 5, 1); // June 1, 2000 (year is offset by 1900)
        if (referenceDate.before(inceptionDate) || referenceDate.after(currentDate)) {
            return false;
        }

        // Check if the remaining characters are all alphabetical (a-z)
        String charSequence = reference.substring(6);
        if (!charSequence.matches("[a-z]+")) {
            return false;
        }

        // All validations passed, the reference is valid
        return true;
    }
}