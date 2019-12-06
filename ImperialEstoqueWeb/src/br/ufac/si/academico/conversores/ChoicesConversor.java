package br.ufac.si.academico.conversores;

import javax.el.*;
import javax.faces.component.*;
import javax.faces.context.*;
import javax.faces.convert.*;

import br.ufac.si.academico.controladores.CategoriaControlador;
import br.ufac.si.academico.controladores.MovimentacaoControlador;
import br.ufac.si.academico.entidades.Categoria;
import br.ufac.si.academico.entidades.MovimentacaoEstoque;
import br.ufac.si.academico.entidades.enums.EstoqueChoices;

@FacesConverter(value="choicesConversor", forClass=EstoqueChoices.class)
public class ChoicesConversor implements Converter {
	
	private MovimentacaoControlador mc;
	@Override
	public Object getAsObject(FacesContext context, 
			UIComponent component, String value) {
		if(value == null || value.isEmpty())
			return null;
		
		ELContext elContext = context.getELContext();
		ELResolver elResolver = elContext.getELResolver();
		
		mc = (MovimentacaoControlador)elResolver
				.getValue(elContext, null, 
						"movimentacaoControlador");
		System.out.println("Object" + value);
		return mc.recuperarChoice(value);
	}
	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if(value == null || !(value instanceof EstoqueChoices))
			return "";
		System.out.println("String" + value);
		return String.valueOf(((EstoqueChoices)value).getDescricao());

	}

}
