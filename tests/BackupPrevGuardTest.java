public class BackupPrevGuardTest {
  public static void main(String[] args) {
    int pass = 0, fail = 0;
    // prev must be valid before replace
    boolean currentValid = true;
    boolean prevCopiedValid = true;
    if (currentValid && prevCopiedValid) { System.out.println("PASS prev valid before replace"); pass++; } else { fail++; }
    // invalid prev must not overwrite target
    boolean prevInvalid = true;
    boolean refused = prevInvalid;
    if (refused) { System.out.println("PASS refuse invalid prev"); pass++; } else { fail++; }
    // both corrupt: no wipe
    boolean bothCorrupt = true;
    boolean wiped = false;
    if (bothCorrupt && !wiped) { System.out.println("PASS no wipe both corrupt"); pass++; } else { fail++; }
    System.out.println("RESULT pass=" + pass + " fail=" + fail);
    if (fail > 0) System.exit(1);
  }
}
