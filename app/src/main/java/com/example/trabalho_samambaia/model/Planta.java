package com.example.trabalho_samambaia.model;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Planta {
    private int id;
    private String nome;
    private String nome_cientifico;
    private String imagem_url;
<<<<<<< Updated upstream

    private String quantidade_agua;
=======
    private String nome_personalizado;
    private String ciclo;
    private String regagem;
    private String proxima_adubagem;
    private String banho_sol;
    private Usuario usuario;
    private int planta_base_id;
>>>>>>> Stashed changes

    public Planta(String nome, String nome_cientifico, String imagem_url) {
        this.nome = nome;
        this.nome_cientifico = nome_cientifico;
        this.imagem_url = imagem_url;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome_cientifico() {
        return nome_cientifico;
    }

    public void setNome_cientifico(String nome_cientifico) {
        this.nome_cientifico = nome_cientifico;
    }

    public String getImagem_url() {
        return imagem_url;
    }

    public void setImagem_url(String imagem_url) {
        this.imagem_url = imagem_url;
    }

    public String getQuantidade_agua() {
        return quantidade_agua;
    }

    public void setQuantidade_agua(String quantidade_agua) {
        this.quantidade_agua = quantidade_agua;
    }

    public String getProxima_adubagem() {
        return proxima_adubagem;
    }

    public void setProxima_adubagem(String proxima_adubagem) {
        this.proxima_adubagem = proxima_adubagem;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static List<Planta> gerarListaPlaceholder(Context context) {
        List<Planta> plantas = new ArrayList<Planta>();
        AssetManager assetManager = context.getAssets();


        try {
            InputStream inputStream = assetManager.open("species-list-traduzido.json");
            int json_tamanho = inputStream.available();
            byte[] buffer = new byte[json_tamanho];
            inputStream.read(buffer);
            inputStream.close();

            String json = new String(buffer, "UTF-8");

            Gson gson = new Gson();
            JsonArray jsonArray = gson.fromJson(json, JsonObject.class).getAsJsonArray("data");
            for (JsonElement element : jsonArray) {
                JsonObject planta_json = element.getAsJsonObject();
                Planta planta = new Planta(planta_json.get("common_name").getAsString(), planta_json.get("scientific_name").getAsJsonArray().get(0).getAsString(), planta_json.getAsJsonObject("default_image").get("small_url").getAsString());
                planta.setPlanta_base_id(planta_json.get("id").getAsInt());
                planta.setCiclo(planta_json.get("cycle").getAsString());
                planta.setRegagem(planta_json.get("watering").getAsString());
                planta.setBanho_sol(planta_json.get("sunlight").getAsJsonArray().toString());
                plantas.add(planta);
            }
            // String smallUrl = jsonObject.getAsJsonObject("default_image").get("small_url").getAsString();

        } catch (IOException e) {
            Log.d("testes", "deu ruim" + e.toString());
        }
        return plantas;
    }

    public static Planta getPlanta(int base_id, Context context) {
        Log.d("teste", "getRandomPlanta: " + base_id);
        return gerarListaPlaceholder(context).stream().filter(p -> p.getPlanta_base_id() == base_id).findFirst().orElse(null);
    }

    public static Planta getRandomPlanta(Context context) {
        try {
            List<Integer> base_ids = gerarListaPlaceholder(context).stream().map(Planta::getPlanta_base_id).collect(Collectors.toList());
            Random generator = new Random();
            Planta planta = getPlanta(base_ids.get(generator.nextInt(base_ids.size())), context);
            return planta;
        } catch (Exception e) {
            Log.d("teste", "getRandomPlanta: " + e.getMessage());
            return null;
        }
    }

    @Override
    public String toString() {
<<<<<<< Updated upstream
        return "Planta{" +
                "nome='" + nome + '\'' +
                ", nome_cientifico='" + nome_cientifico + '\'' +
                ", imagem_url='" + imagem_url + '\'' +
                '}';
=======
        return "Planta{" + "nome='" + nome_comum + '\'' + ", nome_cientifico='" + nome_cientifico + '\'' + ", imagem_url='" + imagem_url + '\'' + '}';
>>>>>>> Stashed changes
    }
}
