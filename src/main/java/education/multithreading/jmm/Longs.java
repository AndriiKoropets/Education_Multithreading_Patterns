package education.multithreading.jmm;

//@JCStressTest
//@Outcome(id = "0",  expect = ACCEPTABLE,             desc = "Seeing the default value: writer had not acted yet.")
//@Outcome(id = "-1", expect = ACCEPTABLE,             desc = "Seeing the full value.")
//@Outcome(           expect = ACCEPTABLE_INTERESTING, desc = "Other cases are violating access atomicity, but allowed under JLS.")
//@Ref("https://docs.oracle.com/javase/specs/jls/se8/html/jls-17.html#jls-17.7")
//@State
public class Longs {
    long v;

//    @Actor
    public void writer() {
        v = 0xFFFFFFFF_FFFFFFFFL;
    }

//    @Actor
//    public void reader(J_Result r) {
//        r.r1 = v;
//    }
}
