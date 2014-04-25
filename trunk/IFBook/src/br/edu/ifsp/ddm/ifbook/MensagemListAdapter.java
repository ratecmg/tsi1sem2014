package br.edu.ifsp.ddm.ifbook;

import java.util.List;

import br.edu.ifsp.ddm.ifbook.modelo.Mensagem;
import android.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MensagemListAdapter extends BaseAdapter {

	private Context context;
	private List<Mensagem> lista;

	public MensagemListAdapter(Context context, List<Mensagem> lista) {
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
		Mensagem p = lista.get(position);
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.activity_list_item, null);
		
		
		//TextView id = (TextView) view.findViewById(R.id.txtIdMensagem);
		//id.setText("ID: "+String.valueOf(p.getIdMensagem()));
		
		
		//TextView titulo = (TextView) view.findViewById(R.id.txtTituloMensagem);
		//titulo.setText("Título: "+p.getTitulo());
		
		//TextView descricao = (TextView) view.findViewById(R.id.txtDescricaoMensagem);
		//descricao.setText("Descrição: "+p.getDescricao());
		
		
		return view;
	}
}
