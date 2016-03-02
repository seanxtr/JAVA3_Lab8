// ---------- Class Definition of TestResult -----------
public class TestResult {
	public int limit;
	public int size;
	public long time;
   	public boolean isMin;
   	
	public TestResult(int limit, int size, long time) {
		this.limit = limit;
		this.size = size;
		this.time = time;
	}
	
	public String toString() {
		return String.format("%s%.4f", (isMin ? "*":""), time / 1e9);
	}
}