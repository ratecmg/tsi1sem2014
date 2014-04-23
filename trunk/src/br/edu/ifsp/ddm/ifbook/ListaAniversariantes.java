package br.edu.ifsp.ddm.ifbook;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import br.edu.ifsp.ddm.ifbook.modelo.Usuario;




import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ListaAniversariantes extends BaseAdapter {

	private Context context;
	private List<Usuario> lista;

	public ListaAniversariantes(Context context, List<Usuario> lista) {
		this.context = context;
		this.lista = lista;
	}

	public int getCount() {
		return lista.size();
		
	}
	


	public Object getItem(int position) {
		return lista.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		Usuario p = lista.get(position);
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.aniversariantes, null);
		
		TextView id = (TextView) view.findViewById(R.id.txtidUsuario);
		id.setText("ID: "+String.valueOf(p.getIdUsuario()));
		
		
		TextView nome = (TextView) view.findViewById(R.id.txtNome);
		nome.setText(p.getNome());
		
		TextView nascimento = (TextView) view.findViewById(R.id.txtNascimento);
		nascimento.setText(p.getNascimento());
		
		
		return view;
	}
}