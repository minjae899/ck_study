package test;

public class OverLoadingEx {
	
	private String name;
	private long w;
	private long h;
 
	//기본 생성자 선언
	//사각형의 이름 생성자 선언 [추가해주세]
	public OverLoadingEx(String name){
		this.name = name;
	}
 
	//정사각형의 넓이 (가로*세로)
	public double areaRectangle(long w) {
		return w * w;
	}

	//직사각형의 넓이 (가로*높이)
	public double areaRectangle(long w, long h) {
		name = "직사각형";
		return w * h;
	}

	//이름을 출력하는 메서드
	public void print(String name){
		System.out.println(name);
	}

	//사이즈를 출력하는 메서드
	public void print(long size){
		System.out.println(size);
	}

	//사가가형의 이름 + 사이즈 선언
	public void print(String name, long size){
		print(name);
		print(size);
	}
  
	//사각형의이름 정보모두 , 계산된 값 배열 
	public void print(){
		
	}

	public static void main(String[] args) {
		
	}
}