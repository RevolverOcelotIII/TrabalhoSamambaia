package com.example.trabalho_samambaia.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.trabalho_samambaia.api.ApiConnection;
import com.example.trabalho_samambaia.model.Planta;

import java.util.ArrayList;
import java.util.List;

public class PlantaDAO {
    private SQLiteDatabase write;
    private SQLiteDatabase read;
    private Context context;
    //private Connection connection;


    public PlantaDAO(Context context) {
        ConnectionFactory connectionFactory = new ConnectionFactory(context);
        this.context = context;
        write = connectionFactory.getWritableDatabase();
        read = connectionFactory.getReadableDatabase();

    }

    public int createPlanta(Planta planta, int id_externo) {
        try {
            ContentValues values = new ContentValues();
            if(id_externo==0){
                ApiConnection apiConnection = new ApiConnection();
                //PARA QUE O APLICATIVO FUNCIONE OFFLINE, COMENTE ESSAS DUAS LINHAS (1/2)
                //int planta_id = apiConnection.EnviarPlantaApi(planta);
                //if(planta_id != 0) values.put("id", planta_id);

            } else values.put("id", id_externo);

            values.put("nome_comum", planta.getNome_comum());
            values.put("nome_cientifico", planta.getNome_cientifico());
            values.put("nome_personalizado", planta.getNome_personalizado());
            values.put("ciclo", planta.getCiclo());
            values.put("regagem", planta.getRegagem());
            values.put("proxima_adubagem", planta.getProxima_adubagem());
            values.put("banho_sol", planta.getBanho_sol());
            //values.put("user_id", planta.getUsuario().getId());
            values.put("planta_base_id", planta.getPlanta_base_id());
            values.put("imagem_url", planta.getImagem_url());
            int created_planta_id = (int) write.insert(ConnectionFactory.TABELA_PLANTA, null, values);
            Log.d("database", "createPlanta: " + getPlantaFromId(created_planta_id).toString());
            return (int) created_planta_id;
        } catch (Exception e) {
            Log.d("database", "createPlanta: " + e.getMessage());
        }
        return -1;
    }

    public int editPlanta(Planta planta) {
        try {
            ContentValues values = new ContentValues();
            values.put("nome_comum", planta.getNome_comum());
            values.put("nome_cientifico", planta.getNome_cientifico());
            values.put("nome_personalizado", planta.getNome_personalizado());
            values.put("ciclo", planta.getCiclo());
            values.put("regagem", planta.getRegagem());
            values.put("proxima_adubagem", planta.getProxima_adubagem());
            values.put("banho_sol", planta.getBanho_sol());
            //values.put("user_id", planta.getUsuario().getId());
            values.put("planta_base_id", planta.getPlanta_base_id());
            values.put("imagem_url", planta.getImagem_url());
            String[] args = {String.valueOf(planta.getId())};
            int created_planta_id = (int) write.update(ConnectionFactory.TABELA_PLANTA, values, "id = ?", args);
            Log.d("database", "editPlanta: " + getPlantaFromId(created_planta_id).toString());
            return (int) created_planta_id;

        } catch (Exception e) {
            Log.d("database", "createPlanta: " + e.getMessage());
        }
        return -1;
    }

    //Não sei pra q isso serve
    @SuppressLint("Range")
    public Planta getPlantaFromId(int planta_id) {
        Planta planta = new Planta();
        Cursor cursor = read.rawQuery("SELECT * FROM " + ConnectionFactory.TABELA_PLANTA + " WHERE id = " + planta_id + ";", null);
        Log.d("api", "getPlantaFromId --: " + planta_id);
        if(cursor.getCount()>0){
            cursor.moveToNext();
            planta.setId(cursor.getInt(cursor.getColumnIndex("id")));
            planta.setNome_comum(cursor.getString(cursor.getColumnIndex("nome_comum")));
            planta.setNome_cientifico(cursor.getString(cursor.getColumnIndex("nome_cientifico")));
            planta.setNome_personalizado(cursor.getString(cursor.getColumnIndex("nome_personalizado")));
            planta.setRegagem(cursor.getString(cursor.getColumnIndex("regagem")));
            planta.setBanho_sol(cursor.getString(cursor.getColumnIndex("banho_sol")));
            planta.setCiclo(cursor.getString(cursor.getColumnIndex("ciclo")));
            planta.setImagem_url(cursor.getString(cursor.getColumnIndex("imagem_url")));
            planta.setPlanta_base_id(cursor.getInt(cursor.getColumnIndex("planta_base_id")));
        }
        return planta;
    }

