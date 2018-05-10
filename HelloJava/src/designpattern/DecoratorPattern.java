package designpattern;

/*
 * 装饰器模式。是一个且有一个，主要用于对持有对象添加新的行为。
 * 适用场景：图片处理，数据包封装等。
 */
public class DecoratorPattern {
	public static void main(String[] args) {
		Picture p1 = new NormalPicture("D:/pic.jpg");
		p1.draw();
		
		Picture p2 = new CuttedPicture(p1, 0.5f);
		p2.draw();
		
		Picture p3 = new FramedPicture(p2, "Red", "1px");
		p3.draw();
		
		Picture p4 = new GraiedPicture(p3, 0.2f);
		p4.draw();
		
		Picture p5 = new GraiedPicture(p2, 0.2f);
		p5.draw();
	}
}

interface Picture{
	void draw();
}

class NormalPicture implements Picture{
	
	private String path;
	
	public NormalPicture(String path) {
		this.path = path;
	}

	@Override
	public void draw() {
		System.out.println("===================================");
		System.out.println("绘制图片" + this.path);
	}
	
}

class FramedPicture implements Picture{
	
	private Picture innerPicture;
	private String color;
	private String width;
	
	public FramedPicture(Picture inner, String color, String width) {
		this.innerPicture = inner;
		this.color = color;
		this.width = width;
	}

	@Override
	public void draw() {
		this.innerPicture.draw();
		System.out.println("添加边框，颜色:" + this.color + ";宽度:" + this.width);
	}
	
}

class CuttedPicture implements Picture{
	
	private Picture innerPicture;
	private float precent;
	
	public CuttedPicture(Picture inner, float precent) {
		this.innerPicture = inner;
		this.precent = precent;
	}

	@Override
	public void draw() {
		this.innerPicture.draw();
		System.out.println("裁剪图片，比例:" + this.precent);
	}
	
}

class GraiedPicture implements Picture{
	
	private Picture innerPicture;
	private float precent;
	
	public GraiedPicture(Picture inner, float precent) {
		this.innerPicture = inner;
		this.precent = precent;
	}

	@Override
	public void draw() {
		this.innerPicture.draw();
		System.out.println("灰化图片，灰度:" + this.precent);
	}
	
}
