package com.example.trabalho_samambaia.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class ConnectionFactory extends SQLiteOpenHelper {
    private static final String DATABASE = "samambaia";
    public static final String TABELA_USER = "usuario";
    public static final String TABELA_PLANTA = "planta";
    private static final int VERSAO = 1;

    public ConnectionFactory(Context context) {
        super(context, DATABASE, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        try{
            sqLiteDatabase.execSQL("create table if not exists "+TABELA_USER+"(\n" +
                    "\tid integer primary key autoincrement,\n" +
                    "    nome varchar(100),\n" +
                    "    email varchar(100),\n" +
                    "    senha varchar(100));");
            sqLiteDatabase.execSQL(
                    "create table if not exists "+TABELA_PLANTA+"(\n" +
                            "\tid integer primary key autoincrement,\n" +
                            "    nome_comum varchar(100),\n" +
                            "    nome_personalizado varchar(100),\n" +
                            "    nome_cientifico varchar(100),\n" +
                            "    ciclo varchar(100),\n" +
                            "    regagem varchar(100),\n" +
                            "    banho_sol varchar(100),\n" +
                            "    user_id int,\n" +
                            "    planta_base_id int,\n" +
                            "    imagem_url varchar(500),\n" +
                            "    foreign key (user_id) references "+TABELA_USER+"(id));");
            Log.w("Teste","Todas as tabelas foram criadas com sucesso");
        }catch(Exception e){

        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String ddl ="DROP TABLE IF EXISTS Disciplina";
        sqLiteDatabase.execSQL(ddl);
        onCreate(sqLiteDatabase);
    }
}
