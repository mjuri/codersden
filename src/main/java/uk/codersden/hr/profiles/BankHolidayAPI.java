package uk.codersden.hr.profiles;

import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class BankHolidayAPI {

    public static List<LocalDate> fetchBankHolidays() {
        String apiUrl = "https://www.gov.uk/bank-holidays.json";
        List<LocalDate> bankHolidays = new ArrayList<>();

        try {
            // Set up connection
            URL url = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            // Read response
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            // Parse JSON response
            JSONObject json = new JSONObject(response.toString());
            JSONArray events = json.getJSONObject("england-and-wales").getJSONArray("events");

            for (int i = 0; i < events.length(); i++) {
                String date = events.getJSONObject(i).getString("date");
                bankHolidays.add(LocalDate.parse(date)); // Convert to LocalDate
            }

        } catch (Exception e) {
            System.err.println("Error fetching bank holidays: " + e.getMessage());
        }

        return bankHolidays;
    }

}

