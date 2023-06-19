package com.example.trabalho_samambaia.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
<<<<<<< Updated upstream
import android.database.sqlite.SQLiteOpenHelper;
=======
import android.util.Log;
>>>>>>> Stashed changes

public class PlantaDAO extends SQLiteOpenHelper {
    private static final String DATABASE = "samambaia";
    private static final int VERSAO = 1;

    public PlantaDAO(Context context) {
        super(context, DATABASE, null, VERSAO);
    }

<<<<<<< Updated upstream
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE Planta (id INTEGER PRIMARY KEY,"
                + " nome TEXT UNIQUE NOT NULL);";


        sqLiteDatabase.execSQL(query);
=======
    public int createPlanta(Planta planta) {
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
            int created_planta_id = (int) write.insert(ConnectionFactory.TABELA_PLANTA, null, values);
            Log.d("database", "createPlanta: " + getPlantaFromId(created_planta_id).toString());
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
>>>>>>> Stashed changes
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
