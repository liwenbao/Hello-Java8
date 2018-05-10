package designpattern;

/*
 * 创建器模式
 * 适用场景：主题的创建；SQL语句的创建；一个由多个组件组成的复杂窗口的创建等。
 */
public class BuilderPattern {
	public static void main(String[] args) {
		Theme theme = Director.BuildTheme(new SpringThemeBuilder());
		System.out.println(theme);
	}
}

/*
 * 一个复杂的产品，也是最终的创建目标。由相对固定的多个复杂部分构成。
 * 创建该产品需要创建产品的各个组成部分。
 */
class Theme{
	private String headers;
	private String background;
	private String footer;
	
	public String getHeaders() {
		return headers;
	}
	
	public void setHeaders(String headers) {
		this.headers = headers;
	}

	public String getBackground() {
		return background;
	}
	
	public void setBackground(String background) {
		this.background = background;
	}
	
	public String getFooter() {
		return footer;
	}
	
	public void setFooter(String footer) {
		this.footer = footer;
	}
	
	public String toString() {
		return "Headers:" + this.getHeaders() + " Background:" + this.getBackground() +  " Footer:" + this.getFooter();
	}
}

/*
 * 抽象的创建器，声明创建细节。
 */
abstract class ThemeBuilder{
	
	abstract void buildHeaders();
	
	abstract void buildBackGround();
	
	abstract void buildFooter();
	
	abstract Theme GetTheme();
}

/*
 * 指挥者。使用一个创建器的实例，调度创建过程。而不关注具体各部分的创建细节。
 */
class Director {
	public static Theme BuildTheme(ThemeBuilder builder) {
		builder.buildHeaders();
		builder.buildBackGround();
		builder.buildFooter();
		return builder.GetTheme();
	}
}

/*
 * 具体的创建器。实现各个部分的创建细节。
 * 扩展不同的创建器实现，可以在创建调度不变的情况下改变创建细节，从而构建不同的产品。
 */
class SpringThemeBuilder extends ThemeBuilder{
	
	protected Theme theme = new Theme();

	@Override
	void buildHeaders() {
		theme.setHeaders("春天主题页头");
	}

	@Override
	void buildBackGround() {
		theme.setBackground("春天主题背景");
	}

	@Override
	void buildFooter() {
		theme.setFooter("春天主题页脚");
	}

	@Override
	Theme GetTheme() {
		return theme;
	}
}
