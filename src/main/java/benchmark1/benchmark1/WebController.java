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
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class WebController {

    @GetMapping("/benchmark")
    public String mainWithParam(@RequestParam(name = "name", required = false, defaultValue = "") final String name, final Model model) throws Exception {

        final File file = new ClassPathResource("/static/MOCK_DATA.csv").getFile();
        final Reader reader = new BufferedReader(new FileReader(file));
        final CsvToBean<User> csvToBean = new CsvToBeanBuilder(reader)
                .withType(User.class)
                .withIgnoreLeadingWhiteSpace(true)
                .build();
        final List<User> users = csvToBean.parse();
        final StringBuilder foo1 = new StringBuilder();
        final StringBuffer foo2 = new StringBuffer();
        // 1
        final long start1 = System.currentTimeMillis();
        final List<User> results1 = new ArrayList<User>();
        for (User u : users) {
            if(u.getLastName().startsWith(name.toUpperCase())) {
                results1.add(u);
            }
        }
        final long duration1 = System.currentTimeMillis() - start1;
        final DateFormat sdf1 = new SimpleDateFormat("ss.SSS");
        final String stringDate1 = sdf1.format(duration1);
        // 2
        final long start2 = System.currentTimeMillis();
        final List<User> results2 = users.stream()
                .filter(person -> person.getLastName().startsWith(name.toUpperCase()))
                .sorted(Comparator.comparing(User::getLastName))
                .collect(Collectors.toList());
        final long duration2 = System.currentTimeMillis() - start2;
        final DateFormat sdf2 = new SimpleDateFormat("ss.SSS");
        final String stringDate2 = sdf2.format(duration2);
        // HTML Template
        model.addAttribute("diffInSeconds1", stringDate1);
        model.addAttribute("diffInSeconds2", stringDate2);
        model.addAttribute("results1", results1);
        model.addAttribute("results2", results2);
        model.addAttribute("name", name);
        return "benchmark";
    }

    @GetMapping("/")
    public String mainWithParam() {
        return "index";
    }
}
