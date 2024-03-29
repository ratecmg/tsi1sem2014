/*package br.edu.ifsp.ddm.ifbook.dao;


import java.util.ArrayList;
import java.util.List;
import br.edu.ifsp.ddm.ifbook.modelo.Classificado;
import br.edu.ifsp.ddm.ifbook.modelo.Mensagem;
import br.edu.ifsp.ddm.ifbook.rest.MensagemREST;

public class MensagemDAO {

	private MensagemREST rest = new MensagemREST("mensagem");

	public Mensagem getByID(Integer id) {
		System.out.println("DAO ID: "+id);	
		Mensagem mensagem = null;
		try {
			mensagem = rest.getMensagem(id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return mensagem;
	}

	public List<Mensagem> listAll() {
		List<Mensagem> list = new ArrayList<Mensagem>();
		try {
			list = rest.getListaMensagens();
		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}
	
	public boolean atualizar(Mensagem mensagem) {
		try {
			if (rest.atualizar(mensagem).equalsIgnoreCase(
					"Mensagem atualizada!"))
				return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}
	
	public boolean deletar(Integer id) {
		try {
			if (rest.deletar(id).equalsIgnoreCase("MENSAGEM REMOVIDA!"))
				return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}
	
	
	
	public List<Mensagem> listAll2(int id) {
		List<Mensagem> list = new ArrayList<Mensagem>();
		try {
			list = rest.getListaMinhasMenssagens(id);
		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}

	public boolean salvar(Mensagem mensagem) {

		try {
			if (rest.inserirMensagem(mensagem).equalsIgnoreCase("MENSAGEM INSERIDA!"))
				return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}
	
	
}
*/
	


package br.edu.ifsp.ddm.ifbook.dao;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifsp.ddm.ifbook.modelo.Classificado;
import br.edu.ifsp.ddm.ifbook.modelo.Mensagem;
import br.edu.ifsp.ddm.ifbook.modelo.Usuario;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class MensagemDAO extends DAO<Mensagem> {

	private SQLiteDatabase database;

	public MensagemDAO(Context context) {
		super(context);
		this.context = context;
		campos = new String[] { "idMensagem", "Titulo", "Descricao", "Data",
				"Imagem", "AreaInteresse_idAreaInteresse", "Usuario_idUsuario" };

		tableName = "mensagem";
		database = getWritableDatabase();
	}

	public List<Mensagem> listAll() {
		List<Mensagem> list = new ArrayList<Mensagem>();
		Cursor cursor = executeSelect(null, null, "1");

		if (cursor != null && cursor.moveToFirst()) {
			do {
				list.add(serializeByCursor(cursor));
			} while (cursor.moveToNext());

		}

		if (!cursor.isClosed()) {
			cursor.close();
		}

		return list;

	}

	public List<Mensagem> listAll(int id) {
		List<Mensagem> list = new ArrayList<Mensagem>();

		Cursor cursor = executeSelect("Usuario_idUsuario = ?", new String[]{String.valueOf(id)}, null);

		if (cursor != null && cursor.moveToFirst()) {
			do {
				list.add(serializeByCursor(cursor));
			} while (cursor.moveToNext());

		}

		if (!cursor.isClosed()) {
			cursor.close();
		}

		return list;

	}

	public boolean inserir(Mensagem mensagem) {
		ContentValues values = serializeContentValues(mensagem);
		if (database.insert(tableName, null, values) > 0)
			return true;
		else
			return false;
	}
	
	public boolean atualizar(Mensagem mensagem) {
		ContentValues values = serializeContentValues(mensagem);
		if (database.update(tableName, values, "idMensagem = ?",
				new String[] { String.valueOf(mensagem.getIdMensagem()) }) > 0)
			return true;
		else
			return false;
	}

	public boolean deletar(Integer id) {
		System.out.println("ID MENSSAGEM: "+ id);
		if (database.delete(tableName, "idMensagem = ?",
				new String[] { String.valueOf(id) }) > 0) {
			fecharConexao();
			return true;
			
		} else {
			fecharConexao();
			return false;
		}
	}

	private Mensagem serializeByCursor(Cursor cursor) {
		Mensagem mensagem = new Mensagem();
		mensagem.setIdMensagem(cursor.getInt(0));
		mensagem.setTitulo(cursor.getString(1));
		mensagem.setDescricao(cursor.getString(2));
		mensagem.setData(cursor.getString(3));
		mensagem.setImagem(cursor.getBlob(4));
		AreaInteresseDAO areainteresseDAO = new AreaInteresseDAO(this.context);

		mensagem.setAreaInteresse(areainteresseDAO
				.getByID(cursor.getInt(5)));
		UsuarioDAO usuarioDAO = new UsuarioDAO(this.context);
		mensagem.setUsuario(usuarioDAO.getById((cursor.getInt(6))));

		return mensagem;

	}
	
	
	public List<Mensagem> listAll2() {
		List<Mensagem> list = new ArrayList<Mensagem>();
		String sql = "SELECT idMensagem, Titulo, Descricao, strftime('%d/%m/%Y %H:%M:%S', Data), Imagem, AreaInteresse_idAreaInteresse, Usuario_idUsuario FROM mensagem ORDER BY idMensagem DESC;";
		
		Cursor cursor = getReadableDatabase().rawQuery(sql, null);
		if(cursor!=null && cursor.moveToFirst())
		{
			do{
				
				
				list.add(serializeByCursor(cursor));
			}while(cursor.moveToNext());
			
			
		}
		
		fecharConexao(cursor);
		
		return list;
		
		
	}
	
	public Mensagem getById (int id){
		Mensagem mensagem = new Mensagem();
			
		Cursor cursor = executeSelect("idMensagem = ?", new String[]{String.valueOf(id)}, null);
		
		if(cursor!=null && cursor.moveToFirst())
		{
			mensagem = serializeByCursor(cursor);
		}
		fecharConexao(cursor);
		
		return mensagem;
	}

	private ContentValues serializeContentValues(Mensagem mensagem) {
		ContentValues values = new ContentValues();
		
		values.put("idMensagem", mensagem.getIdMensagem());
		values.put("Titulo", mensagem.getTitulo());
		values.put("Descricao", mensagem.getDescricao());
		values.put("Data", mensagem.getData());
		values.put("Imagem", mensagem.getImagem());
		values.put("AreaInteresse_idAreaInteresse", mensagem.getAreaInteresse().getIdAreaInteresse());
		values.put("Usuario_idUsuario", mensagem.getUsuario().getIdUsuario());

		return values;
	}

	private Cursor executeSelect(String selection, String[] selectionArgs,
			String orderBy) {

		return database.query(tableName, campos, selection, selectionArgs,
				null, null, orderBy);

	}

}
