package br.ufac.si.academico.conversores;

import javax.el.*;
import javax.faces.component.*;
import javax.faces.context.*;
import javax.faces.convert.*;

import br.ufac.si.academico.controladores.CategoriaControlador;
import br.ufac.si.academico.entidades.Categoria;

@FacesConverter(value="categoriaConversor", forClass=Categoria.class)
public class CategoriaConversor implements Converter {
	
	private CategoriaControlador cc;
	@Override
	public Object getAsObject(FacesContext context, 
			UIComponent component, String value) {
		if(value == null || value.isEmpty())
			return null;
		
		ELContext elContext = context.getELContext();
		ELResolver elResolver = elContext.getELResolver();
		
		cc = (CategoriaControlador)elResolver
				.getValue(elContext, null, 
						"categoriaControlador");
		System.out.println("Valor: "+value);
		return cc.recuperar(Integer.valueOf(value));
	}
	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if(value == null || !(value instanceof Categoria))
			return "";
						
		return String.valueOf(((Categoria)value).getId());

	}

}
