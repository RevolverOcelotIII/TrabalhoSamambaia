package com.example.trabalho_samambaia.api;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.trabalho_samambaia.model.Planta;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ApiConnection {
    public int EnviarPlantaApi(Planta planta) {
        final int[] planta_id = new int[1];
        planta_id[0] = 0;
        Thread thread = new Thread(()->{

                try {
                    // Cria o objeto OkHttpClient
                    OkHttpClient client = new OkHttpClient();

                    // Define o corpo da requisição como JSON
                    MediaType JSON = MediaType.parse("application/json; charset=utf-8");
                    JSONObject requestBody = new JSONObject();
                    requestBody.put("nome_comum", planta.getNome_comum());
                    requestBody.put("nome_personalizado", planta.getNome_personalizado());
                    requestBody.put("nome_cientifico", planta.getNome_cientifico());
                    requestBody.put("proxima_adubagem", planta.getProxima_adubagem());
                    requestBody.put("ciclo", planta.getCiclo());
                    requestBody.put("regagem", planta.getRegagem());
                    requestBody.put("banho_sol", planta.getBanho_sol());
                    requestBody.put("planta_base_id", planta.getPlanta_base_id());
                    requestBody.put("imagem_url", planta.getImagem_url());

                    //String requestBody = "{\"nome_comum\":\"" + planta.getNome_comum() + "\", \"nome_personalizado\":\"" + planta.getNome_personalizado() + "\", \"nome_cientifico\":\"" + planta.getNome_cientifico() + "\", \"proxima_adubagem\":\"" + planta.getProxima_adubagem() + "\", \"ciclo\":\"" + planta.getCiclo() + "\", \"regagem\":\"" + planta.getRegagem() + "\", \"banho_sol\":\"" + planta.getBanho_sol() + "\", \"planta_base_id\":" + planta.getPlanta_base_id() + ", \"imagem_url\":\"" + planta.getImagem_url() + "\"}";
                    RequestBody body = RequestBody.create(JSON, requestBody.toString());

                    // Cria o objeto Request com a URL da API e o corpo da requisição
                    Request request = new Request.Builder()
                            .url("http://192.168.0.231:3001/adicionarPlanta")
                            .post(body)
                            .build();

                    // Envia a requisição e obtém a resposta
                    Response response = client.newCall(request).execute();
                    String api_response = response.body().string();
                    Gson gson = new Gson();
                    planta_id[0] = gson.fromJson(api_response, JsonObject.class).get("id").getAsInt();
                    Log.d("api", "id: "+planta_id[0]);

                    // Obtém o código de resposta da requisição
                    int statusCode = response.code();

                    if (statusCode == 200) {
                        // A requisição foi bem-sucedida
                        // Faça o que for necessário com a resposta
                    } else {
                        Log.d("api", "EnviarPlantaApi: falhou");
                    }
                } catch (IOException e) {
                    Log.e("api", "EnviarPlantaApi: " + e.getMessage(), e);
                } catch (JSONException e) {
                    //throw new RuntimeException(e);
                }
        });
        /*
        new Thread(new Runnable() {
            @Override
            public void run() {
            }
        }).start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }*/
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return planta_id[0];
    }

    public List<Planta> RecuperarPlantasApi() {
        List<Planta> plantas = new ArrayList<>();
        Thread thread = new Thread(() ->{
            try {
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder().url("http://192.168.0.231:3001/getPlantas").get().build();
                Response response = client.newCall(request).execute();
                String json_response = response.body().string();
                Log.d("api", json_response);
                Gson gson = new Gson();
                JsonArray jsonArray = JsonParser.parseString(json_response).getAsJsonArray();
                for (JsonElement element : jsonArray) {
                    JsonObject plantaJson = element.getAsJsonObject();
                    Planta planta = new Planta();
                    planta.setId(plantaJson.get("id").getAsInt());
                    planta.setNome_comum(plantaJson.get("nome_comum").getAsString());
                    planta.setNome_personalizado(plantaJson.get("nome_personalizado").getAsString());
                    planta.setNome_cientifico(plantaJson.get("nome_cientifico").getAsString());
                    planta.setProxima_adubagem(plantaJson.get("proxima_adubagem").getAsString());
                    planta.setCiclo(plantaJson.get("ciclo").getAsString());
                    planta.setRegagem(plantaJson.get("regagem").getAsString());
                    planta.setBanho_sol(plantaJson.get("banho_sol").getAsString());
                    planta.setPlanta_base_id(plantaJson.get("planta_base_id").getAsInt());
                    planta.setImagem_url(plantaJson.get("imagem_url").getAsString());
                    plantas.add(planta);
                }
            } catch (SocketTimeoutException e){
                Log.e("api", "Erro no parsing do JSON: " + e.getMessage(), e);
            } catch (Exception e) {
                Log.e("api", "EnviarPlantaApi1212: " + e.getMessage(), e);
            }
        });
        /*
        new Thread(new Runnable() {
            @Override
            public void run() {

            }
        }).start();*/
        thread.start();
        /*
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }*/
        try {
            thread.join();
        } catch (Exception e) {
            Log.e("api", "RecuperarPlantasApi: ", e);
        }
        return plantas;
    }
}
