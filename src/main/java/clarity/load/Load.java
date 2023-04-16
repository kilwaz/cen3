package clarity.load;

import java.io.File;

public class Load {
    public static Excel excel(File excelFileToLoad) {
        return new Excel().file(excelFileToLoad);
    }
}
