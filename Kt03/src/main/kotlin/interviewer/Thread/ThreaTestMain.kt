package interviewer.Thread

fun main(args:Array<String>){
    //读写锁，读写互斥，写写互斥，读读不互斥
    val queue = ReadWriteLockQueue()

    //因为上读锁，读的时候排斥写操作
    for (i in 0..2){
        println("init+$i")
        Thread{
            for (i in 0..5){
                queue.get()
                try {
                    Thread.sleep(1000)
                }catch (e:InterruptedException){
                    e.printStackTrace()
                }
            }
        }.start()
    }

    for (i in 0..2){
        Thread{
            for (i in 0..5){
                queue.put((Math.random()*1000).toInt())
                try {
                    Thread.sleep(1000)
                }catch (e:InterruptedException){
                    e.printStackTrace()
                }
            }
        }.start()
    }
}