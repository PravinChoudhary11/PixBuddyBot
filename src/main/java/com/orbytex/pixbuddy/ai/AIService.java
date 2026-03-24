package com.orbytex.pixbuddy.ai;

import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
public class AIService {

    @Value("${spring.gemini.pixbuddy.apikey1}")
    private String apiKey1;

    @Value("${spring.gemini.pixbuddy.apikey2}")
    private String apiKey2;

    @Value("${spring.gemini.pixbuddy.apikey3}")
    private String apiKey3;

    private final OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(30, java.util.concurrent.TimeUnit.SECONDS)
            .writeTimeout(30, java.util.concurrent.TimeUnit.SECONDS)
            .readTimeout(60, java.util.concurrent.TimeUnit.SECONDS)
            .build();

    public String getResponse(String message) {

        List<String> apiKeys = Arrays.asList(apiKey1, apiKey2, apiKey3);

        for (String key : apiKeys) {
            try {
                String response = callGemini(message, key);

                if (response != null && !response.isEmpty()) {
                    return response;
                }

            } catch (Exception e) {
                System.out.println("reconnecting to the system");
            }
        }

        return "⚠️ All PixBuddy services are currently busy. Please try again later.";
    }

    private String callGemini(String message, String apiKey) throws IOException {

        JSONObject text = new JSONObject();
        text.put("text",
                "You are PixBuddy, an AI assistant created by Orbytex. " +
                        "Developer: Pravin Choudhary. " +
                        "Be friendly and helpful.\nUser: " + message
        );

        JSONArray parts = new JSONArray();
        parts.put(text);

        JSONObject content = new JSONObject();
        content.put("parts", parts);

        JSONArray contents = new JSONArray();
        contents.put(content);

        JSONObject requestJson = new JSONObject();
        requestJson.put("contents", contents);

        RequestBody body = RequestBody.create(
                requestJson.toString(),
                MediaType.parse("application/json")
        );

        Request request = new Request.Builder()
                .url("https://generativelanguage.googleapis.com/v1beta/models/gemini-3-flash-preview:generateContent?key=" + apiKey)
                .post(body)
                .addHeader("Content-Type", "application/json")
                .build();

        Response response = client.newCall(request).execute();

        String responseBody = response.body().string();

        System.out.println("Gemini Response: " + responseBody);

        JSONObject responseJson = new JSONObject(responseBody);

        if (!responseJson.has("candidates")) {
            throw new RuntimeException("Rate limit or error");
        }

        return responseJson
                .getJSONArray("candidates")
                .getJSONObject(0)
                .getJSONObject("content")
                .getJSONArray("parts")
                .getJSONObject(0)
                .getString("text");
    }
}