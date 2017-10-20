import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UniqueIdentifier {
	private static List<Integer> ids = new ArrayList<Integer>();
	private static final int range = 10000;
	
	private static int index = 0;
	
	static{
		for(int i = 0; i < range; i++){
			ids.add(i);
		}
		Collections.shuffle(ids);
	}
	
	private UniqueIdentifier(){
		
	}
	
	public static int GetIdentifier(){
		if(index > ids.size() - 1)
			index = 0;
		return ids.get(index++);
	}
}
