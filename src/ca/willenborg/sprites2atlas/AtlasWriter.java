package ca.willenborg.sprites2atlas;

import java.io.PrintWriter;

public class AtlasWriter {

	public static void write( SpriteSheet spriteSheet, String fileName ) throws Exception {
		String output = header( spriteSheet );
		for( int i = 0; i < spriteSheet.getSpriteCount(); i++ ) {
			output += spriteEntry( spriteSheet.getSprite( i ) );
		}
		stringToFile( output, fileName );
	}
	
	private static String header( SpriteSheet spriteSheet ) {
		return String.format( "%s%n"
				+ "size: %d, %d%n"
				+ "format: RGBA8888%n"
				+ "filter: Nearest,Nearest%n"
				+ "repeat: none%n"
				, spriteSheet.getImageName()
				, spriteSheet.getWidth()
				, spriteSheet.getHeight() );
	}
	
	private static String spriteEntry( Sprite sprite ) {
		return String.format( "%s%n"
				+ "\trotation: false%n"
				+ "\txy: %d, %d%n"
				+ "\tsize: %d, %d%n"
				+ "\torig: %d, %d%n"
				+ "\toffset: 0, 0%n"
				+ "\tindex: -1%n"
				, sprite.getName()
				, sprite.getX()
				, sprite.getY()
				, sprite.getWidth()
				, sprite.getHeight()
				, sprite.getWidth()
				, sprite.getHeight());
	}
	
	private static void stringToFile( String output, String fileName) throws Exception {
		PrintWriter file = new PrintWriter(fileName, "UTF-8");
		file.print( output );
		file.close();
	}
}
