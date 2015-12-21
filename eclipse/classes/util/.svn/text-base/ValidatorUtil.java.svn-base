package util;

import java.util.Collection;
import java.util.Map;

public class ValidatorUtil {
	
	/**
	 * Valida se string  é diferente de null e não é vazia.
	 *
	 * @return
	 */
	public static final boolean isEmpty(String s) {
		return (s == null || s.trim().length() == 0);
	}
	
	/**
	 * Retorna TRUE se TODOS os objetos informados estão vazios. Utiliza a implementação de {@link ValidatorUtil#isEmpty(Object)}
	 * 
	 * @param objs
	 * @see ValidatorUtil#isEmpty(Object)
	 * @return
	 */
	public static final boolean isAllEmpty(Object... objs) {
		
		if (objs == null)
			return true;
		
		for (Object o : objs) {
			if (isEmpty(o) == false)
				return false;
		}
		
		return true;
	}
	
	/**
	 * Valida se um objeto é vazio. O seu funcionamento vai depender do tipo de objeto
	 * passado como parâmetro. Se o objeto for nulo, é vazio. Se for uma tring, verifica
	 * se não é string vazia ou não é formada apenas por espaços. Se for uma coleção,
	 * verifica se a coleção está vazia, etc.
	 *
	 * @return
	 */
	public static final boolean isEmpty(Object o) {
		if (o == null)
			return true;
		if (o instanceof String)
			return isEmpty( (String) o);
		if (o instanceof Number) {
			Number i = (Number) o;
			return (i.intValue() == 0);
		}
		if (o instanceof Object[])
			return ((Object[]) o).length == 0;
		if (o instanceof int[])
			return ((int[]) o).length == 0;
		if (o instanceof Collection<?>)
			return ((Collection<?>) o).size() == 0;
		if (o instanceof Map<?, ?>)
			return ((Map<?, ?>) o).size() == 0;
		return false;
	}

}
