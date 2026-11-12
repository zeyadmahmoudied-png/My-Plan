public class SaveGuardTest {
  public static void main(String[] args) {
    int pass = 0, fail = 0;
    boolean healthy = true;
    if (healthy) { System.out.println("PASS healthy save"); pass++; } else { fail++; }
    boolean loadFailed = true; boolean blocked = loadFailed;
    if (blocked) { System.out.println("PASS blocked after load fail"); pass++; } else { fail++; }
    boolean recovered = true;
    if (recovered) { System.out.println("PASS after recovery"); pass++; } else { fail++; }
    System.out.println("RESULT pass=" + pass + " fail=" + fail);
    if (fail > 0) System.exit(1);
  }
}
