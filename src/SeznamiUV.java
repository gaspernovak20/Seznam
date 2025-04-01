import java.util.PriorityQueue;
import java.util.Scanner;

public class SeznamiUV {
    private Sklad<String> sklad;
    private PrioritetnaVrsta<String> vrsta;

    public SeznamiUV() {
        sklad = new Sklad<String>();
        vrsta = new PrioritetnaVrsta<String>();
    }

    public String processInput(String input) {
        Scanner sc = new Scanner(input);
        String token = sc.next();
        String result = "OK";
        StringBuilder arg = new StringBuilder();

        switch (token) {
            case "s_add":
                if (sc.hasNext()) {
                    arg.append(sc.next());
                    if (arg.charAt(0) == '"') {
                        while (sc.hasNext()) {
                            arg.append(" ").append(sc.next());
                        }
                        if (arg.charAt(arg.length() - 1) == '"') {
                            arg.deleteCharAt(0);
                            arg.deleteCharAt(arg.length() - 1);
                            sklad.push(arg.toString());
                        } else {
                            result = "Error: Wrong string";
                        }

                    } else
                        sklad.push(arg.toString());

                } else
                    result = "Error: please specify a string";
                break;
            case "s_removeFirst":
                if (!sklad.isEmpty())
                    result = sklad.pop();
                else
                    result = "Error: stack is empty";
                break;
            case "s_reset":
                while (!sklad.isEmpty()) {
                    sklad.pop();
                }
                break;
            case "s_size":
                result = String.format("%d", sklad.size());
                break;
            case "pq_add": // brez elementov z več nizi “”
                if (sc.hasNext()) {
                    String val = sc.next();
                    vrsta.add(val);
                } else
                    result = "Error: please specify a string";
                break;
            case "pq_remove_first":
                if (!vrsta.isEmpty())
                    result = vrsta.removeFirst();
                else
                    result = "Error: priority queue is empty";
                break;
            case "pq_get_first":
                if (!vrsta.isEmpty())
                    result = vrsta.getFirst();
                else
                    result = "Error: priority queue is empty";
                break;
            case "pq_size":
                result = Integer.toString(vrsta.size());
                break;
            case "pq_depth":
                result = Integer.toString(vrsta.depth());
                break;
            case "pq_isEmpty":
                if (vrsta.isEmpty()) result = "Priority queue is empty";
                else result = "Priority queue is not empty";
                break;

        }
        return result;
    }
}
