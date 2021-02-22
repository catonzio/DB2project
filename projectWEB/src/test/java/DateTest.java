
import it.polimi.project.ejb.entities.Admin;
import it.polimi.project.ejb.entities.User;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.file.FileSystems;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public void provaMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("admin", new Admin());
        map.put("error", "erroreee");
        map.put("user", new User());

        for(String k : map.keySet()) {
            System.out.println("With key " + k + ", element " + map.get(k).getClass());
        }
    }

    public void provaSimpleDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        LocalDate date = LocalDate.parse("2021-02-22");
        LocalDate now = LocalDate.now();
        System.out.println(now);
        System.out.println(date);
        System.out.println(now.equals(date));
    }

    public static void main(String[] args) {
        DateTest test = new DateTest();
        //test.provaFile();
        test.provaSimpleDate();
    }


}
