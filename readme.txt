Pasos para instalar la aplicación
1. Descomprima el archivo >Proyecto Java<
2. Abra el proyecto en eclipse
3. Busque el archivo src/main/resource/META-INF/persistence.xml y abralo
	En la propiedad  “javax.jdo.option.ConnectionUserName” ponga como valor su usuario oracle.
	En la propiedad “javax.jdo.option.ConnectionPassword” ponga como valor su clave de oracle.	
4. Abra sqldeveloper y cree las tablas
	El script se encuentra en docs/EsquemaSuperAndes.sql
	Copie el contenido del archivo en una venta de sqldeveloper y ejecutelo com script
5. Pobe las tablas con información
	El script se encuetra en docs/InsercionSuperAndes.sql
	Copie el contenido del archivo en una venta de sqldeveloper y ejecutelo com script
6. Ejecute la interfazApp y pruebe las opciones del menú
7. Ejecute la interfazDemo y pruebe las opciones del menú