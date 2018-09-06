package anaydis.sort.practice.TP2;

class TimeTestMain {
    public static void main(String[] args) {

        for (AbstractTimeTest timeTester :AbstractTimeTest.getAllTesters()) {
            timeTester.timeTest();
        }
    }
}