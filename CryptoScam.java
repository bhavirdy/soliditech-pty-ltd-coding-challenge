import java.util.HashMap;

public class CryptoScam {

	public static HashMap<String, Long> tallyMoneyStolen(String rawInput, HashMap<String, String> walletToName)
	{
		HashMap<String, Long> talliedAmounts = new HashMap<>();
		
		// Split the raw input into individual transactions
        	String[] transactions = rawInput.split(";");

        	// Process each transaction
        	for (String transaction : transactions) {
            		// Split the transaction into wallet ID and amount
            		String[] parts = transaction.split(":");
            		if (parts.length != 2) {
                		continue; // Skip invalid transactions
            		}

            		String walletId = parts[0];
            		long amount = Long.parseLong(parts[1]);

            		// Find the associated real name for the wallet ID
            		String realName = walletToName.get(walletId);
            		if (realName == null) {
                		continue; // Skip transactions without an associated real name
            		}

            		// Update the tallied amount for the real name
            		long currentAmount = talliedAmounts.getOrDefault(realName, 0L);
            		long newAmount = currentAmount + amount;
            		talliedAmounts.put(realName, newAmount);
        	}

		return talliedAmounts;
	}

	/**
	 * Small main method to test if your method yields the correct answer
	 * We won't be evaluating the main method, only your implementation within the tallyMoneyStolen method
	 * @param args
	 */
	public static void main(String[] args)
	{
		HashMap<String, String> markedWallets = new HashMap<>();
		markedWallets.put("qwghn95kj","Scam Bankman Fraud");
		markedWallets.put("qwerrtq21","Scam Bankman Fraud");
		markedWallets.put("adwwh112a","Joke Paul");

		HashMap<String, Long> expectedAnswer = new HashMap<>();
		expectedAnswer.put("Scam Bankman Fraud", 51883L);
		expectedAnswer.put("Joke Paul", 11028L);

		String transactionRecord = "qwghn95kj:49883;adwwh112a:11028;qwerrtq21:2000";

		HashMap<String, Long> yourAnswer = tallyMoneyStolen(transactionRecord, markedWallets);

		if (!yourAnswer.keySet().equals(expectedAnswer.keySet()))
		{
			System.out.println("Keys do not match!");
		}
		else if (!yourAnswer.get("Scam Bankman Fraud").equals(expectedAnswer.get("Scam Bankman Fraud")) || !yourAnswer.get("Joke Paul").equals(expectedAnswer.get("Joke Paul")))
		{
			System.out.println("Values do not match!");
		}
		else
		{
			System.out.println("Everything matches!");
		}
	}

}
