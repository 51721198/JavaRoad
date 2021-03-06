package com.soapsnake.zkclient.client;

import com.soapsnake.zkclient.constant.ZkConstant;
import org.apache.zookeeper.*;

import java.io.IOException;

/**
 * Created by soapsnake on 2017/5/21.
 */
public class ZooKeeperTest {

    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
        // 创建一个与服务器的连接
        ZooKeeper zk = new ZooKeeper(ZkConstant.ZK_HOST_PORT,
                15000, new Watcher() {
            // 监控所有被触发的事件
            public void process(WatchedEvent event) {
                System.out.println("已经触发了" + event.getType() + "事件！");
            }
        });

        // 同步模式创建一个目录节点
        zk.create("/testRootPath", "testRootData".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,
                CreateMode.PERSISTENT);

        // 同步模式创建一个子目录节点
        zk.create("/testRootPath/testChildPathOne", "testChildDataOne".getBytes(),
                ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);

        //利用getData获取单节点数据,第二个参数false表明不监控/testRootPath节点
        System.out.println(new String(zk.getData("/testRootPath", false, null)));

        // 修改子目录节点数据
        zk.setData("/testRootPath/testChildPathOne", "modifyChildDataOne".getBytes(), -1);

        //利用exists监控/testRootPath
        System.out.println("目录节点状态：[" + zk.exists("/testRootPath", true));

        // 创建另外一个子目录节点
        zk.create("/testRootPath/testChildPathTwo", "testChildDataTwo".getBytes(),
                ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

        // 利用getChildren监控/testRootPath,并且取出子目录节点列表
        System.out.println(zk.getChildren("/testRootPath", true));

        //利用getData监控/testRootPath/testChildPathTwo,
        System.out.println("" + new String(zk.getData("/testRootPath/testChildPathTwo", true, null)));

        // 删除子目录节点,testChildPathTwo被监控,因此必然会触发事件
        zk.delete("/testRootPath/testChildPathTwo", -1);

        ///testRootPath/testChildPathOne上面没有监控点,因此不会被监控!!!!!!!!这个删除语句不会触发事件
        zk.delete("/testRootPath/testChildPathOne", -1);

        // 删除父目录节点
        zk.delete("/testRootPath", -1);

        //关闭连接
        zk.close();
    }

}
