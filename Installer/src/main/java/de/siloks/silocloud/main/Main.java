package de.siloks.silocloud.main;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Erstellt von Lars am 18.08.2017.
 * Alle Rechte vorbehalten. Der Entwickler kann jederzeit
 * die Rechte an diesem Code entziehen!
 */
public class Main {

    private static String dataprefix = "SiloCloud-";
    private static String downloadserverUnreachable = "Downloadserver is currently unreachable - please try again later";
    private static String downloadserverFileNotFound = "Doesn't find the file on this downloadserver!";
    private static String downloading = "Download started!";
    private static String finished = "Download finished! - Saved in directory ../downloads";
    public static boolean update;

    public static void main(String[] args){

        update = false;

        for(int i = 0; i < 200; i++){
            System.out.println(" ");
        }
        System.out.println(" ");
        System.out.println("SiloCloud by Siloks");
        System.out.println(" ");
        System.out.println(" ");
        System.out.println("Auto-Setup?");
        System.out.println("  Use 'auto'");
        System.out.println(" ");
        System.out.println("What do you want to download?");
        System.out.println("  - all -    - wrapper -     - cloud -");
        System.out.println(" ");
        System.out.println("Want to exit the installer?");
        System.out.println("  Use 'exit'");
        System.out.println(" ");
        System.out.println("Want to update?");
        System.out.println("  Use 'update'");
        System.out.println(" ");
        System.out.println(" ");
        System.out.println(" ");
        System.out.println(" ");
        System.out.println(" ");
        System.out.println(" ");
        System.out.println(" ");
        System.out.println(" ");


        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                if(line.equalsIgnoreCase("exit")){
                    System.exit(0);
                }else if(line.equalsIgnoreCase("all")){
                    download(DownloadType.ALL);
                }else if(line.equalsIgnoreCase("wrapper")){
                    download(DownloadType.WRAPPER);
                }else if(line.equalsIgnoreCase("cloud")){
                    download(DownloadType.CLOUD);
                }else if(line.equalsIgnoreCase("auto")){
                    AUTOINSTALL();
                } else if(line.equalsIgnoreCase("update")){
                    if(update == false){
                        update = true;
                        for(int i = 0; i < 200; i++){
                            System.out.println(" ");
                        }
                        System.out.println("Do you want to update to the latest version?");
                        System.out.println("  Please confirm with 'update'");
                        System.out.println("  (Needs auto-setup!)");
                    }else{
                        UPDATE();
                    }
                } else {
                    System.out.println("Invalid command!");
                }
            }
        } catch (IOException e) {}
    }

    public static void downloader(URL url, String path, String name){
        System.out.println(downloading);

        try {
            final URLConnection conn = url.openConnection();
            final InputStream is = new BufferedInputStream(conn.getInputStream());
            final OutputStream os = new BufferedOutputStream(new FileOutputStream(path+name));
            byte[] chunk = new byte[1024];
            int chunkSize;
            while ((chunkSize = is.read(chunk)) != -1) {
                os.write(chunk, 0, chunkSize);
            }
            os.flush();
            os.close();
            is.close();
        } catch (IOException e) {

        }
        System.out.println(finished);
    }

    public static void download(DownloadType type) {
        File dir = new File("downloads");
        if(!(dir.exists())){
            dir.mkdirs();
        }
        if(type == DownloadType.ALL){
            try {
                downloader(new URL("http://nomizon.de/download/silocloud/wrapper.jar"), "downloads/", dataprefix+"wrapper.jar");
            } catch (MalformedURLException e) {
                System.err.println(downloadserverUnreachable);
            }
            try {
                downloader(new URL("http://nomizon.de/download/silocloud/cloud.jar"), "downloads/", dataprefix+"cloud.jar");
            } catch (MalformedURLException e) {
                System.err.println(downloadserverUnreachable);
            }
        }
        if(type == DownloadType.CLOUD){
            try {
                downloader(new URL("http://nomizon.de/download/silocloud/cloud.jar"), "downloads/", dataprefix+"cloud.jar");
            } catch (MalformedURLException e) {
                System.err.println(downloadserverUnreachable);
            }
        }else if(type == DownloadType.WRAPPER ){
            try {
                downloader(new URL("http://nomizon.de/download/silocloud/wrapper.jar"), "downloads/", dataprefix+"wrapper.jar");
            } catch (MalformedURLException e) {
                System.err.println(downloadserverUnreachable);
            }
        }else if(type == DownloadType.UPDATE){
            UPDATE();
        }
    }

    public static void download(DownloadType type, boolean windows) {
        try {
            if(windows == false){
                downloader(new URL("http://nomizon.de/download/silocloud/cloud.sh"), "SiloCloud/Cloud/", "cloud.sh");
                downloader(new URL("http://nomizon.de/download/silocloud/wrapper.sh"), "SiloCloud/Wrapper/", "wrapper.sh");
            }else if(windows == true){
                downloader(new URL("http://nomizon.de/download/silocloud/cloud.bat"), "SiloCloud/Cloud/", "cloud.bat");
                downloader(new URL("http://nomizon.de/download/silocloud/wrapper.bat"), "SiloCloud/Wrapper/", "wrapper.bat");
            }
            downloader(new URL("http://nomizon.de/download/silocloud/wrapper.jar"), "SiloCloud/Wrapper/", dataprefix+"wrapper.jar");
            downloader(new URL("http://nomizon.de/download/silocloud/cloud.jar"), "SiloCloud/Cloud/", dataprefix+"cloud.jar");
        } catch (MalformedURLException e) {
            System.err.println(downloadserverUnreachable);
        }
    }

    public static void AUTOINSTALL(){

        boolean windows = false;
        for(int i = 0; i < 200; i++){
            System.out.println(" ");
        }
        System.out.println("Which OS are you using?");
        System.out.println("Supports: <Windows> & <Linux>");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                if(line.equalsIgnoreCase("windows")){
                    windows = true;
                    System.out.println("Setup is running...");
                    File maindir = new File("SiloCloud");
                    if(!maindir.exists()){
                        maindir.mkdirs();
                    }
                    File clouddir = new File("SiloCloud/Cloud");
                    if(!clouddir.exists()){
                        clouddir.mkdirs();
                    }
                    File wrapperdir = new File("SiloCloud/Wrapper");
                    if(!wrapperdir.exists()){
                        wrapperdir.mkdirs();
                    }
                    download(DownloadType.AUTO, windows);
                    for(int i = 0; i < 200; i++){
                        System.out.println(" ");
                    }
                    System.out.println("Setup finished!");
                    reader.close();
                }else if(line.equalsIgnoreCase("linux")){
                    windows = false;
                    System.out.println("Setup is running...");
                    File maindir = new File("SiloCloud");
                    if(!maindir.exists()){
                        maindir.mkdirs();
                    }
                    File clouddir = new File("SiloCloud/Cloud");
                    if(!clouddir.exists()){
                        clouddir.mkdirs();
                    }
                    File wrapperdir = new File("SiloCloud/Wrapper");
                    if(!wrapperdir.exists()){
                        wrapperdir.mkdirs();
                    }
                    download(DownloadType.AUTO, windows);
                    for(int i = 0; i < 200; i++){
                        System.out.println(" ");
                    }
                    System.out.println("Setup finished!");
                    reader.close();
                }else{
                    for(int i = 0; i < 200; i++){
                        System.out.println(" ");
                    }
                    System.out.println("Which OS are you using?");
                    System.out.println("Supports: <Windows> & <Linux>");
                }
            }
        } catch (IOException e) {}
    }

    public static void UPDATE(){
        for(int i = 0; i < 200; i++){
            System.out.println(" ");
        }
        System.out.println("Deleting files...");
        File cloud = new File("SiloCloud/Cloud/cloud.jar");
        File wrapper = new File("SiloCloud/Wrapper/wrapper.jar");
        cloud.delete();
        wrapper.delete();
        for(int i = 0; i < 200; i++){
            System.out.println(" ");
        }
        System.out.println("Deleted files!");
        for(int i = 0; i < 200; i++){
            System.out.println(" ");
        }
        System.out.println("Downloading...");
        try {
            downloader(new URL("http://nomizon.de/download/silocloud/wrapper.jar"), "SiloCloud/Wrapper/", dataprefix+"wrapper.jar");
            downloader(new URL("http://nomizon.de/download/silocloud/cloud.jar"), "SiloCloud/Cloud/", dataprefix+"cloud.jar");
        } catch (MalformedURLException e) {
            System.err.println(downloadserverUnreachable);
        }
        for(int i = 0; i < 200; i++){
            System.out.println(" ");
        }
        System.out.println("Download finished!");
    }

}
