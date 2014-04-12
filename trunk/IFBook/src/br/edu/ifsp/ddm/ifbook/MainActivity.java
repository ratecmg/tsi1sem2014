package br.edu.ifsp.ddm.ifbook;

import com.example.ifbook.R;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class MainActivity extends Activity {
	
	
	private Mensagem p;
	private List<Mensagem> mensagem;
	private MensagemDAO dao;
	private EditText edID;
	private EditText edTITULO;
	private EditText edDESCRICAO;
	private EditText edDATA;
	private Spinner spCATEGORIA;
	private EditText edIDUSUARIO;
	private String operacao;
	private String[] categoria = { "a" , "b" };
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.perfil_login);
		
		setContentView(R.layout.main);
		edID = (EditText) findViewById(R.id.edID);
		edTITULO = (EditText) findViewById(R.id.edTITULO);
		edDESCRICAO = (EditText) findViewById(R.id.edDESCRICAO);
		edDATA = (EditText) findViewById(R.id.edDATA);
		spCATEGORIA = (Spinner) findViewById(R.id.spCATEGORIA);
		edIDUSUARIO = (EditText) findViewById(R.id.edIDUSUARIO);
		mensagem = new ArrayList<Mensagem>;
		operacao = new String ("Novo");
		dao = new MensagemDAO(getApplicationContext());
		preencherCategoria();
		atualizarLista();
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	private void preencherCategoria(){
		try{
			ArrayAdapter<String> adapter =
					new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, categoria);
			adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			spCATEGORIA.setAdapter(adapter);
		} catch (Exception ex){
			exibirMensagem("Erro:" + ex.getMessage());
		}
		
	}
	
	public void enviar(View v){
		
		if (operacao.equalsIgnoreCase("Novo")) {
			p = new Mensagem();
	
		}
		
		p.setTitulo(edTITULO.getText().toString());
		p.setDescricao(edDESCRICAO.getText().toString());
		p.setCategoria(categoria[spCATEGORIA.getSelectedItemPosition()]
				.equalsIgnoreCase("a") ? "a" : "b");
		
		p.setData(edDATA.getText().toString());
		p.setIdUsuario(edIDUSUARIO.getText().toString());
		limparDados();
		
	}
	
	public void novo(View v) {
		operacao=new String("Novo");
		limparDados();
	}
	
	private void limparDados() {
		edID.setText("");
		edTITULO.requestFocus();
		edDESCRICAO.setText("");
		edDATA.setText("");
		spCATEGORIA.setSelection(0);
		edIDUSUARIO.setText("");
		
	}
	
	private void preencherDados(Mensagem mensagem) {
		edID.setText(String.valueOf(Mensagem.getId()));
		edTITULO.setText(mensagem.getTitulo);
		edDESCRICAO.setText(mensagem.getDescricao);
		edDATA.setText(String.valueOf(Mensagem.getId()));
		spCATEGORIA.setSelection(mensagem.getCategoria().equalsIgnoreCas("a") ? 0 : 1);
		edIDUSUARIO.setText(String.valueOf(Mensagem.getIdUsuario()));
	}
	
}
