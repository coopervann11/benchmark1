package benchmark1.benchmark1;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

@Controller
public class WebController {

    @GetMapping("/benchmark")
    public String mainWithParam(@RequestParam(name = "name", required = false, defaultValue = "") String name, Model model) throws Exception {

        File file = new ClassPathResource("/static/MOCK_DATA.csv").getFile();
        Reader reader = new BufferedReader(new FileReader(file));
        CsvToBean<User> csvToBean = new CsvToBeanBuilder(reader)
                .withType(User.class)
                .withIgnoreLeadingWhiteSpace(true)
                .build();
        List<User> users = csvToBean.parse();
        StringBuilder foo1 = new StringBuilder();
        StringBuffer foo2 = new StringBuffer();
        // 1
        long start1 = System.currentTimeMillis();
        for (User u : users) {
            foo1.append(u.getFirstName() + "<br>");
        }
        long duration1 = System.currentTimeMillis() - start1;
        DateFormat sdf1 = new SimpleDateFormat("ss.SSS");
        String stringDate1 = sdf1.format(duration1);
        // 2
        long start2 = System.currentTimeMillis();
        users.forEach(u -> foo2.append(u + "<br>"));
        long duration2 = System.currentTimeMillis() - start2;
        DateFormat sdf2 = new SimpleDateFormat("ss.SSS");
        String stringDate2 = sdf2.format(duration2);
        // HTML Template
        model.addAttribute("diffInSeconds1", stringDate1);
        model.addAttribute("diffInSeconds2", stringDate2);
        model.addAttribute("name", name);
        return "benchmark"; //view
    }

    @GetMapping("/")
    public String mainWithParam() {
        return "index"; //view
    }
}
