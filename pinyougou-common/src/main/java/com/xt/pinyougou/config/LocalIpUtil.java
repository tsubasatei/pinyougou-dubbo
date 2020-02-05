package com.xt.pinyougou.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.*;
import java.util.Enumeration;

/**
 * 替换 localhost 或者域名为真实的 ip
 */
public class LocalIpUtil {
  private static Logger logger = LoggerFactory.getLogger(LocalIpUtil.class);
  private static final String WINDOWS = "WINDOWS";

  public static void main(String[] args) {
    String url = "http://127.0.0.1:8080/client1";

    System.out.println(replaceTrueIpIfLocalhost(url));
  }

  public static String replaceTrueIpIfLocalhost(String url) {
    String localIp = getLocalIp();

    if ((url.contains("localhost")) || (url.contains("127.0.0.1"))) {
      url = url.replaceAll("localhost", localIp).replaceAll("127.0.0.1", localIp);
    }
    return url;
  }

  private static String getLocalIp() {
    String os = System.getProperty("os.name").toUpperCase();
    String address = "";
    if (os.contains("WINDOWS"))
      try {
        address = InetAddress.getLocalHost().getHostAddress();
      } catch (UnknownHostException e) {
        logger.error("windows获取本地IP出错", e);
      }
    else {
      address = getLinuxIP();
    }
    return address;
  }

  private static String getLinuxIP() {
    String address = "";
    try {
      Enumeration allNetInterfaces = NetworkInterface.getNetworkInterfaces();
      InetAddress ip = null;
      while (allNetInterfaces.hasMoreElements()) {
        NetworkInterface netInterface = (NetworkInterface) allNetInterfaces.nextElement();
        if ((netInterface.isUp()) && (!netInterface.isLoopback()) && (!netInterface.isVirtual())) {
          Enumeration addresses = netInterface.getInetAddresses();
          while (addresses.hasMoreElements()) {
            ip = (InetAddress) addresses.nextElement();
            if ((!ip.isLoopbackAddress()) &&
                    (ip != null) && ((ip instanceof Inet4Address)))
              address = ip.getHostAddress();
          }
        }
      }
    } catch (SocketException e) {
      logger.error("linux获取本地IP出错", e);
    }
    return address;
  }
}