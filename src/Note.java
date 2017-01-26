
public class Note{
	int length, position, time;
	Note(int p, int l, int t){
		position = p;
		length = l;
		time = t;
	}
	public String toString(){
		return position + " " + length + " " + time;
	}
}