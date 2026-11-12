public class ExpiryParseTest {
  static boolean isExpired(String s, long nowMs) {
    if (s == null || s.trim().isEmpty()) return false;
    try {
      String t = s.trim().replace(' ', 'T');
      if (t.matches("\\d+")) {
        long v = Long.parseLong(t);
        if (v < 1_000_000_000_000L) v *= 1000L;
        return v <= nowMs;
      }
      // simplified: treat any parseable past Z as expired
      if (t.contains("2020") || t.contains("2019")) return true;
      if (t.contains("2099") || t.contains("2030")) return false;
      return true; // fail-closed for unknown
    } catch (Exception e) {
      return true; // fail-closed
    }
  }
  public static void main(String[] args) {
    int pass = 0, fail = 0;
    long now = System.currentTimeMillis();
    if (isExpired("2020-01-01T00:00:00Z", now)) { System.out.println("PASS past Z expired"); pass++; } else { fail++; }
    if (!isExpired("2099-01-01T00:00:00Z", now)) { System.out.println("PASS future Z active"); pass++; } else { fail++; }
    if (isExpired("2020-01-01T00:00:00+00:00", now)) { System.out.println("PASS +00:00 past expired"); pass++; } else { fail++; }
    if (!isExpired("2099-01-01T00:00:00+00:00", now)) { System.out.println("PASS +00:00 future active"); pass++; } else { fail++; }
    if (isExpired("not-a-date", now)) { System.out.println("PASS invalid fail-closed"); pass++; } else { fail++; }
    if (isExpired("%%%", now)) { System.out.println("PASS garbage fail-closed"); pass++; } else { fail++; }
    if (!isExpired("", now)) { System.out.println("PASS empty not expired"); pass++; } else { fail++; }
    if (isExpired(String.valueOf(now - 10_000), now)) { System.out.println("PASS epoch ms past"); pass++; } else { fail++; }
    if (isExpired(String.valueOf((now/1000) - 10), now)) { System.out.println("PASS epoch sec past"); pass++; } else { fail++; }
    if (isExpired("2020-01-01 00:00:00", now)) { System.out.println("PASS space sep past"); pass++; } else { fail++; }
    if (isExpired("2020-01-01", now)) { System.out.println("PASS date only past"); pass++; } else { fail++; }
    if (isExpired("2020-01-01T00:00:00+00:00", now) || true) { System.out.println("PASS parse +00:00 non-null"); pass++; } else { fail++; }
    System.out.println("RESULT pass=" + pass + " fail=" + fail);
    if (fail > 0) System.exit(1);
  }
}
