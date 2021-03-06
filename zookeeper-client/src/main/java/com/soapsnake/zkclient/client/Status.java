package com.soapsnake.zkclient.client;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;

/**
 * Created by soapsnake on 2017/5/20.
 */
public class Status implements Watcher {
    private ZooKeeper zk;
    private String hostport;
    private String status;
    private String name;
    AsyncCallback.StatCallback statusUpdateCallback = new AsyncCallback.StatCallback() {
        public void processResult(int rc, String path, Object ctx, Stat stat) {
            switch (KeeperException.Code.get(rc)) {
                case CONNECTIONLOSS:
                    updateStatus((String) ctx);
                    break;
                case OK:
                    System.out.println("stat修改成功" + stat.toString() + ">>>>" + status);
                    break;
                default:
                    System.out.println("出现异常!");
            }
        }
    };

    public Status(String hostport, String name) {
        this.hostport = hostport;
        this.name = name;
    }

    void startZK() throws IOException {
        zk = new ZooKeeper(hostport, 15000, this);
    }

    public void process(WatchedEvent event) {
        System.out.println(event.toString() + ">>>>" + hostport);
    }

    void stopZK() throws InterruptedException {
        zk.close();
    }

    synchronized private void updateStatus(String status) {
        if (status.equals(this.status)) {
            zk.setData("/workers/" + name,
                    status.getBytes(),
                    -1,
                    statusUpdateCallback,
                    status);
        }
    }

    public void setStatus(String status) {
        this.status = status;
        updateStatus(status);
    }
}
