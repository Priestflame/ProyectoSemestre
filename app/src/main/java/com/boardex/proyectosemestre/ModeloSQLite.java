package com.boardex.proyectosemestre;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.boardex.proyectosemestre.SQLiteDB.ConexionSQLite;

public class ModeloSQLite {

    public SQLiteDatabase getConn(Context context){
        ConexionSQLite conn = new ConexionSQLite(context, "juegos", null, 1);
        SQLiteDatabase db = conn.getWritableDatabase();
        return db;
    }

    public int insertaJuego(Context context, JuegosDTO dto){
        int res = 0;

        String sql ="INSERT INTO juegos(id, nombre, categoria) VALUES ('"+ dto.getId()+"','"+ dto.getNombre()+"','"+ dto.getCategoria()+"',)";
        SQLiteDatabase db = this.getConn(context);

        try{
            db.execSQL(sql);
            res = 1;
        }catch (Exception e){

        }

        return res;
    }
}
