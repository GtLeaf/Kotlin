package Image

import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

fun main(args: Array<String>) {
    //内存中创建
    var image = BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB)
    image.setRGB(0,0,0xff0000)
    var x = 0 .. 99//宽度
    var h = 0 .. 99//高度
    image.apply {
        for (i in x){
            for (j in h){
                setRGB(i, j, 0x0000ff)
            }
        }
    }
    ImageIO.write(image, "bmp", File("a.bmp"))
}