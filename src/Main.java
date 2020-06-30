import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;

public class Main {
    public static void main(String[] args) {
        // блок доступа к DB
        String ACCESS_TOKEN = "your_token";
        DbxRequestConfig config = DbxRequestConfig.newBuilder("dropbox/java-tutorial").build();

        //блок запуска класса "MyThread" и передачи значения переменной "client"
        MyThread thread = new MyThread();
        thread.client = new DbxClientV2(config, ACCESS_TOKEN);
        thread.start();

        /*
         * TODO: - используется для выделения заданий
         *
         * что бы закомментировать несколько строк нужно
         * выделить их и нажать: Ctrl + /
         *
         * для того, что бы вывести разрешение изображения
         * можно использовать команду:
         * System.out.println(image.getWidth() + "x" + image.getHeight());
         *
         * для того, что бы цикл "for" сделать бесконечным
         * нужно поставить два раза знак ";"
         * пример: for(;;) {}
         */
    }
}
