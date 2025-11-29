import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the first 3  digits of the bank account number: ");
        String bankCodeInput = scanner.nextLine().trim();

        if (!bankCodeInput.matches("\\d{3,4}")) {
            System.out.println("Invalid input. Please enter 3 or 4 digits.");
            return;
        }

        String fileURL = "https://ewib.nbp.pl/plewibnra?dokNazwa=plewibnra.txt";
        Charset[] charsetsToTry = {
                Charset.forName("ISO-8859-2"),
                Charset.forName("Windows-1250"),
                Charset.forName("UTF-8")
        };

        boolean found = false;
        for (Charset cs : charsetsToTry) {
            try {
                if (findBankName(fileURL, cs, bankCodeInput)) {
                    found = true;
                    break;
                }
            } catch (IOException e) {

            }
        }

        if (!found) {
            System.out.println("Bank with the given code was not found.");
        }
    }
    private static boolean findBankName(String fileUrl, Charset charset, String bankCode) throws IOException {
        URL url = new URL(fileUrl);
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), charset))) {

            reader.readLine();
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\t");
                if (parts.length >= 2) {
                    String bankNumber = parts[0];
                    if (bankNumber.startsWith(bankCode)) {
                        String bankName = parts[1];
                        System.out.println("Bank name: " + bankName);
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