    @SuppressLint("Range")
    public List<Planta> getPlantasFromUser(int user_id) {
        List<Planta> plantas = new ArrayList<>();
        Cursor cursor = read.rawQuery("SELECT * FROM " + ConnectionFactory.TABELA_PLANTA + " WHERE user_id = " + user_id + ";", null);
        while (cursor.moveToNext()) {
            Planta planta = new Planta();
            planta.setId(cursor.getInt(cursor.getColumnIndex("id")));
            planta.setNome_comum(cursor.getString(cursor.getColumnIndex("nome_comum")));
            planta.setNome_cientifico(cursor.getString(cursor.getColumnIndex("nome_cientifico")));
            planta.setNome_personalizado(cursor.getString(cursor.getColumnIndex("nome_personalizado")));
            planta.setRegagem(cursor.getString(cursor.getColumnIndex("regagem")));
            planta.setBanho_sol(cursor.getString(cursor.getColumnIndex("banho_sol")));
            planta.setCiclo(cursor.getString(cursor.getColumnIndex("ciclo")));
            planta.setImagem_url(cursor.getString(cursor.getColumnIndex("imagem_url")));
            planta.setPlanta_base_id(cursor.getInt(cursor.getColumnIndex("planta_base_id")));
            plantas.add(planta);
        }
        return plantas;
    }

    @SuppressLint("Range")
    public List<Planta> getAllPlantas() {
        //PARA QUE O APLICATIVO FUNCIONE SEM A API, COMENTE ESSA LINHA (2/2)
        //atualizarListaPlantas();

        Log.d("api", "rodow: ");
        List<Planta> plantas = new ArrayList<>();
        Cursor cursor = read.rawQuery("SELECT * FROM " + ConnectionFactory.TABELA_PLANTA + ";", null);
        while (cursor.moveToNext()) {
            Planta planta = new Planta();
            planta.setId(cursor.getInt(cursor.getColumnIndex("id")));
            planta.setNome_comum(cursor.getString(cursor.getColumnIndex("nome_comum")));
            planta.setNome_cientifico(cursor.getString(cursor.getColumnIndex("nome_cientifico")));
            planta.setNome_personalizado(cursor.getString(cursor.getColumnIndex("nome_personalizado")));
            planta.setRegagem(cursor.getString(cursor.getColumnIndex("regagem")));
            planta.setBanho_sol(cursor.getString(cursor.getColumnIndex("banho_sol")));
            planta.setCiclo(cursor.getString(cursor.getColumnIndex("ciclo")));
            planta.setImagem_url(cursor.getString(cursor.getColumnIndex("imagem_url")));
            planta.setPlanta_base_id(cursor.getInt(cursor.getColumnIndex("planta_base_id")));
            Log.d("api", "id é: "+cursor.getInt(cursor.getColumnIndex("id")));
            plantas.add(planta);
        }
        return plantas;
    }

    public void atualizarListaPlantas(){
        try {
            ApiConnection apiConnection = new ApiConnection();
            List<Planta> plantas = apiConnection.RecuperarPlantasApi();
            if(plantas.size()>0){
                for (Planta p : plantas){
                    Log.d("api", "atualizarListaPlantas: "+ p.getNome_personalizado());
                    if(getPlantaFromId(p.getId()).getId()==0){
                        createPlanta(p, p.getId());
                    }
                }
            }
        } catch (Exception e) {
            //throw new RuntimeException(e);
            Log.e("api", "atualizarListaPlantas: "+e.getMessage(),e);
        }
    }

}
