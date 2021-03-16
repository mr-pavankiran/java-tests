import java.io.File;
import java.nio.file.*;
import java.util.ArrayList;

public class DirWatcher {

    private static final String DROPBOX_FOLDER="/opt/dropbox-dev";
    private static ArrayList<String> fileListFromFolder(String inputPath) {
        File file = new File(inputPath);
        String[] fileList = file.list();
        ArrayList<String> names= new ArrayList<String>();
        for (String name : fileList) {
            System.out.println(name);
           String [] parts= name.split("_");
            names.add(parts[0]);
           System.out.println("submitter code:"+parts[0]);
        }
        return names;
    }

    private static void watchService(String inPath) throws Exception {

        WatchService watchService = FileSystems.getDefault().newWatchService();

        Path path = Paths.get(inPath);

        path.register(
                watchService,
                StandardWatchEventKinds.ENTRY_CREATE,
                StandardWatchEventKinds.ENTRY_DELETE,
                StandardWatchEventKinds.ENTRY_MODIFY);

        WatchKey key;
        while ((key = watchService.take()) != null) {
            for (WatchEvent<?> event : key.pollEvents()) {
                if (event.kind() == StandardWatchEventKinds.ENTRY_CREATE) {
                    event.context();
                    fileListFromFolder(DROPBOX_FOLDER);
                }
            }
            key.reset();
        }
    }

    public static void main(String[] args) throws Exception {

   MysqlConn.getOutletNames(fileListFromFolder(DROPBOX_FOLDER).toString());
    }

}
