import java.awt.PageAttributes.ColorType;

/**
 * Classe PixelMapPlus
 * Image de type noir et blanc, tons de gris ou couleurs
 * Peut lire et ecrire des fichiers PNM
 * Implemente les methodes de ImageOperations
 * @author : 
 * @date   : 
 */

public class PixelMapPlus extends PixelMap implements ImageOperations 
{
	/**
	 * Constructeur creant l'image a partir d'un fichier
	 * @param fileName : Nom du fichier image
	 */
	PixelMapPlus(String fileName)
	{
		super( fileName );
	}
	
	/**
	 * Constructeur copie
	 * @param type : type de l'image a creer (BW/Gray/Color)
	 * @param image : source
	 */
	PixelMapPlus(PixelMap image)
	{
		super(image); 
	}
	
	/**
	 * Constructeur copie (sert a changer de format)
	 * @param type : type de l'image a creer (BW/Gray/Color)
	 * @param image : source
	 */
	PixelMapPlus(ImageType type, PixelMap image)
	{
		super(type, image); 
	}
	
	/**
	 * Constructeur servant a allouer la memoire de l'image
	 * @param type : type d'image (BW/Gray/Color)
	 * @param h : hauteur (height) de l'image 
	 * @param w : largeur (width) de l'image
	 */
	PixelMapPlus(ImageType type, int h, int w)
	{
		super(type, h, w);
	}
	
	/**
	 * Genere le negatif d'une image
	 */
	public void negate()
	{
		// compl�ter
		for(int i = 0; i < height; i++) {
			for(int j = 0; j < width; j++) {
				this.imageData[i][j] = this.imageData[i][j].Negative();
			}
		}
	}
	
	/**
	 * Convertit l'image vers une image en noir et blanc
	 */
	public void convertToBWImage()
	{
		// compl�ter
		for(int i = 0; i < height; i++) {
			for(int j = 0; j < width; j++) {
				this.imageData[i][j] = this.imageData[i][j].toBWPixel();
			}
		}
	}
	
	/**
	 * Convertit l'image vers un format de tons de gris
	 */
	public void convertToGrayImage()
	{
		// compl�ter
		for(int i = 0; i < height; i++) {
			for(int j = 0; j < width; j++) {
				this.imageData[i][j] = this.imageData[i][j].toGrayPixel();
			}
		}
	}
	
	/**
	 * Convertit l'image vers une image en couleurs
	 */
	public void convertToColorImage()
	{
		// compl�ter
		for(int i = 0; i < height; i++) {
			for(int j = 0; j < width; j++) {
				this.imageData[i][j] = this.imageData[i][j].toColorPixel();
			}
		}
	}
	
	public void convertToTransparentImage()
	{
		// compl�ter
		for(int i = 0; i < height; i++) {
			for(int j = 0; j < width; j++) {
				this.imageData[i][j] = this.imageData[i][j].toTransparentPixel();
			}
		}
	}
	
	/**
	 * Fait pivoter l'image de 10 degres autour du pixel (row,col)=(0, 0)
	 * dans le sens des aiguilles d'une montre (clockWise == true)
	 * ou dans le sens inverse des aiguilles d'une montre (clockWise == false).
	 * Les pixels vides sont blancs.
	 * @param clockWise : Direction de la rotation 
	 */
	public void rotate(int x, int y, double angleRadian)
	{
		// compl�ter
		
	}
	
	/**
	 * Modifie la longueur et la largeur de l'image 
	 * @param w : nouvelle largeur
	 * @param h : nouvelle hauteur
	 */
	public void resize(int w, int h) throws IllegalArgumentException
	{
		if(w < 0 || h < 0)
			throw new IllegalArgumentException();


		AbstractPixel[][] imageResize = new AbstractPixel[h][w];

		double x_ratio = this.width / (double) w;
		double y_ratio = this.height / (double) h;

		for(int i = 0; i < h; i++){
			for(int j = 0; j < w; j++){
				double px = Math.floor(j*x_ratio);  		
				double py = Math.floor(i*y_ratio);
				imageResize[i][j] = this.imageData[(int)py][(int)px];
			}
		}


		this.height = h;
		this.width = w;
		this.imageData = imageResize;
	}

	/**
	 * Insert pm dans l'image a la position row0 col0
	 */
	public void inset(PixelMap pm, int row0, int col0)
	{
		for(int i = 0; i < pm.height; i++){
			for(int j = 0; j < pm.width; j++){
				if(i + row0 < height && j + col0 < width) {
					this.imageData[i + row0][j + col0] = pm.getPixel(i, j);
				}
			}
		}
	}

	/**
	 * Decoupe l'image
	 */
	public void crop(int h, int w)
	{
		AbstractPixel[][] imageCrop = new AbstractPixel[h][w];

		for(int i = 0; i < h; i++){
			for(int j = 0; j < w; j++){
				if(i < this.height && j < this.width){
					imageCrop[i][j] = this.imageData[i][j];
				}else{
					imageCrop[i][j] = new ColorPixel();  //blanc
				}
			}
		}

		this.imageData = imageCrop;
		this.height = h;
		this.width = w;
	}

	/**
	 * Effectue une translation de l'image
	 */
	public void translate(int rowOffset, int colOffset)
	{

		AbstractPixel[][] imageTranslate = new AbstractPixel[height][width];

		for(int i = 0; i < this.height; i++){
			for(int j = 0; j < this.width; j++){
				if(i + rowOffset < this.height && i + rowOffset >= 0 
				&& j + colOffset < this.width && j + colOffset >= 0){
					imageTranslate[i + rowOffset][j + colOffset] = this.imageData[i][j];
				}else{
					imageTranslate[i][j] = new ColorPixel(); //blanc
				}
			}
		}

		this.imageData = imageTranslate;
	}

	/**
	 * Effectue un zoom autour du pixel (x,y) d'un facteur zoomFactor
	 * @param x : colonne autour de laquelle le zoom sera effectue
	 * @param y : rangee autour de laquelle le zoom sera effectue
	 * @param zoomFactor : facteur du zoom a effectuer. Doit etre superieur a 1
	 */
	public void zoomIn(int x, int y, double zoomFactor) throws IllegalArgumentException
	{
		if(zoomFactor < 1.0)
			throw new IllegalArgumentException();

		//Taille de la partie a zoomer
		int height_zoom = (int) (this.height / zoomFactor);
		int width_zoom = (int) (this.width / zoomFactor);

		AbstractPixel[][] imageZoom = new AbstractPixel[height_zoom][width_zoom];   //On recupere la partie zoomé avant de l'agrandir


		while(x - height_zoom/2 <= 0 || x + height_zoom/2 >= this.height || y - width_zoom/2 <= 0 || y + width_zoom/2 >= this.width){
			if(x - height_zoom/2 <= 0)
				x+=1;
			if(x + height_zoom/2 >= this.height)
				x-=1;
			if(y - width_zoom/2 <= 0)						//On deplace le centre si nécessaire
				y+=1;
			if(y + width_zoom/2 >= this.width)
				y-=1;
		}

		for(int i = 0; i < height_zoom; i++){
			for(int j = 0; j < width_zoom; j++){
				imageZoom[i][j] = this.imageData[i + (x - height_zoom/2)][j + (y - width_zoom/2)];
					}
				}
		int old_width = this.width;
		int old_height = this.height;

		this.height=height_zoom;
		this.width=width_zoom;
		this.imageData = imageZoom;

		this.resize(old_width, old_height);
	}
}