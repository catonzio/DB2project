
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.file.FileSystems;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateTest {

    public void provaDate() {
        String input = "23/01/2018" ;
        DateTimeFormatter f = DateTimeFormatter.ofPattern( "dd/MM/uuuu" ) ;
        LocalDate ld = LocalDate.parse( input , f );
        Date date = Date.from(ld.atStartOfDay(ZoneId.systemDefault()).toInstant());
        System.out.println(date);
    }

    public void provaFile() {
        try {
            String path = FileSystems.getDefault()
                    .getPath("")
                    .toAbsolutePath()
                    .toString() + "/images";
            String fileName = "prova.png";
            OutputStream out = new FileOutputStream(new File(path + File.separator + fileName));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        DateTest test = new DateTest();
        test.provaFile();
    }


}
