package ca.willenborg.sprites2atlas;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import ca.willenborg.sprites2atlas.Sprite.SpriteBuilder;
import ca.willenborg.sprites2atlas.SpriteSheet.SpriteSheetBuilder;

public class SpritesReader {
	
	public static SpriteSheet read( String filePath ) throws Exception {			
         File inputFile = new File( filePath );
         DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
         DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
         Document doc = dBuilder.parse( inputFile );
         doc.getDocumentElement().normalize();
         return buildSpriteSheet( doc );
	}
	
	private static SpriteSheet buildSpriteSheet( Document xmlDocument ) {		
		SpriteSheetBuilder ssb = new SpriteSheetBuilder();
		NamedNodeMap nam = xmlDocument.getDocumentElement().getAttributes();
		try {
			ssb.setImageName( nam.getNamedItem( "name" ).getNodeValue() );
			ssb.setSprites( parseNode( 
					xmlDocument.getElementsByTagName( "definitions" ).item( 0 ) ) );
			ssb.setWidth( Integer.parseInt( 
					nam.getNamedItem( "w" ).getNodeValue() ) );
			ssb.setHeight( Integer.parseInt( 
					nam.getNamedItem( "h" ).getNodeValue() ) );
			return (SpriteSheet) ssb.build();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit( 1 );
			return null; //never occurs
		}
	}
	
	private static List<Sprite> parseNode( Node directoryNode ) {
		return parseNode( directoryNode, "" );		
	}
	
	private static List<Sprite> parseNode( Node directoryNode, String prefix ) {
		List<Sprite> sprites = new ArrayList<Sprite>();
        NodeList childNodes = directoryNode.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
        	Node childNode = childNodes.item( i );
        	if(childNode.getNodeType() != Node.TEXT_NODE) {
	        	if( isDirectoryNode( childNode ) ) {
	        		sprites.addAll( parseNode( childNode, determinePrefix( prefix, childNode ) ) );
	        	} else {
	        		sprites.add( createSpriteFromElement( childNodes.item( i ), prefix ) );
	        	}
        	}
        }
        return sprites;
	}
	
	private static String determinePrefix( String prefix, Node node ) {
		String concatPrefix = node.getAttributes().getNamedItem( "name" ).getNodeValue();
		if ( concatPrefix.contentEquals( "/" ) ) {
			return "";
		} else {
			return (prefix.length() == 0) ? concatPrefix : prefix + "-" + concatPrefix;
		}
	}
	
	private static boolean isDirectoryNode( Node node ) {
		return node.getNodeName() == "dir";
	}
	
	private static Sprite createSpriteFromElement( Node spriteNode, String prefix ) {		
        	NamedNodeMap spriteAttributes = spriteNode.getAttributes();
        	SpriteBuilder spriteBuilder = new SpriteBuilder();
        	prefix = ( prefix.isEmpty() ) ? "" : prefix + "-";
        	
        	try {
        		spriteBuilder.setName( 
        			prefix + spriteAttributes.getNamedItem("name").getNodeValue());
	        	spriteBuilder.setX( 
	        			Integer.parseInt( spriteAttributes.getNamedItem( "x" ).getNodeValue() ));
	        	spriteBuilder.setY( 
	        			Integer.parseInt( spriteAttributes.getNamedItem( "y" ).getNodeValue() ));
	        	spriteBuilder.setHeight( 
	        			Integer.parseInt( spriteAttributes.getNamedItem( "h" ).getNodeValue() ));
	        	spriteBuilder.setWidth( 
	        			Integer.parseInt( spriteAttributes.getNamedItem( "w" ).getNodeValue() ));
				return (Sprite)spriteBuilder.build();
			} catch (Exception e) {
				e.printStackTrace();
				System.exit( 2 );
				return null; //never occurs
			}
	}
	
}
