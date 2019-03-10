package interviewer.Thread

import java.util.concurrent.locks.ReentrantReadWriteLock

class ReadWriteLockQueue {
    var data:Any? = null
    var rwl = ReentrantReadWriteLock()

    fun get(){
        rwl.readLock().lock()   //上读锁，其他线程只能读不能写
        println(Thread.currentThread().getName() + " be ready to read data!")
        try {
            Thread.sleep((Math.random()*1000).toLong())
            println(Thread.currentThread().getName() + "have read data :" + data)
        }catch (e:InterruptedException){
            e.printStackTrace()
        }catch (e:Exception){
            e.printStackTrace()
        }finally {
            rwl.readLock().unlock()
        }
    }

    fun put(data:Any){
        rwl.writeLock().lock()
        println(Thread.currentThread().getName() + " be ready to write  data!")
        try {
            Thread.sleep((Math.random()*1000).toLong())
            this.data = data
            println(Thread.currentThread().getName() + " have write data: " + data)
        }catch (e:InterruptedException){
            e.printStackTrace()
        }catch (e:Exception){
            e.printStackTrace()
        }finally {
            rwl.writeLock().unlock()
        }
    }
}