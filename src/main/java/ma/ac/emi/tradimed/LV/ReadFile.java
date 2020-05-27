package ma.ac.emi.tradimed.LV;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadFile {
    private BufferedReader x;
    private String[] array;
    private FileCSV file;
    private List<String> table= new ArrayList<String>();
    private String line;

    public FileCSV getFile() {
        return file;
    }

    public void openFile(String path){
        try {
            x= new BufferedReader(new FileReader(path));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void readFile() throws IOException {
        file = new FileCSV();

        line=x.readLine();

        while(line!=null){
            //System.out.print("Line :"+line+"\n");
            array=line.split(",");
            //System.out.print("array[0]: "+array[0]+"\n");
            this.table.add(array[0]);
            line=x.readLine();
        }
        file.setListeWord(table);


    }

    public void closeFile() throws IOException {
        x.close();
    }
}
