package br.edu.ifsp.ddm.ifbook.dao;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifsp.ddm.ifbook.modelo.Mensagem;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class MensagemDAO extends DAO<Mensagem>{

	private SQLiteDatabase database;
	
	public MensagemDAO(Context context) {
		super(context);
		campos = new String[]{"idMensagem","Data","Titulo","Descricao","Imagem","AreaInteresse_idAreaInteresse","Usuario_idUsuario"};
		
		tableName = "mensagem";
		database = getWritableDatabase();	
	}
	
	
	public List<Mensagem> listAll() {
		List<Mensagem> list = new ArrayList<Mensagem>();
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
	
	public boolean atualizar(Mensagem mensagem) {
		ContentValues values = serializeContentValues(mensagem);
		if(database.update(tableName, values, "id = ?", new String[]{String.valueOf(mensagem.getIdMensagem())})>0)
			return true;
		else
			return false;
	}		
	
	public boolean deletar(Integer id) {
		if(database.delete(tableName, "id = ?", new String[]{String.valueOf(id)})>0)
			return true;
		else
			return false;
	}
	
	
	
	private Mensagem serializeByCursor(Cursor cursor)
	{
		Mensagem mensagem = new Mensagem();
		mensagem.setIdMensagem(cursor.getInt(0));
		//mensagem.setData(cursor.get???(1));
		mensagem.setTitulo(cursor.getString(2));
		mensagem.setDescricao(cursor.getString(3));		
		
		return mensagem;
		
	}
	
	private ContentValues serializeContentValues(Mensagem mensagem)
	{
		ContentValues values = new ContentValues();
		values.put("IdMensagem", mensagem.getIdMensagem());
		values.put("Data", mensagem.getData().toString());
		values.put("Titulo", mensagem.getTitulo());
		values.put("Descricao", mensagem.getDescricao());
		//values.put("Imagem", mensagem.getImagem());
		
		return values;
	}	
	
	
	private Cursor executeSelect(String selection, String[] selectionArgs, String orderBy)
	{
		
		return database.query(tableName,campos, selection, selectionArgs, null, null, orderBy);		

	}
	
}
