package ma.ac.emi.tradimed.utility;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class OnlineTranslator {
    public String getOnlineTranslation(String word){
        String[] result={""};
//        Some url used fro test
//        String url = "https://translate.google.co.ma/?hl=fr#view=home&op=translate&sl=fr&tl=ar&text="+word;
//        String url = "https://dictionnaire.reverso.net/francais-arabe/"+word;
//        String url = "https://context.reverso.net/traduction/francais-arabe/"+word;  select("div#translations-content");

        String url = "https://www.larousse.fr/dictionnaires/francais-arabe/"+word;
        try {
            Document doc = Jsoup.connect(url).userAgent("Mozilla/15.0").get();
//            System.out.println(doc.body());
            Elements elements = doc.select("table").attr("border","0");
            for (Element e:elements) {
                if(e.getElementsByTag("tbody").first().getElementsByTag("tr").first().getElementsByTag("td").first().getElementsByTag("div").first().getElementsByTag("span").text().equals("1.")){
                    Elements els = e.getElementsByTag("tbody").first().getElementsByTag("tr").select("span");
                    for(Element es:els){
                        if (es.hasAttr("dir")) {
                            //System.out.println(es.text()+"\n");
                            result = es.text().split(" ");
                            return result[0];
                        }
                    }
                    break;
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return result[0];
    }
}
