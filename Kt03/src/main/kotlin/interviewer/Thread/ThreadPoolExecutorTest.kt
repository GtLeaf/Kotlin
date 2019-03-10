package interviewer.Thread

import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.SynchronousQueue
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit

class ThreadPoolExecutorTest() {
    init {
        ThreadPoolRun()
    }

    fun ThreadPoolRun(){
        var myRunnable = object :Runnable{
            override fun run() {
                try {
                    Thread.sleep(2000)
                    println(Thread.currentThread().name+"run")
                }catch (e:InterruptedException){
                    e.printStackTrace()
                }
            }
        }

        //SynchronousQueue有更多任务时，会开启新线程，任务结束时回收非核心线程
        /*val executor = ThreadPoolExecutor(3, 10, 5
                , TimeUnit.SECONDS, object :SynchronousQueue<Runnable>(){})*/
        //LinkedBlockingQueue有更多任务时，会将任务缓存与队列，复用核心线程而不会创建新线程，任务结束后不回收
        //LinkedBlockingQueue<Runnable>()中参数为队列长度,任务缓存数量超出队列长度时，抛异常
        /*val executor = ThreadPoolExecutor(3, 10, 5
                , TimeUnit.SECONDS, object :LinkedBlockingQueue<Runnable>(){})*/
        val executor = ThreadPoolExecutor(3, 10, 5
                , TimeUnit.SECONDS, object :LinkedBlockingQueue<Runnable>(){})


        executor.execute(myRunnable)
        executor.execute(myRunnable)
        executor.execute(myRunnable)
        println("----先开3个----")
        println("核心线程数${executor.corePoolSize}")
        println("线程池数${executor.poolSize}")
        println("任务队列数${executor.queue.size}")
        executor.execute(myRunnable)
        executor.execute(myRunnable)
        executor.execute(myRunnable)
        println("---再开三个----")
        println("核心线程数${executor.corePoolSize}")
        println("线程池数${executor.poolSize}")
        println("任务队列数${executor.queue.size}")
        Thread.sleep(8000)
        println("----休息8秒后----")
        println("核心线程数${executor.corePoolSize}")
        println("线程池数${executor.poolSize}")
        println("任务队列数${executor.queue.size}")
    }


}

fun main(args:Array<String>){
    var threadPoolRun = ThreadPoolExecutorTest()
}