package ca.willenborg.sprites2atlas;

public class Sprite {
	private String name;
	private int xPosition, yPosition;
	private int width, height;
	
	private Sprite() {
		//do nothing
	}
	
	public String getName() {
		return name;
	}
	
	public int getX() {
		return xPosition;
	}
	
	public int getY() {
		return yPosition;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public static class SpriteBuilder implements Builder {
		
		private String name;
		private int xPosition, yPosition;
		private int width, height;

		public SpriteBuilder setName( String name ) {
			this.name = name;
			return this;
		}
		
		public SpriteBuilder setX( int xPosition ) {
			this.xPosition = xPosition;
			return this;
		}
		
		public SpriteBuilder setY( int yPosition ) {
			this.yPosition = yPosition;
			return this;
		}
		
		public SpriteBuilder setWidth( int width ) {
			this.width = width;
			return this;
		}
		
		public SpriteBuilder setHeight( int height ) {
			this.height = height;
			return this;
		}	
		
		@Override
		public Sprite build() throws Exception {
			if ( name == null || 
					xPosition < 0 || 
					yPosition < 0 || 
					width < 0 || 
					height < 0 ) {
				throw new IllegalStateException();
			} else {
				Sprite sprite = new Sprite();
				sprite.name = name;
				sprite.xPosition = xPosition;
				sprite.yPosition = yPosition;
				sprite.width = width;
				sprite.height = height;
				return sprite;
			}
		}
	}
		
}
