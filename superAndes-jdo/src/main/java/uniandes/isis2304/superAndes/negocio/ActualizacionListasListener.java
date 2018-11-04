package uniandes.isis2304.superAndes.negocio;

import java.util.List;

public interface ActualizacionListasListener {

	/**
	 * M�todo que actualiza la lista con la nueva lista que entra como par�metro.
	 * @param lista Nueva lista a colocar en la interfaz gr�fica.
	 */
	public void actualizarLista(List<Object[]>[] lista);
}
