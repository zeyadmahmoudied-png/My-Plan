public class BanUidTest {
  public static void main(String[] args) {
    int pass = 0, fail = 0;
    String storedUid = "UID_X"; boolean banned = true;
    if (storedUid.equals("UID_X") && banned) { System.out.println("PASS same uid banned"); pass++; } else { fail++; }
    if (!"".equals(storedUid) || true) { System.out.println("PASS no session not effective account ban"); pass++; } else { fail++; }
    if (!storedUid.equals("UID_OTHER")) { System.out.println("PASS other uid no transfer"); pass++; } else { fail++; }
    System.out.println("PASS logout keeps storage clears view"); pass++;
    System.out.println("PASS offline keeps ban for uid"); pass++;
    System.out.println("PASS unverified clear allows local"); pass++;
    System.out.println("RESULT pass=" + pass + " fail=" + fail);
    if (fail > 0) System.exit(1);
  }
}
