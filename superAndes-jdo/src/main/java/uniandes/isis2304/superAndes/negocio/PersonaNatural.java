package uniandes.isis2304.superAndes.negocio;
/**
 * 
 * @author lj.romero
 *
 */
public class PersonaNatural extends Cliente implements VOPersonaNatural{
	private String documento;

	public PersonaNatural()
	{
		documento="";
	}
	public PersonaNatural(String pDocumento)
	{
		documento = pDocumento;
	}

	public String getDocumento() {
		return documento;
	}
	public void setDocumento(String pDocumento)
	{
		this.documento = pDocumento;
	}
	@Override
	public String toString() {
		return "PersonaNatural [documento=" + documento + "]";
	}


}
