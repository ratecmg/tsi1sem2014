package br.edu.ifsp.ifbook.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DAO <T extends Object> extends SQLiteOpenHelper {

	private static final String DATABASE_NAME = "ifbook.sqlite";
	private static final int DATABASE_VERSION = 1;
	public static final String TABLE_ESTADOCIVIL = "estadocivil";
	public static final String TABLE_USUARIO = "usuario";
	public static final String TABLE_MENSAGEM = "mensagem";
	public static final String TABLE_CLASSIFICADO = "classificado";
	public static final String TABLE_AREAINTERESSE = "areainteresse";
	
	protected Context context;
	protected String[] campos;
	protected String tableName;
	
	
	private static final String CREATE_TABLE_ESTADOCIVIL = "CREATE TABLE estadocivil ( "
	+ "idEstadocivil INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL, "
	+ "EstadoCivil VARCHAR(20) NOT NULL );";
	
	
	
	
	
	
	
	
	

	public DAO(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	
	@Override
	public void onCreate(SQLiteDatabase database) {
		database.execSQL(CREATE_TABLE_PESSOA);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL(" DROP TABLE IF EXISTS " + TABLE_PESSOA);
		
		onCreate(db);
	}
	
	
	
	
	
	
	protected void closeDatabase(SQLiteDatabase db)
	{		
		if(db.isOpen())
			db.close();		
	}

}	

