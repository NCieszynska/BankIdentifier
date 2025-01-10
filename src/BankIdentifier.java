import java.io.*;
import java.net.URL;
import java.util.Scanner;
public class BankIdentifier {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        System.out.print("Podaj trzy pierwsze cyfry numeru konta:");
        String bankCode = scanner.nextLine();

        String url = "https://ewib.nbp.pl/plewibnra?dokNazwa=plewibnra.txt";

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new URL(url).openStream()))){
            String line;
            boolean found = false;

            while ((line = reader.readLine()) !=null){
                if (line.startsWith(bankCode)){
                    String[] parts = line.split("\t");
                    if (parts.length >= 2){
                        String shortBankCode = parts[0].trim();
                        String bankName = parts[1].trim();

                        System.out.println("Numer banku:" + shortBankCode);
                        System.out.println("Nazwa banku:" + bankName);
                        found = true;
                        break;
                    }
                }
            }
            if (!found){
                System.out.println("Nie znalezniono banku dla podanego kodu:" + bankCode);
            }
        }
        catch (IOException e){
            System.out.println("Wystąpił błąd podczas wczytywania pliku:" + e.getMessage());
            e.printStackTrace();
        }
    }
}
