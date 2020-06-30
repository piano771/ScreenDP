import com.dropbox.core.v2.DbxClientV2;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyThread extends Thread{
    public DbxClientV2 client;

    @Override
    public void run() {
        for(;;) {
            //блок задающий формат даты и заносящий её в переменную "now"
            SimpleDateFormat formatter = new SimpleDateFormat("/yyyyMMdd_HHmmss.");
            SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date now = new Date();
            /*
             * примечание: в формате "formatter" "/" используется
             * для корректного сохранения файла в DB,
             * а точка нужна для задания формата изображению
             */

            try {
                Robot robot = new Robot();
                String picFormat = "png";
                String picName = formatter.format(now) + picFormat;

                // блок захвата экрана
                Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
                BufferedImage image = robot.createScreenCapture(screenRect);

                // блок создания изображения на основе захвата
                ByteArrayOutputStream os = new ByteArrayOutputStream();
                ImageIO.write(image, picFormat, os);

                // блок загрузки изображения в DB
                InputStream in = new ByteArrayInputStream(os.toByteArray());
                client.files().uploadBuilder(picName)
                        .uploadAndFinish(in);

                System.out.println("Скриншот успешно загружен: " + formatter2.format(now));
                sleep(1000 * 30);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
