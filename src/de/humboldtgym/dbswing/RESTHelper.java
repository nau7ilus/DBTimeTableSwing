package de.humboldtgym.dbswing;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.CompletableFuture;

public class RESTHelper {
    public static CompletableFuture<String> get(String endpoint) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                URL url = new URL(endpoint);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setRequestProperty("Accept", "application/json");

                int responseCode = connection.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                        StringBuilder response = new StringBuilder();
                        String line;
                        while ((line = reader.readLine()) != null) {
                            response.append(line);
                        }
                        return response.toString();
                    }
                } else {
                    throw new RuntimeException("API Error: " + responseCode);
                }
            } catch (Exception e) {
                throw new RuntimeException("Request failed", e);
            }
        });
    }

    public static String encodeQuery(String query) {
        try {
            //noinspection CharsetObjectCanBeUsed
            return URLEncoder.encode(query, StandardCharsets.UTF_8.toString());
        } catch (Exception e) {
            throw new RuntimeException("Failed to encode query", e);
        }
    }
}
