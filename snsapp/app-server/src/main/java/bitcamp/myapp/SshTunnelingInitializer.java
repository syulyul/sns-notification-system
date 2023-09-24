package bitcamp.myapp;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Profile;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.annotation.PreDestroy;
import java.util.Properties;

// SSH 연결 및 터널링을 설정하고 원격 데이터베이스 서버로의 연결을 가능

@Slf4j
@Profile("prod")
@Component
@ConfigurationProperties(prefix = "ssh")
@Validated
@Setter
public class SshTunnelingInitializer {

    private String host;
    private String user;
    private int sshPort;
    private String privateKey;
    private int databasePort;

    private Session session;


    @PreDestroy
    public void closeSSH() {
        if (session != null && session.isConnected())
            session.disconnect();
    }


    public Integer buildSshConnection() {

        Integer forwardedPort = null;

        try {
            log.info("{}@{}:{}:{} with privateKey",user, host, sshPort, databasePort);

            log.info("start ssh tunneling..");
            JSch jSch = new JSch();

            log.info("creating ssh session");

            jSch.addIdentity(privateKey);  // 개인키

            session = jSch.getSession(user, host, sshPort);  // 세션 설정
            Properties config = new Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);
            log.info("complete creating ssh session");

            log.info("start connecting ssh connection");
            session.connect();  // ssh 연결
            log.info("success connecting ssh connection ");

            // 로컬pc의 남는 포트 하나와 원격 접속한 pc의 db포트 연결
            log.info("start forwarding");
            forwardedPort = session.setPortForwardingL(3306, "localhost", databasePort);
            log.info("successfully connected to database");

        } catch (JSchException e){
            this.closeSSH();
            e.printStackTrace();
            log.error("fail to make ssh tunneling : {}", e.getMessage());
        }

        return forwardedPort;
    }
}