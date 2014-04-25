package br.edu.ifsp.ddm.ifbook.dao;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import br.edu.ifsp.ddm.ifbook.modelo.Usuario;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.sql.Date;

public class UsuarioDAO extends DAO<Usuario>{
	
	
	private SQLiteDatabase database;
 
	
	public UsuarioDAO(Context context) {
		super(context);
		campos = new String[]{"idUsuario","Nome", "strftime('%m-%d', Nascimento)"};
		tableName = "usuario";
		database = getWritableDatabase();
		
		
	}
	


	
	private Usuario serializeByCursor(Cursor cursor)
	{
	    ;
		
		Usuario usuario = new Usuario();
		usuario.setIdUsuario(cursor.getInt(0));
		usuario.setNome(cursor.getString(1));
	    usuario.setNascimento(cursor.getString(2));
	
		return usuario;
		
	}
	

	private Cursor executeSelect(String selection, String[] selectionArgs, String orderBy)
	{
		

		return database.query(tableName,campos,selection, selectionArgs, null, null, orderBy);		

	}
	
	
	
	public List<Usuario> Aniversariantes() {
		List<Usuario> list = new ArrayList<Usuario>();
		String sql = "SELECT idUsuario,Nome,strftime('%m/%d', Nascimento) FROM usuario WHERE strftime('%m/%d', Nascimento) <= strftime('%m/%d',date('now','+7 day')) ORDER BY Nascimento;";
		
		Cursor cursor = getReadableDatabase().rawQuery(sql, null);
		if(cursor!=null && cursor.moveToFirst())
		{
			do{
				
				
				list.add(serializeByCursor(cursor));
			}while(cursor.moveToNext());
			
			
		}
		
		if(!cursor.isClosed())
		{
			cursor.close();
		}
		
		return list;
		
		
	}

	
	public List<Usuario> listAll() {
		List<Usuario> list = new ArrayList<Usuario>();
		Cursor cursor = executeSelect(null, null, "1");
		

		if(cursor!=null && cursor.moveToFirst())
		{
			do{
				
				
				list.add(serializeByCursor(cursor));
			}while(cursor.moveToNext());
			
			
		}
		
		if(!cursor.isClosed())
		{
			cursor.close();
		}
		
		return list;
		
		
	}
	

}
