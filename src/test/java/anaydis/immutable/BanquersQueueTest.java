package anaydis.immutable;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

public class BanquersQueueTest{

    @Test
    public void  test_new_queueu_is_empty(){
        BanquersQueue<Integer> banquersQueue = new BanquersQueue<>();
        assertTrue(banquersQueue.isEmpty());
    }

    @Test
    public void test02_added_elem_is_not_empty(){
        BanquersQueue<Integer> banquersQueue = new BanquersQueue<>(), banquersQueue1;
        banquersQueue1 = banquersQueue.enqueue(1);
        assertFalse(banquersQueue1.isEmpty());
    }

    @Test
    public void test02_added_elem_and_dequeued(){
        BanquersQueue<Integer> banquersQueue = new BanquersQueue<>(), banquersQueue1;
        banquersQueue1 = banquersQueue.enqueue(1);
        Queue.Result r = banquersQueue1.dequeue();
        assertEquals(1, r.value);
        assertTrue(r.queue.isEmpty());
    }

    @Test
    public void test0e_added_multi_elems_dequeued(){
        Queue<Integer> banquersQueue = new BanquersQueue<>(), banquersQueue1, banquersQueue2, banquersQueue3, banquersQueue4;
        banquersQueue1 = banquersQueue.enqueue(1);
        banquersQueue2 = banquersQueue1.enqueue(2);

        Queue.Result<Integer> r = banquersQueue2.dequeue();
        banquersQueue3 =  r.queue;
        assertEquals(1,  r.value.intValue());

        banquersQueue4 = banquersQueue3.enqueue(3);
        Queue.Result r2 = banquersQueue4.dequeue();
        assertEquals(2, r2.value);


    }

}