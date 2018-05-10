package designpattern;

/*
 * ������ģʽ
 * ���ó���������Ĵ�����SQL���Ĵ�����һ���ɶ�������ɵĸ��Ӵ��ڵĴ����ȡ�
 */
public class BuilderPattern {
	public static void main(String[] args) {
		Theme theme = Director.BuildTheme(new SpringThemeBuilder());
		System.out.println(theme);
	}
}

/*
 * һ�����ӵĲ�Ʒ��Ҳ�����յĴ���Ŀ�ꡣ����Թ̶��Ķ�����Ӳ��ֹ��ɡ�
 * �����ò�Ʒ��Ҫ������Ʒ�ĸ�����ɲ��֡�
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
 * ����Ĵ���������������ϸ�ڡ�
 */
abstract class ThemeBuilder{
	
	abstract void buildHeaders();
	
	abstract void buildBackGround();
	
	abstract void buildFooter();
	
	abstract Theme GetTheme();
}

/*
 * ָ���ߡ�ʹ��һ����������ʵ�������ȴ������̡�������ע��������ֵĴ���ϸ�ڡ�
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
 * ����Ĵ�������ʵ�ָ������ֵĴ���ϸ�ڡ�
 * ��չ��ͬ�Ĵ�����ʵ�֣������ڴ������Ȳ��������¸ı䴴��ϸ�ڣ��Ӷ�������ͬ�Ĳ�Ʒ��
 */
class SpringThemeBuilder extends ThemeBuilder{
	
	protected Theme theme = new Theme();

	@Override
	void buildHeaders() {
		theme.setHeaders("��������ҳͷ");
	}

	@Override
	void buildBackGround() {
		theme.setBackground("�������ⱳ��");
	}

	@Override
	void buildFooter() {
		theme.setFooter("��������ҳ��");
	}

	@Override
	Theme GetTheme() {
		return theme;
	}
}