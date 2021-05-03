package replacexmlnode;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;


public class ReplaceXmlNode {
    
    
    public static void main(String[] args) throws IOException {
        
        String pom = readFile(ReplaceXmlNode.class.getResourceAsStream("pom-plain.xml"));
        String web = readFile(ReplaceXmlNode.class.getResourceAsStream("pom-web.xml"));
        
        Document doc_web = Jsoup.parse(web,"",Parser.xmlParser());
        Document doc_pom = Jsoup.parse(pom,"",Parser.xmlParser());
        
        Elements dependencies_web = doc_web. getElementsByTag("dependencies");
        Elements dependencies_pom = doc_pom. getElementsByTag("dependencies");
        dependencies_pom.clear();
        dependencies_pom.addAll(dependencies_web);

        Files.writeString(Paths.get("pom-plain_out.xml"),doc_pom.toString(),StandardOpenOption.CREATE);
    }
    
    public static String readFile(InputStream in) throws IOException{
       
        return new String(in.readAllBytes(), StandardCharsets.UTF_8);
    }
}