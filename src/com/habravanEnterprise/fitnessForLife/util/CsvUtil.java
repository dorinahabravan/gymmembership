package com.habravanEnterprise.fitnessForLife.util;

// Separam vaorile prin virgula CSV.
import com.habravanEnterprise.fitnessForLife.models.Client;
import com.habravanEnterprise.fitnessForLife.models.RegisterMesage;
import java.time.LocalDate;

// EOL of line EOF.
//Din rind nou "\n".
public class CsvUtil {

    public static final String CSV = ",";
    public static final String EOL = "\n";

    public static String clientToLine(Client client) {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append(client.getCustomerid());
        stringbuilder.append(CSV);
        
        stringbuilder.append(client.getUserID());
        stringbuilder.append(CSV);

        stringbuilder.append(client.getFullname());
        stringbuilder.append(CSV);

        stringbuilder.append(client.getEmail_address());
        stringbuilder.append(CSV);

        stringbuilder.append(client.getDate_of_birth());
        stringbuilder.append(CSV);

        stringbuilder.append(client.getLocation());
        stringbuilder.append(CSV);

        stringbuilder.append(client.getPhone_number());
        stringbuilder.append(EOL);

        stringbuilder.trimToSize();

        return stringbuilder.toString();

    }

    public static Client lineToClient(String line) {

        String[] words = line.split(CSV);
        int customerId = Integer.parseInt(words[0]);
        int userID    = Integer.parseInt(words[0]);
        String fullName = words[1];
        String emailAddress = words[2];
        LocalDate date = DataFormater.convertStringToLocaldate(words[3]);
        //int dateOfbirth = Integer.parseInt( words[3]);
        String location = words[4];
        int phoneNumber = Integer.parseInt(words[5]);

        Client client;
        client = new Client( customerId,  userID,  fullName,  location,  phoneNumber, emailAddress, date);

        return client;
    }

    public static RegisterMesage lineToMesage(String line) {
        RegisterMesage msg = new RegisterMesage();

        String[] words = line.split("&");
        LocalDate date = DataFormater.convertStringToLocaldate(words[0]);

        String text = words[1];
        msg.setDate(date);
        msg.setText(text);

        return msg;

    }

}
