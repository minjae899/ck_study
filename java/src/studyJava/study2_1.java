package studyJava;

public class study2_1 {
	public static void main(String[] args) {
		// 10000원짜리 맥북 생성
		NoteBook noteBook01 = new NoteBook("apple","mac_pro",1,10000);
		//형석이 생일
		BirthDate birthDate = new BirthDate(1988, 12, 32);
		//형석이가 10000원 짜리 맥북을 갖는다.
		Programer p01 = new Programer("오형석", noteBook01, birthDate);
		
		System.out.println(p01.toString());
	}
}
class NoteBook {
	//변수
	private String brandName; 
	private String model; 
	private int serialNumber; 
	private int price;
	
	//생성자
	NoteBook(){}
	NoteBook(String brandName, String model, int serialNumber, int price){
		this.brandName = brandName;
		this.model = model;
		this.serialNumber = serialNumber;
		this.price = price;
	}
	//메서드
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public int getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(int serialNumber) {
		this.serialNumber = serialNumber;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "NoteBook [brandName=" + brandName + ", model=" + model + ", serialNumber=" + serialNumber + ", price="
				+ price + "]";
	}
}


class Programer {
	//변수
	private String name;
	private NoteBook noteBook;
	private BirthDate birthDate;
	
	//생성자
	Programer(){
		
	}
	Programer(String name, NoteBook noteBook, BirthDate birthDate){
		this.name = name;
		this.noteBook = noteBook;
		this.birthDate = birthDate;
	}
	//메서드
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public NoteBook getNoteBook() {
		return noteBook;
	}
	public void setNoteBook(NoteBook noteBook) {
		this.noteBook = noteBook;
	}
	@Override
	public String toString() {
		return "Programer [name=" + name + ", noteBook=" + noteBook + "]";
	}
	
}

class BirthDate{
	private int year;
	private int month;
	private int day;
	BirthDate(int year, int month, int day){
		this.year = year;
		this.month = month;
		this.day = day;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	@Override
	public String toString() {
		return "BirthDate [year=" + year + ", month=" + month + ", day=" + day + "]";
	}
}

