package Client;

import java.io.Serializable;

public class ClientInfo implements Serializable {

    public ClientInfo(String nickName, String ip) {
        this.nickName = nickName;
        this.ip = ip;
    }

    public String getNickName() {
        return nickName;
    }

    public String getIp() {
        return ip;
    }

    private String nickName ;

    private String ip ;

}
