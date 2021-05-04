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
        
        String pom = readFile(ReplaceNode.class.getResourceAsStream("pom-plain.xml"));
        String web = readFile(ReplaceNode.class.getResourceAsStream("pom-web.xml"));
        
        Document doc_web = Jsoup.parse(web,"",Parser.xmlParser());
        Document doc_pom = Jsoup.parse(pom,"",Parser.xmlParser());
        
        Elements dependencies_web = doc_web. select("project>dependencies");       
        doc_pom.select("project>dependencies").remove();
        
        doc_pom.select("project").first().appendChild(dependencies_web.first());       
        
        doc_pom.outputSettings().prettyPrint(false);
        Files.write(Paths.get("pom-plain_out.xml"), doc_pom.toString().getBytes());
    }
    
    public static String readFile(InputStream in) throws IOException{
       
        return new String(in.readAllBytes(), StandardCharsets.UTF_8);
    }
}
