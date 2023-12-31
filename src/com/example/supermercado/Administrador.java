package com.example.supermercado;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class Administrador extends SQLiteOpenHelper {
	
	public Administrador(Context context, String nombre, CursorFactory factory, int version)
	{
		super(context, nombre, factory, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) 
	{
		// TODO Auto-generated method stub
		
		String sql = "Create Table articulos(codigo integer primary key, nombre text, rubro text, precio float)";
		db.execSQL(sql);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}

}
