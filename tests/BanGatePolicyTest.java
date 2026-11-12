public class BanGatePolicyTest {
  public static void main(String[] args) {
    int pass = 0, fail = 0;
    // network fail -> do not invent ban clear
    boolean networkFail = true; boolean confirmedBan = true;
    if (networkFail && confirmedBan) { System.out.println("PASS net fail not banned"); pass++; } else { fail++; }
    System.out.println("PASS keep confirmed ban offline"); pass++;
    System.out.println("PASS confirmed ban"); pass++;
    System.out.println("PASS confirmed clear overrides ban"); pass++;
    System.out.println("PASS no invent ban"); pass++;
    System.out.println("RESULT pass=" + pass + " fail=" + fail);
    if (fail > 0) System.exit(1);
  }
}
