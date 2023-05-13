
import java.util.Arrays;

public class EntanglingStrings
{
	public String entangle(String str1, String str2)
	{
		StringBuilder result = new StringBuilder();

        int maxLength = Math.max(str1.length(), str2.length());
        for (int i = 0; i < maxLength; i++) {
            if (i < str1.length()) {
                result.append(str1.charAt(i));
            }
            if (i < str2.length()) {
                result.append(str2.charAt(str2.length() - 1 - i));
            }
        }

        return result.toString();
	}

	public String[] disentangle(String inputString, int largerStringLength)
	{
		String[] result = new String[2];

        StringBuilder leftString = new StringBuilder();
        StringBuilder rightString = new StringBuilder();

        int leftLength = Math.max(0, largerStringLength);
        int rightLength = Math.max(0, -largerStringLength);

        for (int i = 0; i < inputString.length(); i++) {
            char c = inputString.charAt(i);
            if (i % 2 == 0) {
                if (leftString.length() < leftLength) {
                    leftString.append(c);
                }
            } else {
                if (rightString.length() < rightLength) {
                    rightString.insert(0, c);
                }
            }
        }

        result[0] = leftString.toString();
        result[1] = rightString.toString();

        return result;
	}
	
	public static void main(String[] args)
	{
		EntanglingStrings entangler = new EntanglingStrings();

        // Testing the entangle method
        String str1 = "Hello";
        String str2 = "World";
        String entangled = entangler.entangle(str1, str2);
        System.out.println("Entangled: " + entangled);

        // Testing the disentangle method
        String inputString = "H!edlllrooW";
        int largerStringLength = 6;
        String[] disentangled = entangler.disentangle(inputString, largerStringLength);
        System.out.println("Disentangled: " + Arrays.toString(disentangled));
	}
}
