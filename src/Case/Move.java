package Case;

import Base.RunTime;
import org.apache.commons.io.FileUtils;

import static Base.RunTime.*;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;

public class Move {
    public static void move() throws IOException {
        File test = new File("/Users/sykim/Downloads"); //파일 리스트를 받을 경로
        ArrayList<String> fileNameList = new ArrayList<>();

        File[] fileList = test.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith("png"); //png 파일만 필터링 해서 리스트 받을 것임
            }
        });
        ArrayList<String> getFileList = new ArrayList<>();
//여기서부터 파일 옮기는 부분
        for(File file : fileList){
            getFileList.add(file.toString()); //받은 파일 리스트의 원래 경로 저장
        }

        try {
            for (int i = 0; i < getFileList.size(); i++) { //파일 리스트만큼 for문 돌리고
                File file = FileUtils.getFile(getFileList.get(i)); //원래 경로
                fileNameList.add(file.toString().substring(getFileList.get(i).lastIndexOf("/"))); //받은 파일 리스트에서 파일명만 저장
                RunTime.setDownloadTime();
                File fileToMove = FileUtils.getFile("/Users/sykim/AutomationProject/fileMove" + fileNameList.get(i) + "_" + downloadTime); //옮기고 싶은 경로 + 파일명 합쳐서 옮길 것
                FileUtils.moveFile(file, fileToMove); //원래 경로에서 원하는 경로로 옮기는 부분
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}