package clarity.load;

import java.io.File;

public class Load {
    public static Excel excel(File excelFileToLoad) {
        return new Excel().file(excelFileToLoad);
    }

    public static ConfigJSON configJSON(File excelFileToLoad){
        return new ConfigJSON().file(excelFileToLoad);
    }
}
