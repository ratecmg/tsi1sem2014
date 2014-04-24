package br.edu.ifsp.ddm.ifbook;

import java.util.List;

import br.edu.ifsp.ddm.ifbook.dao.MensagemDAO;
import br.edu.ifsp.ddm.ifbook.modelo.Mensagem;


import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivityMensagem extends Activity {
	
	
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
	private Integer[] categoria = { 1 , 2 };
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.perfil_login);
		
		setContentView(R.layout.mensagem);
		edID = (EditText) findViewById(R.id.edID);
		edTITULO = (EditText) findViewById(R.id.edTITULO);
		edDESCRICAO = (EditText) findViewById(R.id.edDESCRICAO);
		edDATA = (EditText) findViewById(R.id.edDATA);
		spCATEGORIA = (Spinner) findViewById(R.id.spCATEGORIA);
		edIDUSUARIO = (EditText) findViewById(R.id.edIDUSUARIO);
		
		operacao = new String ("Novo");
		dao = new MensagemDAO(getApplicationContext());
		preencherCategoria();
		
		
		
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
					new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1);
			adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			spCATEGORIA.setAdapter(adapter);
		} catch (Exception ex){
			exibirMensagem("Erro:" + ex.getMessage());
		}
		
	}
	
	public void salvar(View v){
		
		if (operacao.equalsIgnoreCase("Novo")) {
			p = new Mensagem();
	
		}
		
		p.setTitulo(edTITULO.getText().toString());
		p.setDescricao(edDESCRICAO.getText().toString());
		//inserir imagem comofaz? perguntar luis
		p.setAreaInteresse_idAreaInteresse(categoria[spCATEGORIA.getSelectedItemPosition()]);
		// data e id do usuário serrá preenchido automticamente, como fazer isso?
		//p.setData(edDATA.getText().toString());
		//p.setUsuario_idUsuario(edIDUSUARIO.getText().toString());
		//limparDados();
		
	}
	
	public void novo(View v) {
		operacao=new String("Novo");
		//adicionar o caminho para visualizar msgs
		//limparDados();
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
		edID.setText(String.valueOf(mensagem.getIdMensagem()));
		edTITULO.setText(mensagem.getTitulo());
		edDESCRICAO.setText(mensagem.getDescricao());
		//edDATA.setText(String.valueOf(mensagem.getData()));
		spCATEGORIA.setSelection(mensagem.getAreaInteresse_idAreaInteresse());
		//edIDUSUARIO.setText(String.valueOf(Mensagem.getIdUsuario()));
	}
	
	
	
	// Excluir Mensagem 14/04/2014
	private void excluirMensagem(final int idMensagem) {
			
			
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle("Excluir a Mensagem?")
					.setIcon(android.R.drawable.ic_dialog_alert)
					.setMessage("Deseja excluir essa Mesagem?")
					.setCancelable(false)
					.setPositiveButton("Sim",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog, int id) {
									if (dao.deletar(idMensagem)) {
										//atualizarLista();
										exibirMensagem("Mensagem excluída com sucesso!");
									} else {
										exibirMensagem("Não foi possível excluir a Mensagem!");
									}
	
								}
							})
					.setNegativeButton("Não",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog, int id) {
									dialog.cancel();
								}
							});
			builder.create();
			builder.show();
	
		}
	
	private void exibirMensagem(String msg) {
		Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
	}
}

