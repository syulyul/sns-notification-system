package bitcamp.myapp;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class SSHTunnelManager {

    @Value("${ssh.host}")
    private String sshHost;

    @Value("${ssh.port}")
    private int sshPort;

    @Value("${ssh.username}")
    private String sshUsername;

    @Value("${ssh.password}")
    private String sshPassword;

    @Value("${ssh.localPort}")
    private int localPort;

    @Value("${ssh.remoteHost}")
    private String remoteHost;

    @Value("${ssh.remotePort}")
    private int remotePort;

    private Session session;

    @PostConstruct
    public void initializeSSHTunnel() {
        try {
            JSch jsch = new JSch();
            session = jsch.getSession(sshUsername, sshHost, sshPort);
            session.setPassword(sshPassword);
            session.setConfig("StrictHostKeyChecking", "no"); // 호스트 키 확인 비활성화

            // SSH 연결 시작
            session.connect();

            // SSH 터널 설정
            session.setPortForwardingL(localPort, remoteHost, remotePort);

            System.out.println("SSH 터널이 열렸습니다. 로컬 포트: " + localPort);
        } catch (JSchException e) {
            e.printStackTrace();
            System.err.println("SSH 연결에 실패했습니다. 애플리케이션을 종료합니다.");
            System.exit(1); // 애플리케이션 종료
        }
    }

    // Spring Boot 애플리케이션이 종료될 때 SSH 세션을 닫습니다.
    @PreDestroy
    public void closeSSHTunnel() {
        if (session != null && session.isConnected()) {
            session.disconnect();
            System.out.println("SSH 세션이 닫혔습니다.");
        }
    }
}




