package br.ufac.si.academico.conversores;

import javax.el.*;
import javax.faces.component.*;
import javax.faces.context.*;
import javax.faces.convert.*;

import br.ufac.si.academico.controladores.CategoriaControlador;
import br.ufac.si.academico.controladores.ProdutoControlador;
import br.ufac.si.academico.entidades.Categoria;
import br.ufac.si.academico.entidades.Produto;

@FacesConverter(value="produtoConversor", forClass=Produto.class)
public class ProdutoConversor implements Converter {
	
	private ProdutoControlador pc;
	@Override
	public Object getAsObject(FacesContext context, 
			UIComponent component, String value) {
		if(value == null || value.isEmpty())
			return null;
		
		ELContext elContext = context.getELContext();
		ELResolver elResolver = elContext.getELResolver();
		
		pc = (ProdutoControlador)elResolver
				.getValue(elContext, null, 
						"produtoControlador");
		System.out.println("Valor: "+value);
		return pc.recuperar(Integer.valueOf(value));
	}
	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if(value == null || !(value instanceof Produto))
			return "";
						
		return String.valueOf(((Produto)value).getId());

	}

}
