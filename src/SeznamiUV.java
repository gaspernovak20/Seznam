import java.util.HashMap;
import java.util.Scanner;

public class SeznamiUV {
    HashMap<String, Seznam<String>> seznami;
    Seznam<String> seznam;

    public SeznamiUV() {
        seznami = new HashMap<>();
        seznami.put("sk", new Sklad<>());
        seznami.put("pv", new PrioritetnaVrsta<>());
        seznami.put("bst", new BST<>());
    }

    public String processInput(String input) {
        Scanner sc = new Scanner(input);
        String token = sc.next();
        String result = "OK";
        StringBuilder arg = new StringBuilder();

        switch (token) {
            case "use":
                if (sc.hasNext()) {
                    seznam = seznami.get(sc.next());
                    if (seznam == null) result = "Error: please specify a correct data structure type (pv, sk, bst)";
                } else result = "Error: please specify a data structure type (pv, sk, bst)";
                break;
            case "add":
                if (sc.hasNext()) {
                    arg.append(sc.next());
                    if (arg.charAt(0) == '"') {
                        while (sc.hasNext()) {
                            arg.append(" ").append(sc.next());
                        }
                        if (arg.charAt(arg.length() - 1) == '"') {
                            arg.deleteCharAt(0);
                            arg.deleteCharAt(arg.length() - 1);
                            seznam.add(arg.toString());
                        } else {
                            result = "Error: Wrong string";
                        }

                    } else seznam.add(arg.toString());

                } else result = "Error: please specify a string";
                break;
            case "removeFirst":
                if (!seznam.isEmpty()) result = seznam.removeFirst();
                else result = "Error: Data structure is empty";
                break;
            case "reset":
                while (!seznam.isEmpty()) seznam.removeFirst();
                break;
            case "size":
                result = String.format("%d", seznam.size());
                break;
            case "exists":
                if (sc.hasNext()) {
                    if (seznam.isEmpty()) {
                        result = "Data structure is empty";
                    } else {
                        result = seznam.exists(sc.next()) ? "Element exists in data structure" : "Element does not exist in data structure";
                    }
                } else result = "Please specify a string";
                break;
            case "get_first":
                if (!seznam.isEmpty()) result = seznam.getFirst();
                else result = "Error: Data structure is empty";
                break;
            case "depth":
                result = String.format("%d", seznam.depth());
                break;
            case "isEmpty":
                if (seznam.isEmpty()) result = "Data structure is empty";
                else result = "Data structure is not empty";
                break;
            case "remove":
                if (sc.hasNext()) {
                    if (seznam.isEmpty()) result = "Data structure is empty";
                    else {
                        arg.append(sc.next());
                        if (seznam.exists(arg.toString())){
                            result = seznam.remove(arg.toString());
                        }else result = "Element not found";
                    }
                } else result = "Please specify a string";
                break;
        }
        return result;
    }
}
