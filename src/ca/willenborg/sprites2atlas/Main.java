package ca.willenborg.sprites2atlas;

public class Main {
	
	public static final String VERSION = "0.1";

	public static void main( String[] args ) {
		try {
			SpriteSheet sprites = SpritesReader.read( args[0] );
			AtlasWriter.write( sprites, args[1] );
		} catch ( Exception e ) {
			e.printStackTrace();
		}
	}
}
