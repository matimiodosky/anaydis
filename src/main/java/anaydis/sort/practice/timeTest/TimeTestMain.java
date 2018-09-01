package anaydis.sort.practice.timeTest;

class TimeTestMain {
    public static void main(String[] args) {

        for (AbstractTimeTest timeTester :AbstractTimeTest.getAllTesters()) {
            timeTester.timeTest();
        }
    }
}