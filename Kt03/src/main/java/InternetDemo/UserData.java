package InternetDemo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by PC-0775 on 2018/10/31.
 */

public class UserData {
    private List<UserInfo> userInfoList;
    private int numScores;       // number of scores in the array

    private static final int MAX_SCORES = 10;
    private static String USER_FN = "users.txt";

    public UserData(){
        userInfoList = new ArrayList<>();
        loadScores();
    }

    public String toString(){

        return null;
    }

    private void loadScores(){
        String line;
        try {
            BufferedReader in = new BufferedReader(new FileReader(USER_FN));
            while ((line = in.readLine()) != null) {
                addUser(line);
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addUser(String line){
        StringTokenizer st = new StringTokenizer(line, "&");
        String username = st.nextToken().trim();
        String password = st.nextToken().trim();
        addUser(username, password);

    }

    //先将所有用户读出来，写进内存，然后用于对比。浪费内存，需要优化
    public void addUser(String username, String password){
        userInfoList.add(new UserInfo(username, password));
    }

    public boolean checkUser(UserInfo user){
        boolean result = false;
        for (UserInfo userInfo : userInfoList){
            if (userInfo.getUsername().equals(user.getUsername()) && userInfo.getPassword().equals(user.getPassword())) {
                return true;
            }
        }

        return result;
    }

}

class UserInfo {
    private String username;
    private String password;

    public UserInfo(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
