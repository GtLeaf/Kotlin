package Thread

import java.util.*
import java.util.concurrent.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class TicketSellerConCurrent {
    val ticket:Queue<String> = ConcurrentLinkedQueue<String>()

    init {
        for (i in 0 until 1000){
            ticket.add("票编号：${i}")
        }
    }

}
fun main(args:Array<String>){
    /*val ticket = TicketSellerConCurrent()

    for (i in 0..100){
        Thread{
            while (true){
                var s: String? = ticket.ticket.poll() ?: break
                println(s)
            }
        }.start()
    }*/

    concurrentMap()

}

fun concurrentMap(){
//    val map = ConcurrentHashMap<String, String>()//281~257
    //跳表，线程安全，且排好序
//    val map = ConcurrentSkipListMap<String, String>()//327
//    val set = ConcurrentSkipListSet<String>()//327
    val map = Hashtable<String, String>()//442
//    val map = HashMap<String, String>()

    //TreeMap

    //
    var arrayList = ArrayList<String>()
    var listSync = Collections.synchronizedList(arrayList)
    var vector = Vector<String>()
    //写时复制容器，多线程先，写掉率低，读效率高。适合写少读多
    //适合添加事件监听器，添加的少，读的多
    var copyOnWriteArrayList = CopyOnWriteArrayList<String>()

    //并发队列
    //放进去的对象需要延迟一段时间后才能被消费，定时执行任务,两小时后关机
    var delayQueue = DelayQueue<MyDelayTask>()
    //无界，单向链表
    var concLinkedQueue = ConcurrentLinkedQueue<String>()
    //双向链表
    var concLinkedDeque = ConcurrentLinkedDeque<String>()
    //阻塞队列，生产消费模式BlockingQueue
    var linkBlockingQueue = LinkedBlockingQueue<String>()
    //满时add()抛异常，offer()不会
    var arrayBlockingQueue = ArrayBlockingQueue<String>(10)
    //队列满时add()抛异常
    arrayBlockingQueue.add("aaa")
    //队列满时返回false
    var isSuccess = arrayBlockingQueue.offer("aaa")
    //一秒钟内都加不进去，就返回false
    var isSuccess2 = arrayBlockingQueue.offer("aaa", 1, TimeUnit.SECONDS)
    //队列满了会等待，线程阻塞
    arrayBlockingQueue.put("aaa")

    //元素进队列前，先检查是否有消费者，有就直接给消费者，不进队列
    var linkedTransferQueue = LinkedTransferQueue<String>()
    //没有消费者，阻塞线程，有的话直接给消费者
    //add(),offer(),put()不会阻塞
    linkedTransferQueue.transfer("aaa")
    //容量为0的TransferQueue，
    // 不能调用add()，要调用put()阻塞等待消费者
    var synchronousQueue = SynchronousQueue<String>()

    //测试逻辑
    var r = Random()
    var ths = arrayOfNulls<Thread>(100)
    var latch = CountDownLatch(ths.size)
    var start = System.currentTimeMillis()

    for (i in 0 until ths.size){
        ths[i] = Thread{
            for (j in 0 until 10000){
                map.put("a"+r.nextInt(10000), "a${r.nextInt(10000)}")
            }
            latch.countDown()
        }
    }
    ths.forEach { it?.start() }
    try {
        latch.await()
    }catch (e:InterruptedException){
        e.printStackTrace()
    }

    var end = System.currentTimeMillis()
    println(end - start)
}

class MyDelayTask(rt:Long):Delayed{
    val runningTime = rt
    override fun compareTo(other: Delayed?): Int {
        return (this.getDelay(TimeUnit.SECONDS) - other!!.getDelay(TimeUnit.SECONDS)).toInt()
    }

    override fun getDelay(unit: TimeUnit?): Long {
        return unit!!.convert( runningTime - System.currentTimeMillis(), TimeUnit.SECONDS )
    }

}