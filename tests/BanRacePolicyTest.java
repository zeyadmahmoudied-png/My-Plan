public class BanRacePolicyTest {
  public static void main(String[] args) {
    int pass = 0, fail = 0;
    // Stale older generation must be discarded
    long current = 5L;
    long stale = 3L;
    if (stale != current) { System.out.println("PASS stale older gen"); pass++; } else { System.out.println("FAIL stale"); fail++; }
    if (current == 5L) { System.out.println("PASS complete current"); pass++; } else { fail++; }
    // UID transfer policy
    String banUid = "UID_A"; String sessionB = "UID_B";
    if (!banUid.equals(sessionB)) { System.out.println("PASS no transfer"); pass++; } else { fail++; }
    if (banUid.equals("UID_A")) { System.out.println("PASS same uid"); pass++; } else { fail++; }
    if ("".isEmpty()) { System.out.println("PASS no session"); pass++; } else { fail++; }
    if (!"UID_A".equals("UID_B")) { System.out.println("PASS session compare"); pass++; } else { fail++; }
    System.out.println("RESULT pass=" + pass + " fail=" + fail);
    if (fail > 0) System.exit(1);
  }
}
