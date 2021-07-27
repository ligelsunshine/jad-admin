/*
 * Copyright (C), 2021-2021, jad
 */

package com.jad.common.utils;

import com.sshtools.net.SocketTransport;
import com.sshtools.publickey.ConsoleKnownHostsKeyVerification;
import com.sshtools.sftp.SftpClient;
import com.sshtools.sftp.SftpStatusException;
import com.sshtools.sftp.TransferCancelledException;
import com.sshtools.ssh.ChannelOpenException;
import com.sshtools.ssh.PasswordAuthentication;
import com.sshtools.ssh.SshAuthentication;
import com.sshtools.ssh.SshClient;
import com.sshtools.ssh.SshConnector;
import com.sshtools.ssh.SshException;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * SshClientUtil
 *
 * @author cxxwl96
 * @since 2021/7/27 22:19
 */
public class SshClientUtil {
    private static SftpClient sftp;

    private final SshClient ssh;

    public SshClientUtil(String hostName, String username, String password) {
        this(hostName, 22, username, password);
    }

    public SshClientUtil(String hostName, int port, String username, String password) {
        try {
            System.out.println("Connecting to " + hostName + "...");
            SshConnector connector = SshConnector.createInstance();
            connector.getContext().setHostKeyVerification(new ConsoleKnownHostsKeyVerification());
            SocketTransport transport = new SocketTransport(hostName, port);
            // 初始化ssh
            System.out.println("Creating SSH client");
            ssh = connector.connect(transport, username, true);
            PasswordAuthentication pwd = new PasswordAuthentication();
            pwd.setPassword(password);
            // 认证
            System.out.println("Authenticate with password...");
            switch (ssh.authenticate(pwd)) {
                case SshAuthentication.COMPLETE:
                    System.out.println("Complete");
                    break;
                case SshAuthentication.FAILED:
                    System.out.println("Authentication failed");
                    throw new RuntimeException("Authentication failed");
                case SshAuthentication.FURTHER_AUTHENTICATION_REQUIRED:
                    System.out.println("Further authentication required");
                    break;
                case SshAuthentication.CANCELLED:
                    System.out.println("Cancelled");
                    break;
                case SshAuthentication.PUBLIC_KEY_ACCEPTABLE:
                    System.out.println("Public key acceptable");
                    break;
            }
            System.out.println("connected: " + ssh.isConnected());
            System.out.println("authenticated: " + ssh.isAuthenticated());
            if (!ssh.isConnected()) {
                throw new RuntimeException("Disconnected");
            }
            if (!ssh.isAuthenticated()) {
                throw new RuntimeException("Unauthorized");
            }
            // 初始化sftp
            if (ssh.isAuthenticated()) {
                sftp = new SftpClient(ssh);
            }
        } catch (SshException | IOException | SftpStatusException | ChannelOpenException exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }

    /**
     * getSftpClient
     *
     * @return sftp client
     */
    public SftpClient getSftpClient() {
        return sftp;
    }

    /**
     * 下载文件到本地指定路径
     *
     * @param remote 远程文件地址
     * @param local 本地地址
     */
    public void downloadFile(String remote, String local) {
        try {
            // 使用正则
            sftp.setRegularExpressionSyntax(SftpClient.GlobSyntax);
            sftp.getFiles(remote, local);
        } catch (FileNotFoundException | SftpStatusException | SshException | TransferCancelledException exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }

    /**
     * 断开连接
     */
    public void disconnect() {
        if (ssh != null) {
            ssh.disconnect();
        }
    }
}