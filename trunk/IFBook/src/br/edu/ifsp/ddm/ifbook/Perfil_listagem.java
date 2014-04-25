package br.edu.ifsp.ddm.ifbook;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import br.edu.ifsp.ddm.ifbook.dao.UsuarioDAO;
import br.edu.ifsp.ddm.ifbook.modelo.Usuario;


import android.os.Bundle;
import android.app.Activity;
import android.widget.ImageView;
import android.widget.ListView;


public class Perfil_listagem extends Activity {

	private List<Usuario> usuarios;
	private UsuarioDAO dao;
	private ListView lvUsuarios;
	private Usuario usuario;
	


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_perfil_listagem);

		lvUsuarios = (ListView) findViewById(R.id.ListaAniversariantes);
		usuarios = new ArrayList<Usuario>();
		dao = new UsuarioDAO(getApplicationContext());
		atualizarLista();
			}

	private void atualizarLista() {
		
		usuarios = dao.Aniversariantes();
		
		
		Calendar c = Calendar.getInstance();  
	    SimpleDateFormat f = new SimpleDateFormat("MM/dd");
	    String hoje = f.format( c.getTime() );
	    
	 
	    Date minhaData = new Date();  
	    Calendar calendar = Calendar.getInstance();  
	    calendar.setTime(minhaData);   
	    calendar.add(Calendar.DAY_OF_MONTH, 1);     
	    SimpleDateFormat dataFormatada = new SimpleDateFormat("MM/dd"); 	    
	    String amanha = dataFormatada.format(calendar.getTime());
	 

		
		for (Usuario obj : usuarios) {
				
			System.out.println ("id: "+obj.getIdUsuario());
		    System.out.println ("Nome: "+obj.getNome());  
			
			 if(obj.getNascimento().equalsIgnoreCase(hoje)){
			    	obj.setNascimento("Hoje");
			   System.out.println ("Nascimento: "+obj.getNascimento()); 
			    }
			 else if(obj.getNascimento().equalsIgnoreCase(amanha)){
				 
					obj.setNascimento("Amanha");
					   System.out.println ("Nascimento: "+obj.getNascimento()); 
				 
			 }
			 else{
		    System.out.println ("Nascimento: "+obj.getNascimento());
		    
			 }
		    
	
		    
		  
		}
	
	    
		
		if (usuarios != null) {
		
		
			if (usuarios.size() > 0) {
			
				ListaAniversariantes pla = new ListaAniversariantes(
						getApplicationContext(), usuarios);
				lvUsuarios.setAdapter(pla);
			}

		}

	}
	
}
