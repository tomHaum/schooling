package march14;

public class RefTest{
	public String name;
	RefTest(String name){
		this.name = name;
	}
	public String getName(){
		return this.name;
	}
	public void setName(String name){
		this.name = name;
	}
	public static void main(String[] args){
		RefTest a = new RefTest("Tom");
		final RefTest b = new RefTest("Chuck");
		RefTest c = a;
		c.setName("Doug");
		System.out.println(a.getName() + " " + b.getName() + " " + c.getName());
		a.setName("Tom");
		System.out.println(a.getName() + " " + b.getName() + " " + c.getName());
		
		InnerTest d = new RefTest("Eric").new InnerTest(a);
		d.setLast("Haum");
		d.getRefTest().setName("Ant");
		System.out.println(a.getName() + " " + b.getName() + " " + c.getName());
	}
	public class InnerTest{
		public RefTest a;
		public String last;
		InnerTest(RefTest a){
			this.a = a;
		}
		InnerTest(){
			
		}
		public RefTest getRefTest(){
			return a;
		}
		public String getLast(){
			return last;
		}
		public void setReftTest(RefTest a){
			this.a = a;
		}
		public void setLast(String last){
			this.last = last;
		}
	}
}
