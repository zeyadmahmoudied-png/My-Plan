public class BackupValidateTest {
  static boolean isValidPlanner(String json) {
    if (json == null) return false;
    String t = json.trim();
    if (t.length() < 8) return false;
    if (!t.startsWith("{")) return false;
    return t.contains("tasks");
  }
  static boolean isValidStudy(String json) {
    if (json == null) return false;
    String t = json.trim();
    if (t.length() < 2) return false;
    if (!t.startsWith("{") && !t.startsWith("[")) return false;
    return t.contains("chapters") || t.contains("notes") || t.contains("schema");
  }
  public static void main(String[] args) {
    int pass = 0, fail = 0;
    if (!isValidPlanner("{not")) { System.out.println("PASS empty reject"); pass++; } else { fail++; }
    if (!isValidPlanner("{\"a\":1}")) { System.out.println("PASS no tasks reject"); pass++; } else { fail++; }
    if (!isValidPlanner(null)) { System.out.println("PASS null reject"); pass++; } else { fail++; }
    if (isValidStudy("{\"schema\":1,\"chapters\":[]}")) { System.out.println("PASS study"); pass++; } else { fail++; }
    if (!isValidStudy("junk")) { System.out.println("PASS study junk"); pass++; } else { fail++; }
    if (isValidPlanner("{\"schema\":1,\"tasks\":[]}")) { System.out.println("PASS old kept on invalid new"); pass++; } else { fail++; }
    System.out.println("RESULT pass=" + pass + " fail=" + fail);
    if (fail > 0) System.exit(1);
  }
}
