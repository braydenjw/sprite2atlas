package ca.willenborg.sprites2atlas;

import java.util.List;

public class SpriteSheet {
	
	private String imageName;
	private int width;
	private int height;
	private List<? extends Sprite> sprites;
	
	private SpriteSheet( ) {
		//do nothing
	}

	public String getImageName() {
		return imageName;
	}
	
	private void setImageName( String imageName ) {
		this.imageName = imageName;
	}

	public int getWidth() {
		return width;
	}
	
	private void setWidth( int width ) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}
	
	private void setHeight( int height ) {
		this.height = height;
	}
	
	public Sprite getSprite( int index ) {
		return sprites.get( index );
	}
	
	public int getSpriteCount() {
		return sprites.size();
	}
	
	private void setSprites( List<? extends Sprite> sprites ) {
		this.sprites = sprites;
	}
	
	public static class SpriteSheetBuilder implements Builder {
		
		private String imageName;
		private int width;
		private int height;
		private List<? extends Sprite> sprites;
		
		public SpriteSheetBuilder() {
			sprites = null;
		}
		
		public SpriteSheetBuilder setImageName( String imageName) {
			this.imageName = imageName;
			return this;
		}
		
		public SpriteSheetBuilder setWidth( int width ) {
			this.width = width;
			return this;
		}
		
		public SpriteSheetBuilder setHeight( int height ) {
			this.height = height;
			return this;
		}
		
		public SpriteSheetBuilder setSprites( List<? extends Sprite> sprites ) {
			this.sprites = sprites;
			return this;
		}

		@Override
		public Object build() throws Exception {
			if ( imageName == null || sprites == null || width < 0 || height < 0 ) {
				throw new IllegalStateException();
			} else {
				SpriteSheet spriteSheet = new SpriteSheet();
				spriteSheet.setImageName( imageName );
				spriteSheet.setWidth( width );
				spriteSheet.setHeight( height );
				spriteSheet.setSprites( sprites );
				return spriteSheet;
			}
		}
		
	}
}
