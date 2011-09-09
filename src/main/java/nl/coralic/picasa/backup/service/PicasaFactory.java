package nl.coralic.picasa.backup.service;

import com.google.gdata.client.photos.PicasawebService;

public class PicasaFactory
{

	public static Picasa createPicasa()
	{
		Picasa picasa = new Picasa();
		picasa.setPicasaService(new PicasawebService(Picasa.class.getName()));
		return picasa;
	}

}
