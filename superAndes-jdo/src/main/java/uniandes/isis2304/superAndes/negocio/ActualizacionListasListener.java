package uniandes.isis2304.superAndes.negocio;

import java.util.List;

public interface ActualizacionListasListener {

	/**
	 * Método que actualiza la lista con la nueva lista que entra como parámetro.
	 * @param lista Nueva lista a colocar en la interfaz gráfica.
	 */
	public void actualizarLista(List<Object[]>[] lista);
}
